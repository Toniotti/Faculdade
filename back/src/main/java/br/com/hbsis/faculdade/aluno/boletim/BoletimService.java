package br.com.hbsis.faculdade.aluno.boletim;

import br.com.hbsis.faculdade.aluno.Aluno;
import br.com.hbsis.faculdade.aluno.AlunoService;
import br.com.hbsis.faculdade.aluno.nota.NotaService;
import net.bytebuddy.utility.RandomString;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BoletimService {
    Logger LOGGER = LoggerFactory.getLogger(Boletim.class);

    private final IBoletimRepository boletimRepository;
    private final AlunoService alunoService;
    private final NotaService notaService;

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

    public Boletim findByMatriculaAluno(Long matricula){
        Aluno aluno = this.alunoService.getEntityById(matricula);
        Optional<Boletim> boletimOptional = this.boletimRepository.findByAluno(aluno);

        if(boletimOptional.isPresent()){
            return boletimOptional.get();
        }

        throw new NoResultException("Boletim não encontrado.");
    }

    public void generateJasper(Long matriculaAluno, HttpServletResponse response) {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=boletim_"+ RandomString.make()+".pdf");

        try (
                OutputStream outputStream = response.getOutputStream();
        ) {
            Boletim boletim = this.findByMatriculaAluno(matriculaAluno);

            LOGGER.info("Iniciando a geração do boletim para o aluno {}", boletim.getAluno().getNome());

            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("boletim.jrxml");
            File file = new File(resource.getPath());
            JasperDesign design = JRXmlLoader.load(file);

            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            List<ReportDTO> reports = new ArrayList<>();

            boletim.getNotas().forEach(nota -> {
                reports.add(new ReportDTO(nota.getNota(), nota.getDescricao()));
            });

            Map parameters = new HashMap();
            parameters.put("descricao", reports.stream().map(ReportDTO::getDescricao).collect(Collectors.toList()));
            parameters.put("nota", reports.stream().map(ReportDTO::getNota).collect(Collectors.toList()));
            parameters.put("nome", boletim.getAluno().getNome());
            parameters.put("data", new Date(System.currentTimeMillis()).toString());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            LOGGER.info("Boletim gerado para o aluno {}", boletim.getAluno().getNome());
        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
    }


}
