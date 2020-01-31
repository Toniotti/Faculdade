package br.com.hbsis.faculdade.sala;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISalaRepository extends JpaRepository<Sala, Long> {
    Sala findBySerieAndLetraSala(Integer serie, String letraSala);
    List<Sala> findBySerie(Integer serie);
}
