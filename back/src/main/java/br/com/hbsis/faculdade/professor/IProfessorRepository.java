package br.com.hbsis.faculdade.professor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessorRepository extends JpaRepository<Professor, Long> {
}
