package domain.entities.trabajos;

import domain.entities.Persistente;
import domain.entities.actores.Consumidor;
import domain.entities.actores.Prestador;
import domain.entities.servicios.Tarea;
import domain.entities.trabajos.direccion.Direccion;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Table(name = "trabajo")
public class Trabajo extends Persistente {
    @ManyToOne
    @JoinColumn(name = "consumidor_id")
    private Consumidor consumidor;

    @ManyToOne
    @JoinColumn(name = "prestador_id")
    private Prestador prestador;

    @Column(columnDefinition = "DATE")
    private LocalDate fechaDeSolicitud;

    @Column
    private Boolean trabajoAceptado;

    @Embedded
    private Direccion direccion;

    @Column(columnDefinition = "DATE")
    private LocalDate fecha;

    @Column(columnDefinition = "TIME")
    private LocalTime horaInicio;

    @Column(columnDefinition = "TIME")
    private LocalTime horaFin;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tarea> tareas;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoTrabajo estado;

    @Column
    private Boolean finalizadoSegunConsumidor;

    @Column
    private Boolean finalizadoSegunPrestador;

    public Trabajo() {
    }

    public Trabajo(Consumidor consumidor, Prestador prestador) {
        this.consumidor       = consumidor;
        this.prestador        = prestador;
        this.fechaDeSolicitud = LocalDate.now();
        this.trabajoAceptado  = false;
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public LocalDate getFechaDeSolicitud() {
        return fechaDeSolicitud;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public EstadoTrabajo getEstado() {
        return estado;
    }

    public Boolean getFinalizadoSegunConsumidor() {
        return finalizadoSegunConsumidor;
    }

    public Boolean getFinalizadoSegunPrestador() {
        return finalizadoSegunPrestador;
    }

    public void setEstado(EstadoTrabajo estado) {
        this.estado = estado;
    }

    public Integer cantSemanasHastaFechaActual(){
        return (int) getFechaDeSolicitud().until(LocalDate.now(), ChronoUnit.WEEKS);
    }
}
