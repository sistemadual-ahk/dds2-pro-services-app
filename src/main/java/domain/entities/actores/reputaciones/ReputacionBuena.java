package domain.entities.actores.reputaciones;

import domain.entities.actores.Prestador;
import domain.entities.trabajos.calificaciones.Calificacion;
import domain.entities.trabajos.Trabajo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("buena")
public class ReputacionBuena extends Reputacion {

    public ReputacionBuena(Prestador prestador) {
        super(prestador);
    }

    @Override
    public void recibirCalificacion(Calificacion calificacion) {

    }

    @Override
    public void serContratado(Trabajo trabajo) {
        super.prestador.agregarTrabajo(trabajo);
    }
}
