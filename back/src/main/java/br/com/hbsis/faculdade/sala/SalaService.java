package br.com.hbsis.faculdade.sala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class SalaService {
    @Autowired
    private final ISalaRepository salaRepository;

    public SalaService(ISalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public Sala findEntityByLetra(String letraSala) {
        Sala sala = this.salaRepository.findByLetraSala(letraSala);
        if (!(sala == null)) {
            return sala;
        } else {
            throw new NoResultException("Não foi encontrado uma sala com essa letra");
        }
    }
}
