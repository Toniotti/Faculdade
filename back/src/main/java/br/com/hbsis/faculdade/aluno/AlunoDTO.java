package br.com.hbsis.faculdade.aluno;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AlunoDTO {
    private Long matricula;
    @NotNull(message = "O nome precisa ser informado.")
    @NotBlank(message = "O nome precisa ser informado.")
    @Size(max = 40, message = "O nome do aluno não pode ser maior do que 40.")
    private String nome;
    @NotNull(message = "A letra da sala precisa ser informada.")
    @NotBlank(message = "A letra da sala precisa ser informada.")
    @Size(max = 2, message = "A letra da sala não pode ser maior do que 2.")
    private String sala;
    @NotNull(message = "A serie precisa ser informada")
    @Max(value = 3, message = "A serie não pode ser maior do que 3.")
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
