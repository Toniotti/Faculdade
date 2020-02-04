package br.com.hbsis.faculdade.aluno.nota;

import br.com.hbsis.faculdade.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotaRespository extends JpaRepository<Nota, Long> {

    List<Nota> findByAluno(Aluno entityById);
}
