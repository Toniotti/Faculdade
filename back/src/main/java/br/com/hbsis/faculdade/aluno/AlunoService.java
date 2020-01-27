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
        this.validateDto(alunoDTO);
        Sala sala = this.salaService.findEntityByLetra(alunoDTO.getSala());
        Aluno aluno = new Aluno(
                alunoDTO.getNome(),
                sala
        );

        return AlunoDTO.of(this.alunoRepository.save(aluno));
    }

    public AlunoDTO update(AlunoDTO alunoDTO, String nome, String letraSala) throws InvalidPropertiesFormatException {
        this.validateDto(alunoDTO);
        Aluno aluno = this.findEntityByNomeAndSala(nome, letraSala);
        aluno.setNome(alunoDTO.getNome());
        aluno.setSala(this.salaService.findEntityByLetra(alunoDTO.getSala()));

        return AlunoDTO.of(this.alunoRepository.save(aluno));
    }

    public void delete(String nome, String sala){
        Aluno aluno = this.findEntityByNomeAndSala(nome, sala);
        this.alunoRepository.delete(aluno);
    }

    public Aluno findEntityByNomeAndSala(String nome, String letraSala){
        Sala sala = this.salaService.findEntityByLetra(letraSala);
        Aluno aluno = this.alunoRepository.findByNomeAndSala(nome, sala);

        if (aluno != null) {
            return this.alunoRepository.save(aluno);
        }
        throw new NoResultException("Nenhum aluno encontrado com esse nome.");
    }

    private void validateDto(AlunoDTO alunoDTO) throws InvalidPropertiesFormatException {
        if (alunoDTO.getNome().isEmpty() || alunoDTO.getNome() == null) {
            throw new InvalidPropertiesFormatException("O nome não pode estar vazio.");
        }
        if (alunoDTO.getSala().isEmpty() || alunoDTO.getSala() == null) {
            throw new InvalidPropertiesFormatException("A letra da sala não pode estar vazia.");
        }
    }
}
