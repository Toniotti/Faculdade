package br.com.hbsis.faculdade.professor;

import br.com.hbsis.faculdade.sala.SalaDTO;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDTO {
    private String nome;
    private List<SalaDTO> salas;

    public ProfessorDTO() {
    }

    public ProfessorDTO(String nome, List<SalaDTO> salas) {
        this.nome = nome;
        this.salas = salas;
    }

    public static ProfessorDTO of(Professor professor){
        List<SalaDTO> salaDTOList = new ArrayList<>();
        professor.getSala().forEach(sala -> salaDTOList.add(SalaDTO.of(sala)));
        return new ProfessorDTO(
                professor.getNome(),
                salaDTOList
        );
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<SalaDTO> getSalas() {
        return salas;
    }

    public void setSalas(List<SalaDTO> salas) {
        this.salas = salas;
    }
}
