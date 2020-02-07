package br.com.hbsis.faculdade.aluno.boletim;

import br.com.hbsis.faculdade.aluno.Aluno;
import br.com.hbsis.faculdade.aluno.nota.Nota;

import javax.persistence.*;
import java.util.List;

@Entity
public class Boletim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Aluno aluno;
    @OneToMany
    private List<Nota> notas;

    public Boletim() {
    }

    public Boletim(Long id, Aluno aluno, List<Nota> notas) {
        this.id = id;
        this.aluno = aluno;
        this.notas = notas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}


