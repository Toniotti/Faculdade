package br.com.hbsis.faculdade.aluno;

import br.com.hbsis.faculdade.sala.Sala;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByNomeAndSala(String nome, Sala sala, Pageable pageable);
}
