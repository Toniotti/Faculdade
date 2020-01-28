package br.com.hbsis.faculdade.aluno;

import br.com.hbsis.faculdade.sala.Sala;
import br.com.hbsis.faculdade.sala.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.*;

@Service
public class AlunoService {
    @Autowired
    private final IAlunoRepository alunoRepository;
    private final SalaService salaService;

    public AlunoService(IAlunoRepository alunoRepository, SalaService salaService) {
        this.alunoRepository = alunoRepository;
        this.salaService = salaService;
    }

    public AlunoDTO save(AlunoDTO alunoDTO) {
        Sala sala = this.salaService.findEntityBySerieAndLetra(alunoDTO.getSerie(), alunoDTO.getSala());
        Aluno aluno = new Aluno(
                alunoDTO.getNome(),
                sala
        );

        return AlunoDTO.of(this.alunoRepository.save(aluno));
    }

    public AlunoDTO update(AlunoDTO alunoDTO, Long matricula) {
        Optional<Aluno> alunoOptional = this.alunoRepository.findById(matricula);
        if(!alunoOptional.isPresent()){
            throw new NoResultException("Não foi encontrado nenhum aluno com essa matricula.");
        }
        Aluno aluno = alunoOptional.get();
        aluno.setNome(alunoDTO.getNome());
        aluno.setSala(this.salaService.findEntityBySerieAndLetra(alunoDTO.getSerie(), alunoDTO.getSala()));

        return AlunoDTO.of(this.alunoRepository.save(aluno));
    }

    public void delete(Long matricula){
        Optional<Aluno> alunoOptional = this.alunoRepository.findById(matricula);
        if(alunoOptional.isPresent()){
            this.alunoRepository.delete(alunoOptional.get());
        }
        throw new NoResultException("Nenhum aluno foi encontrado com essa matricula.");
    }

    public AlunoDTO get(Long matricula){
        Optional<Aluno> alunoOptional = this.alunoRepository.findById(matricula);
        if(alunoOptional.isPresent()){
            return AlunoDTO.of(alunoOptional.get());
        }
        throw new NoResultException("Nenhum aluno foi encontrado co messa matricula.");
    }

    public List<AlunoDTO> getAllPaginated(String nome, String letra, int serie, int page, int size){
        Sala sala = this.salaService.findEntityBySerieAndLetra(serie, letra);
        List<AlunoDTO> alunoDTOList = new ArrayList<>();
        this.alunoRepository.findByNomeAndSala(nome, sala, PageRequest.of(page, size)).forEach(aluno -> alunoDTOList.add(AlunoDTO.of(aluno)));
        return alunoDTOList;
    }

}
