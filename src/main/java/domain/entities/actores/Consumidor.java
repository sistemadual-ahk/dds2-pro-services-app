package domain.entities.actores;

import domain.entities.Persistente;

import javax.persistence.*;

@Entity
@Table(name = "consumidor")
public class Consumidor extends Persistente {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column
    private Integer usuario;

    public Consumidor(String nombre, String apellido) {
        this.init(nombre, apellido);
    }

    private void init(String nombre, String apellido) {
        this.nombre     = nombre;
        this.apellido   = apellido;
    }

    public Consumidor(){

    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
