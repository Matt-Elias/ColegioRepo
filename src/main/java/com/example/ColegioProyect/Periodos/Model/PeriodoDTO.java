package com.example.ColegioProyect.Periodos.Model;

import com.example.ColegioProyect.Niveles.Model.Nivel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PeriodoDTO {

    @NotNull(groups = {ModificarPeriodo.class}, message = "Es necesario el id del periodo")
    private Long idPeriodo;

    @NotBlank(groups = {RegistrarPeriodo.class, ModificarPeriodo.class}, message = "Es necesario las calificaciones")
    private String calificaciones;

    @NotBlank(groups = {RegistrarPeriodo.class, ModificarPeriodo.class}, message = "Es necesario la asistencia")
    private String asistencia;

    @NotBlank(groups = {RegistrarPeriodo.class, ModificarPeriodo.class}, message = "Es necesario las tareas")
    private String tareas;

    @NotBlank(groups = {RegistrarPeriodo.class, ModificarPeriodo.class}, message = "Es necesario los proyectos")
    private String proyectos;

    @NotBlank(groups = {RegistrarPeriodo.class, ModificarPeriodo.class}, message = "Es necesario el tipo de periodo")
    private String tipoPeriodo;

    @NotNull(groups = {RegistrarPeriodo.class, ModificarPeriodo.class}, message = "Es necesario el nivel")
    private Nivel nivel;

    public PeriodoDTO () {}

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

    public interface RegistrarPeriodo {}
    public interface ModificarPeriodo {}

}
