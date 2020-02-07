package br.com.hbsis.faculdade.sala;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SalaDTO {
    @NotNull(message = "A serie precisa ser informada.")
    @Max(value = 3, message = "A serie não pode ser maior do que 3.")
    private Integer serie;
    @NotNull(message = "A letra precisa ser informada.")
    @NotBlank(message = "A letra precisa ser informada.")
    @Size(max = 2, message = "A letra não pode conter mais do que 2 caracteres.")
    private String letra;

    public SalaDTO() {
    }

    public SalaDTO(Integer serie, String letra) {
        this.serie = serie;
        this.letra = letra;
    }

    public static SalaDTO of(Sala sala) {
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
