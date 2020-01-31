package br.com.hbsis.faculdade.professor;

import br.com.hbsis.faculdade.sala.Sala;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findByNomeContainingIgnoreCaseAndSalaIn(@Param("nome") String nome, @Param("sala") List<Sala> sala, Pageable pageable);
}
