package br.com.hbsis.faculdade.aluno.boletim;

public class ReportDTO {
    private Double nota;
    private String descricao;

    public ReportDTO() {
    }

    public ReportDTO(Double nota, String descricao) {
        this.nota = nota;
        this.descricao = descricao;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
