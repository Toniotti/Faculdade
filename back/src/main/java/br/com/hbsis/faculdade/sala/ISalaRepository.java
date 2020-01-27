package br.com.hbsis.faculdade.sala;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalaRepository extends JpaRepository<Sala, Long> {
    Sala findByLetraSala(String letraSala);
}
