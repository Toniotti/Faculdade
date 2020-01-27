package br.com.hbsis.faculdade.sala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalaService {
    @Autowired
    private final ISalaRepository salaRepository;

    public SalaService(ISalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public Sala findEntityBySerieAndLetra(Integer serie, String letraSala) {
        Sala sala = this.salaRepository.findBySerieAndLetraSala(serie, letraSala);
        if (!(sala == null)) {
            return sala;
        } else {
            throw new NoResultException("Não foi encontrado uma sala com essa letra");
        }
    }

    public SalaDTO save(SalaDTO salaDTO){
        Sala sala = new Sala(salaDTO.getSerie(), salaDTO.getLetra());
        return SalaDTO.of(this.salaRepository.save(sala));
    }

    public SalaDTO update(SalaDTO salaDTO, Integer serie, String letra){
        Sala sala = this.findEntityBySerieAndLetra(serie, letra);
        sala.setLetraSala(salaDTO.getLetra());
        sala.setSerie(salaDTO.getSerie());

        return SalaDTO.of(this.salaRepository.save(sala));
    }

    public void delete(Integer serie, String letra){
        Sala sala = this.findEntityBySerieAndLetra(serie, letra);
        if(sala == null){
            throw new NoResultException("Nenhuma sala encontrada para essa serie e letra.");
        }
        this.salaRepository.delete(sala);
    }

    public List<SalaDTO> getAllSalas(){
        List<SalaDTO> salaList = new ArrayList<>();
        for (Sala sala : this.salaRepository.findAll()){
            salaList.add(SalaDTO.of(sala));
        }

        if(salaList.isEmpty()){
            throw new NoResultException("Nenhuma sala foi encontrada.");
        }

        return salaList;
    }
}
