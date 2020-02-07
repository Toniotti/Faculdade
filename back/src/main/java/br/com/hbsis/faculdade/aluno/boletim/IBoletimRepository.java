package br.com.hbsis.faculdade.aluno.boletim;

import br.com.hbsis.faculdade.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBoletimRepository extends JpaRepository<Boletim, Long> {

    Optional<Boletim> findByAluno(Aluno aluno);
}
