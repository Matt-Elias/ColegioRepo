package com.example.ColegioProyect.RegistroAsistencia.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class RegistroAsistenciaDTO {

    @NotNull(groups = {}, message = "Es necesario el Id del la asistencia")
    private Long idRegistroAsistencia;

    @NotBlank(groups = {RegistrarAsistencia.class}, message = "La asistencia no puede estar vacia")
    private String registro;

    private Instant fechaHora;

    private Long idUsuario;

    private Long idEstudiante;

    public RegistroAsistenciaDTO() {}

    public Long getIdRegistroAsistencia() {
        return idRegistroAsistencia;
    }

    public void setIdRegistroAsistencia(Long idRegistroAsistencia) {
        this.idRegistroAsistencia = idRegistroAsistencia;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public Instant getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Instant fechaInicio) {
        this.fechaHora = fechaHora;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public interface RegistrarAsistencia {}

}
