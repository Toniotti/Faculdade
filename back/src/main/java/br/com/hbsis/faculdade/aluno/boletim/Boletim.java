package br.com.hbsis.faculdade.aluno.boletim;

import br.com.hbsis.faculdade.aluno.Aluno;
import br.com.hbsis.faculdade.aluno.nota.Nota;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Document(collection = "boletim")
public class Boletim {
    @Id
    private String id;
    @OneToOne
    private Aluno aluno;
    @ManyToMany
    private List<Nota> notas;

    public Boletim() {
    }

    public Boletim(String id, Aluno aluno, List<Nota> notas) {
        this.id = id;
        this.aluno = aluno;
        this.notas = notas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


