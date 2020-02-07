package br.com.hbsis.faculdade.professor;

import br.com.hbsis.faculdade.sala.SalaDTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDTO {
    @Size(max = 40, message = "O nome não pode ser maior do que 40.")
    @NotNull(message = "O nome não pode estar vazio.")
    @NotBlank(message = "O nome não pode estar vazio.")
    private String nome;
    @NotNull(message = "O professor precisa estar cadastrado em pelo menos uma sala.")
    @Max(value = 10, message = "O professor não pode ter mais do que 10 salas cadastradas.")
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
