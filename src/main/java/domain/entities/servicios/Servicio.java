package domain.entities.servicios;

import domain.entities.Persistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "servicio")
public class Servicio extends Persistente {
    @Column
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id")
    private List<Tarea> tareas;

    public Servicio() {
    }

    public Servicio(String nombre) {
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void agregarTareas(Tarea ... tareas){
        Collections.addAll(this.tareas, tareas);
    }
}
