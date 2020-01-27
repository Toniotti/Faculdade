package br.com.hbsis.faculdade.aluno;

import br.com.hbsis.faculdade.sala.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findByNomeAndSala(String nome, Sala sala);
}
