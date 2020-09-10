package domain.entities.actores.reputaciones;

import domain.entities.actores.Prestador;
import domain.entities.actores.reputaciones.verificador.VerificadorDeContratacion;
import domain.entities.trabajos.calificaciones.Calificacion;
import domain.entities.trabajos.Trabajo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@DiscriminatorValue("regular")
public class ReputacionRegular extends Reputacion {
    private static Integer minimoDeEstrellas = 4;
    private static Integer cantSeguidasRequeridas = 4;
    private static Integer periodoDeMaximosEnSemanas = 1;
    private static Integer maxCantVecesPorPeriodo    = 4;

    @Column
    private Integer cantidadDeBuenasEstrellas;

    @Column(columnDefinition = "DATE")
    private LocalDate fechaUltimaContratacion;

    @Column
    private Integer cantVecesContratadoEnPeriodo;

    public ReputacionRegular(Prestador prestador) {
        super(prestador);
        this.cantidadDeBuenasEstrellas      = 0;
        this.cantVecesContratadoEnPeriodo   = 0;
    }

    @Override
    public void recibirCalificacion(Calificacion calificacion) {
        this.cantidadDeBuenasEstrellas =
                calificacion.getEstrellas() >= minimoDeEstrellas?
                        this.cantidadDeBuenasEstrellas +1
                        : 0;
        this.cambiarEstadoSiEsNecesario();
    }

    @Override
    public void serContratado(Trabajo trabajo) {
        if(puedeSerContratado()) {
            super.prestador.agregarTrabajo(trabajo);
            this.fechaUltimaContratacion = trabajo.getFechaDeSolicitud();
            actualizarContador();
        }
        else throw new RuntimeException("El prestador no puede ser contratado más de " + maxCantVecesPorPeriodo+ " veces por cada "+ periodoDeMaximosEnSemanas+" semanas.");
    }

    private void actualizarContador(){
        this.cantVecesContratadoEnPeriodo = crearVerificador().valorParaContadorDeContrataciones(cantSemanasDesdeUltimaContratacion());
    }


    private Boolean puedeSerContratado(){
        return crearVerificador().prestadorPuedeSerContratado(cantSemanasDesdeUltimaContratacion());
    }

    private VerificadorDeContratacion crearVerificador(){
        return new VerificadorDeContratacion(periodoDeMaximosEnSemanas, maxCantVecesPorPeriodo, cantVecesContratadoEnPeriodo);
    }

    private Integer cantSemanasDesdeUltimaContratacion(){
        return (int) this.fechaUltimaContratacion.until(LocalDate.now(), ChronoUnit.WEEKS);
    }

    private void cambiarEstadoSiEsNecesario(){
        if(this.cantidadDeBuenasEstrellas >= cantSeguidasRequeridas){
            super.prestador.cambiarReputacion(new ReputacionBuena(super.prestador));
        }
    }
}
