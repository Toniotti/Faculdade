package br.com.hbsis.faculdade.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, Long> {
}
