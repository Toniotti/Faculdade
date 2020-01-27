package br.com.hbsis.faculdade.sala;

import javax.persistence.*;

@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "serie")
    private int serie;
    @Column(name = "letra_sala")
    private String letraSala;

    public Sala(Long id, int serie, String letra_sala) {
        this.id = id;
        this.serie = serie;
        this.letraSala = letra_sala;
    }

    public Sala() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public String getLetraSala() {
        return letraSala;
    }

    public void setLetraSala(String letraSala) {
        this.letraSala = letraSala;
    }
}
