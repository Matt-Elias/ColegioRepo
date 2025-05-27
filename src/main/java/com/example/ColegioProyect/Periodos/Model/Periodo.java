package com.example.ColegioProyect.Periodos.Model;

import com.example.ColegioProyect.Niveles.Model.Nivel;
import jakarta.persistence.*;

@Entity
@Table (name = "periodo")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeriodo;

    @Column(name = "calificaciones", columnDefinition = "VARCHAR(15)")
    private String calificaciones;

    @Column(name = "asistencia", columnDefinition = "VARCHAR(15)")
    private String asistencia;

    @Column(name = "tareas", columnDefinition = "VARCHAR(15)")
    private String tareas;

    @Column(name = "proyectos", columnDefinition = "VARCHAR(15)")
    private String proyectos;

    @Column(name = "tipoPeriodo", columnDefinition = "VARCHAR(20)")
    private String tipoPeriodo;

    @OneToOne
    private Nivel nivel;

    public Periodo() {}

    public Periodo(String calificaciones, String asistencia, String tareas, String proyectos, String tipoPeriodo, Nivel nivel) {
        this.calificaciones = calificaciones;
        this.asistencia = asistencia;
        this.tareas = tareas;
        this.proyectos = proyectos;
        this.tipoPeriodo = tipoPeriodo;
        this.nivel = nivel;
    }

    public Periodo(Long idPeriodo, String calificaciones, String asistencia, String tareas, String proyectos, String tipoPeriodo, Nivel nivel) {
        this.idPeriodo = idPeriodo;
        this.calificaciones = calificaciones;
        this.asistencia = asistencia;
        this.tareas = tareas;
        this.proyectos = proyectos;
        this.tipoPeriodo = tipoPeriodo;
        this.nivel = nivel;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(String calificaciones) {
        this.calificaciones = calificaciones;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getTareas() {
        return tareas;
    }

    public void setTareas(String tareas) {
        this.tareas = tareas;
    }

    public String getProyectos() {
        return proyectos;
    }

    public void setProyectos(String proyectos) {
        this.proyectos = proyectos;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

}
