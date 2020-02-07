package br.com.hbsis.faculdade.aluno.nota;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NotaDTO {
    private Long idNota;
    @NotNull(message = "A nota precisa ser informada.")
    @Max(value = 10, message = "A nota não pode ser maior do que 10.")
    private Double nota;
    @NotNull(message = "O aluno precisa ser informado.")
    private Long idAluno;
    @NotBlank(message = "A descrição precisa ser informada.")
    @NotNull(message = "A descrição precisa ser informada.")
    @Size(max = 120, message = "A descrição não pode ser maior do que 120.")
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
