package domain.entities.actores;

import domain.entities.Persistente;
import domain.entities.actores.reputaciones.ReputacionBuena;
import domain.entities.actores.reputaciones.Reputacion;
import domain.entities.servicios.ServicioOfrecido;
import domain.entities.trabajos.calificaciones.Calificacion;
import domain.entities.trabajos.Trabajo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "prestador")
public class Prestador extends Persistente {
    @Column(name = "nombre")
    private String nombre;

    @Column
    private String apellido;

    @Column(columnDefinition = "DATE")
    private LocalDate fechaDeNacimiento;

    @Column
    private String cuitCuil;

    @Column
    private String foto;

    @Column
    private Integer usuario;

    @Enumerated(EnumType.STRING)
    private TipoDeDocumento tipoDeDocumento;

    @Column
    private Integer numeroDeIdentificacion;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "email", joinColumns = @JoinColumn(name = "prestador_id"))
    @Column(name = "email")
    private Set<String> emails;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "telefono", joinColumns = @JoinColumn(name = "prestador_id"))
    @Column(name = "telefono")
    private Set<Integer> telefonos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prestador", cascade = CascadeType.ALL)
    private List<ServicioOfrecido> serviciosOfrecidos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "prestador_id")
    private List<Disponibilidad> disponibilidades;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "prestador_id")
    private List<Calificacion> calificaciones;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "prestador")
    private List<Trabajo> trabajosRealizados;

    @OneToOne
    private Reputacion reputacion;

    public Prestador(){

    }


    public Prestador(String nombre, String apellido) {
        this.init(nombre, apellido);
    }

    private void init(String nombre, String apellido){
        this.nombre             = nombre;
        this.apellido           = apellido;
        this.serviciosOfrecidos = new ArrayList<>();
        this.disponibilidades   = new ArrayList<>();
        this.calificaciones     = new ArrayList<>();
        this.emails             = new LinkedHashSet<>();
        this.telefonos          = new LinkedHashSet<>();
        this.trabajosRealizados = new ArrayList<>();
        this.reputacion         = new ReputacionBuena(this);
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
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

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCuitCuil() {
        return cuitCuil;
    }

    public void setCuitCuil(String cuitCuil) {
        this.cuitCuil = cuitCuil;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public TipoDeDocumento getTipoDeDocumento() {
        return tipoDeDocumento;
    }

    public void setTipoDeDocumento(TipoDeDocumento tipoDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento;
    }

    public Integer getNumeroDeIdentificacion() {
        return numeroDeIdentificacion;
    }

    public void setNumeroDeIdentificacion(Integer numeroDeIdentificacion) {
        this.numeroDeIdentificacion = numeroDeIdentificacion;
    }

    public List<ServicioOfrecido> getServiciosOfrecidos() {
        return serviciosOfrecidos;
    }

    public List<Disponibilidad> getDisponibilidades() {
        return disponibilidades;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public Set<Integer> getTelefonos() {
        return telefonos;
    }

    public Reputacion getReputacion() {
        return reputacion;
    }

    public void cambiarReputacion(Reputacion reputacion){
        this.reputacion = reputacion;
    }

    public void agregarTelefonos(Integer ... telefonos){
        Collections.addAll(this.telefonos, telefonos);
    }

    public List<Trabajo> getTrabajosRealizados() {
        return trabajosRealizados;
    }

    public void agregarMails(String ... mails){
        Collections.addAll(this.emails, mails);
    }

    public void agregarTrabajo(Trabajo trabajo){
        this.trabajosRealizados.add(trabajo);
    }

    public void serContratado(Trabajo unTrabajo){
        this.reputacion.serContratado(unTrabajo);
    }

    public void agregarDisponibilidades(Disponibilidad ... disponibilidades) {
        Collections.addAll(this.disponibilidades, disponibilidades);
    }

    public void recibirCalificacion(Calificacion calificacion){
        this.calificaciones.add(calificacion);
        this.reputacion.recibirCalificacion(calificacion);
    }
}
