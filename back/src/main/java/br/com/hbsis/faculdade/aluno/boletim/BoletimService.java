package br.com.hbsis.faculdade.aluno.boletim;

import br.com.hbsis.faculdade.aluno.Aluno;
import br.com.hbsis.faculdade.aluno.AlunoService;
import br.com.hbsis.faculdade.aluno.nota.Nota;
import br.com.hbsis.faculdade.aluno.nota.NotaService;
import net.bytebuddy.utility.RandomString;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BoletimService {
    private final IBoletimRepository boletimRepository;
    private final AlunoService alunoService;
    private final NotaService notaService;
    Logger LOGGER = LoggerFactory.getLogger(Boletim.class);

    public BoletimService(IBoletimRepository boletimRepository, AlunoService alunoService, NotaService notaService) {
        this.boletimRepository = boletimRepository;
        this.alunoService = alunoService;
        this.notaService = notaService;
    }

    public BoletimDTO save(BoletimDTO boletimDTO) {
        Boletim boletim = new Boletim();
        boletim.setAluno(this.alunoService.getEntityById(boletimDTO.getAluno()));
        boletim.setNotas(this.notaService.findAllEntityById(boletimDTO.getNotas()));

        boletim = this.boletimRepository.save(boletim);

        return BoletimDTO.of(boletim);
    }

    public void generateJasper(Long matriculaAluno, HttpServletResponse response) {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=boletim_" + RandomString.make() + ".pdf");

        try (
                OutputStream outputStream = response.getOutputStream();
        ) {
            Aluno aluno = this.alunoService.getEntityById(matriculaAluno);
            List<Nota> notaList = this.notaService.getNotaEntityByAluno(matriculaAluno);

            LOGGER.info("Iniciando a geração do boletim para o aluno {}", aluno.getNome());

            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("boletim.jrxml");
            File file = new File(resource.getPath());
            JasperDesign design = JRXmlLoader.load(file);

            JasperReport jasperReport = JasperCompileManager.compileReport(design);


            LOGGER.info("Salvando boletim para o aluno {}", aluno.getNome());

            String generatedId = this.save(new BoletimDTO(aluno.getId(), notaList.stream().map(Nota::getId).collect(Collectors.toList()))).getId();

            Map parameters = new HashMap();
            parameters.put("nota", notaList.stream().map(Nota::getNota).collect(Collectors.toList()));
            parameters.put("descricao", notaList.stream().map(Nota::getDescricao).collect(Collectors.toList()));
            parameters.put("nome", aluno.getNome());
            parameters.put("data", new Date(System.currentTimeMillis()).toString());
            parameters.put("identifier", generatedId);

            LOGGER.info("Salvando arquivo do boletim.");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            LOGGER.info("Boletim gerado para o aluno {}", aluno.getNome());
        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
    }


}
