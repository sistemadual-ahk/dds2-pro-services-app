package domain.entities.servicios;

import domain.entities.Persistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tarea")
public class Tarea extends Persistente {
    @Column
    private String descripcion;

    public Tarea() {
    }

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}