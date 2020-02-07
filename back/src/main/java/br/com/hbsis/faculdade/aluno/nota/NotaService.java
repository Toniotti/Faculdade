package br.com.hbsis.faculdade.aluno.nota;

import br.com.hbsis.faculdade.aluno.AlunoService;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotaService {
    private final INotaRespository notaRespository;
    private final AlunoService alunoService;

    public NotaService(INotaRespository notaRespository, AlunoService alunoService) {
        this.notaRespository = notaRespository;
        this.alunoService = alunoService;
    }

    public NotaDTO save(NotaDTO notaDTO) {
        Nota nota = new Nota();
        nota.setAluno(this.alunoService.getEntityById(notaDTO.getIdAluno()));
        nota.setDescricao(notaDTO.getDesc());
        nota.setNota(notaDTO.getNota());

        return NotaDTO.of(this.notaRespository.save(nota));
    }

    public NotaDTO update(NotaDTO notaDTO, Long idNota) {
        Optional<Nota> notaOptional = this.notaRespository.findById(idNota);

        if (notaOptional.isPresent()) {
            Nota nota = notaOptional.get();
            nota.setNota(notaDTO.getNota());
            nota.setDescricao(notaDTO.getDesc());
            nota.setAluno(this.alunoService.getEntityById(notaDTO.getIdAluno()));

            return NotaDTO.of(nota);
        }
        throw new NoResultException("Nota não encontrada.");
    }

    public void delete(Long idNota) {
        this.notaRespository.deleteById(idNota);
    }

    public List<NotaDTO> getNotaByAluno(Long idAluno) {
        List<NotaDTO> notaDTOList = new ArrayList<>();
        this.notaRespository.findByAluno(this.alunoService.getEntityById(idAluno)).forEach(nota -> notaDTOList.add(NotaDTO.of(nota)));

        if (notaDTOList.isEmpty()) {
            throw new NoResultException("Nenhuma nota encontrada para esse aluno.");
        }

        return notaDTOList;
    }

    public List<Nota> findAllEntityById(List<Long> notas) {
        return this.notaRespository.findAllById(notas);
    }
}
