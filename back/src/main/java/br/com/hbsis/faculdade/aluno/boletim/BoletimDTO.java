package br.com.hbsis.faculdade.aluno.boletim;

import br.com.hbsis.faculdade.aluno.AlunoDTO;

import java.util.ArrayList;
import java.util.List;

public class BoletimDTO {


    private Long id;
    private Long aluno;
    private List<Long> notas;

    public BoletimDTO() {
    }

    public BoletimDTO(Long id, Long aluno, List<Long> notas) {
        this.id = id;
        this.aluno = aluno;
        this.notas = notas;
    }

    public static BoletimDTO of(Boletim boletim) {
        List<Long> notaList = new ArrayList<>();
        boletim.getNotas().forEach(nota -> notaList.add(nota.getId()));

        return new BoletimDTO(
                boletim.getId(),
                AlunoDTO.of(boletim.getAluno()).getMatricula(),
                notaList
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAluno() {
        return aluno;
    }

    public void setAluno(Long aluno) {
        this.aluno = aluno;
    }

    public List<Long> getNotas() {
        return notas;
    }

    public void setNotas(List<Long> notas) {
        this.notas = notas;
    }
}
