package br.com.hbsis.faculdade.aluno.nota;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NotaDTO {
    private Long idNota;
    @NotNull
    private Double nota;
    @NotNull
    private Long idAluno;
    @NotBlank
    @NotNull
    private String desc;

    public NotaDTO() {
    }

    public NotaDTO(Long idNota, Double nota, Long idAluno, String desc) {
        this.idNota = idNota;
        this.nota = nota;
        this.idAluno = idAluno;
        this.desc = desc;
    }

    public static NotaDTO of(Nota nota) {
        return new NotaDTO(
                nota.getId(),
                nota.getNota(),
                nota.getAluno().getId(),
                nota.getDescricao()
        );
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getIdNota() {
        return idNota;
    }

    public void setIdNota(Long idNota) {
        this.idNota = idNota;
    }
}
