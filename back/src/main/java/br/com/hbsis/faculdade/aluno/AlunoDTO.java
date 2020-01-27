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

    public AlunoDTO() {
    }

    public AlunoDTO(String nome, String letraSala) {
        this.nome = nome;
        this.sala = letraSala;
    }

    public static AlunoDTO of(Aluno aluno) {
        return new AlunoDTO(
                aluno.getNome(),
                aluno.getSala().getLetraSala());
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
}
