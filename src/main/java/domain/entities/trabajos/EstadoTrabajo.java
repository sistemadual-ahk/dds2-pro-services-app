package domain.entities.trabajos;

import domain.entities.Persistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "estado_trabajo")
public class EstadoTrabajo extends Persistente {
    @Column
    private String nombre;

    public EstadoTrabajo() {
    }

    public EstadoTrabajo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}