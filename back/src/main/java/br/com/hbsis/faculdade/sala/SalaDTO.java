package br.com.hbsis.faculdade.sala;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SalaDTO {
    @NotNull
    private Integer serie;
    @NotNull
    @NotBlank
    private String letra;

    public SalaDTO() {
    }

    public SalaDTO(Integer serie, String letra) {
        this.serie = serie;
        this.letra = letra;
    }

    public static SalaDTO of(Sala sala){
        return new SalaDTO(sala.getSerie(), sala.getLetraSala());
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
}
