package br.com.hbsis.faculdade.aluno;

import br.com.hbsis.faculdade.sala.Sala;
import br.com.hbsis.faculdade.sala.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.InvalidPropertiesFormatException;

@Service
public class AlunoService {
    @Autowired
    private final IAlunoRepository alunoRepository;
    private final SalaService salaService;

    public AlunoService(IAlunoRepository alunoRepository, SalaService salaService) {
        this.alunoRepository = alunoRepository;
        this.salaService = salaService;
    }

    public AlunoDTO save(AlunoDTO alunoDTO) throws InvalidPropertiesFormatException {
        Sala sala = this.salaService.findEntityByLetra(alunoDTO.getSerie(), alunoDTO.getSala());
        Aluno aluno = new Aluno(
                alunoDTO.getNome(),
                sala
        );

        return AlunoDTO.of(this.alunoRepository.save(aluno));
    }

    public AlunoDTO update(AlunoDTO novoAluno, AlunoDTO antigoAluno) throws InvalidPropertiesFormatException {
        Aluno aluno = this.findEntityByDTO(antigoAluno);
        aluno.setNome(novoAluno.getNome());
        aluno.setSala(this.salaService.findEntityByLetra(novoAluno.getSerie(), novoAluno.getSala()));

        return AlunoDTO.of(this.alunoRepository.save(aluno));
    }

    public void delete(AlunoDTO alunoDTO){
        Aluno aluno = this.findEntityByDTO(alunoDTO);
        this.alunoRepository.delete(aluno);
    }

    public Aluno findEntityByDTO(AlunoDTO alunoDTO){
        Sala sala = this.salaService.findEntityByLetra(alunoDTO.getSerie(), alunoDTO.getSala());
        Aluno aluno = this.alunoRepository.findByNomeAndSala(alunoDTO.getNome(), sala);

        if (aluno != null) {
            return this.alunoRepository.save(aluno);
        }
        throw new NoResultException("Nenhum aluno encontrado com esse nome.");
    }

}
