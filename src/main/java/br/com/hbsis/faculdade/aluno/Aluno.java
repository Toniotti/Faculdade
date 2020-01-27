package br.com.hbsis.faculdade.aluno;

import br.com.hbsis.faculdade.sala.Sala;

import javax.persistence.Entity;

@Entity
public class Aluno {
    private Long id;
    private String nome;
    private Sala sala;

    public Aluno(Long id, String nome, Sala sala) {
        this.id = id;
        this.nome = nome;
        this.sala = sala;
    }

    public Aluno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
