package br.com.hbsis.faculdade.aluno;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AlunoDTO {
    private Long matricula;
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

    public AlunoDTO(@NotNull Long matricula, @NotNull @NotBlank String nome, @NotNull @NotBlank String sala, @NotNull Integer serie) {
        this.matricula = matricula;
        this.nome = nome;
        this.sala = sala;
        this.serie = serie;
    }

    public static AlunoDTO of(Aluno aluno) {
        return new AlunoDTO(
                aluno.getId(),
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

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
}
