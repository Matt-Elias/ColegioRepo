package com.example.ColegioProyect.NotificacionToken.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class NotificacionTokenDTO {

    @NotNull(groups = {}, message = "Es necesario el Id del la nototificacion del token")
    private Long idNotificacionToken;

    Long idUsuario;

    @NotBlank(groups = {RegistrarToken.class}, message = "La clave del id de OneSigna no puede estar vacia")
    private String claveOneSignalId;

    @NotBlank(groups = {RegistrarToken.class}, message = "El tipo de disposito mobil no puede estar vacio")
    private String tipoDispositivo;

    private Instant fechaRegistro;

    public NotificacionTokenDTO() {}

    public Long getIdNotificacionToken() {
        return idNotificacionToken;
    }

    public void setIdNotificacionToken(Long idNotificacionToken) {
        this.idNotificacionToken = idNotificacionToken;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getClaveOneSignalId() {
        return claveOneSignalId;
    }

    public void setClaveOneSignalId(String claveOneSignalId) {
        this.claveOneSignalId = claveOneSignalId;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public Instant getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Instant fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public interface RegistrarToken {}

}
