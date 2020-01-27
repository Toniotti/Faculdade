package br.com.hbsis.faculdade.aluno;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AlunoDTO {
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String sala;
    @NotNull
    private Integer serie;

    public AlunoDTO() {
    }

    public AlunoDTO(String nome, String letraSala, Integer serie) {
        this.nome = nome;
        this.sala = letraSala;
        this.serie = serie;
    }

    public static AlunoDTO of(Aluno aluno) {
        return new AlunoDTO(
                aluno.getNome(),
                aluno.getSala().getLetraSala(),
                aluno.getSala().getSerie()
                );
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }
}
