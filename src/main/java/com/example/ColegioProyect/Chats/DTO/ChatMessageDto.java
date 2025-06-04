package com.example.ColegioProyect.Chats.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class ChatMessageDto {
    private Long idConversacion;

    @NotNull(message = "El remitente no puede ser nullo")
    private Long idRemitente;

    @NotNull(message = "El destinatario no puede ser nullo")
    private Long idDestinatario;

    @NotBlank(message = "El contenido no puede estar vacio")
    private String contenido;

    private Date enviadoEn;
    private Boolean leido;
    private String tipoUsuario;

    public Long getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Long idConversacion) {
        this.idConversacion = idConversacion;
    }

    public Long getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(Long idRemitente) {
        this.idRemitente = idRemitente;
    }

    public Long getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(Long idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getEnviadoEn() {
        return enviadoEn;
    }

    public void setEnviadoEn(Date enviadoEn) {
        this.enviadoEn = enviadoEn;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
