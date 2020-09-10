package domain.entities.actores.reputaciones;

import domain.entities.Persistente;
import domain.entities.actores.Prestador;
import domain.entities.trabajos.calificaciones.Calificacion;
import domain.entities.trabajos.Trabajo;

import javax.persistence.*;

@Entity
@Table(name = "reputacion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Reputacion extends Persistente {
    @OneToOne
    @JoinColumn(name = "prestador_id")
    protected Prestador prestador;

    public Reputacion(Prestador prestador) {
        this.prestador = prestador;
    }

    public abstract void recibirCalificacion(Calificacion calificacion);

    public abstract void serContratado(Trabajo trabajo);
}