package domain.entities.servicios;

import domain.entities.Persistente;
import domain.entities.actores.Prestador;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "servicio_ofrecido")
public class ServicioOfrecido extends Persistente {
    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "prestador_id")
    private Prestador prestador;

    @Column
    private String experiencia;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "foto", joinColumns = @JoinColumn(name = "servicioOfrecido_id"))
    @Column(name = "foto")
    private List<String> fotos;

    public ServicioOfrecido() {
    }

    public ServicioOfrecido(Servicio servicio, Prestador prestador) {
        this.servicio   = servicio;
        this.prestador  = prestador;
        this.fotos      = new ArrayList<>();
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void agregarFotos(String ... fotos){
        Collections.addAll(this.fotos, fotos);
    }
}