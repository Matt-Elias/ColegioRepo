package com.example.ColegioProyect.Eventos.Model;

import com.example.ColegioProyect.Usuarios.Model.Usuario;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @Column(name = "titulo", columnDefinition = "VARCHAR(45)")
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "VARCHAR(150)")
    private String descripcion;

    @Column(name = "fechaInicio", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant fechaInicio;

    @Column(name = "fechaFin", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant fechaFin;

    @Column(name = "colorEtiqueta", columnDefinition = "VARCHAR(12)")
    private String colorEtiqueta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Evento() {}

    public Evento(String titulo, String descripcion, String colorEtiqueta, Usuario usuario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.colorEtiqueta = colorEtiqueta;
        this.usuario = usuario;
    }

    public Evento(String titulo, String descripcion, Instant fechaInicio, Instant fechaFin, String colorEtiqueta, Usuario usuario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.colorEtiqueta = colorEtiqueta;
        this.usuario = usuario;
    }

    public Evento(Long idEvento, String titulo, String descripcion, String colorEtiqueta, Usuario usuario) {
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.colorEtiqueta = colorEtiqueta;
        this.usuario = usuario;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Instant getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Instant fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getColorEtiqueta() {
        return colorEtiqueta;
    }

    public void setColorEtiqueta(String colorEtiqueta) {
        this.colorEtiqueta = colorEtiqueta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
