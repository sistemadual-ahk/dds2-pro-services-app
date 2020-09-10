package domain.entities.actores;

import domain.converters.DiaDeLaSemanaConverter;
import domain.entities.Persistente;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "disponibilidad")
public class Disponibilidad extends Persistente {
    @Column(columnDefinition = "TIME")
    private LocalTime horaInicio;

    @Column(columnDefinition = "TIME")
    private LocalTime horaFin;

    @Convert(converter = DiaDeLaSemanaConverter.class)
    private DayOfWeek dia;

    public Disponibilidad() {
    }

    public Disponibilidad(LocalTime horaInicio, LocalTime horaFin, DayOfWeek dia) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dia = dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public DayOfWeek getDia() {
        return dia;
    }
}