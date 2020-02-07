package br.com.hbsis.faculdade.professor;

import br.com.hbsis.faculdade.sala.Sala;

import javax.persistence.*;
import java.util.List;

@Entity(name = "professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @ManyToMany
    private List<Sala> sala;

    public Professor() {
    }

    public Professor(Long id, String nome, List<Sala> sala) {
        this.id = id;
        this.nome = nome;
        this.sala = sala;
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

    public List<Sala> getSala() {
        return sala;
    }

    public void setSala(List<Sala> sala) {
        this.sala = sala;
    }
}
