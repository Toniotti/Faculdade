package br.com.hbsis.faculdade.aluno.nota;

import br.com.hbsis.faculdade.aluno.Aluno;

import javax.persistence.*;

@Entity(name = "notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double nota;
    @ManyToOne(targetEntity = Aluno.class)
    @JoinColumn(name = "fk_aluno")
    private Aluno aluno;
    private String descricao;

    public Nota() {
    }

    public Nota(Long id, Double nota, Aluno aluno, String descricao) {
        this.id = id;
        this.nota = nota;
        this.aluno = aluno;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
