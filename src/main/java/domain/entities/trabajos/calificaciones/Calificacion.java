package domain.entities.trabajos.calificaciones;

import domain.entities.Persistente;
import domain.entities.trabajos.Trabajo;

import javax.persistence.*;

@Entity
@Table(name = "calificacion")
public class Calificacion extends Persistente {
    @Column
    private Integer estrellas;

    @Column
    private String opinionLibre;

    @OneToOne
    @JoinColumn(name = "trabajo_id")
    private Trabajo trabajo;

    public Calificacion() {
    }

    public Calificacion(Integer estrellas, Trabajo trabajo) {
        this.estrellas = estrellas;
        this.trabajo = trabajo;
    }

    public void setOpinionLibre(String opinionLibre) {
        this.opinionLibre = opinionLibre;
    }

    public String getOpinionLibre() {
        return opinionLibre;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public Integer getEstrellas() {
        return estrellas;
    }
}