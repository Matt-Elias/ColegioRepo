package com.example.ColegioProyect.NotificacionToken.Model;

import com.example.ColegioProyect.Padres.Model.Padre;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "notificacionToken")
public class NotificacionToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificacionToken;

    /*@OneToMany(mappedBy = "notificacionToken")
    @JsonIgnore
    private List<Usuario> usuario;*/

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "claveOneSignalId", columnDefinition = "VARCHAR(255)")
    private String claveOneSignalId;

    @Column(name = "tipoDispositivo", columnDefinition = "VARCHAR(50)")
    private String tipoDispositivo;

    @Column(name = "fechaRegistro", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant fechaRegistro;

    @Column(name = "activo", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean activo;

    public NotificacionToken () {}

    public NotificacionToken(Usuario usuario, String claveOneSignalId, String tipoDispositivo, Instant fechaRegistro, boolean activo) {
        this.usuario = usuario;
        this.claveOneSignalId = claveOneSignalId;
        this.tipoDispositivo = tipoDispositivo;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }

    public NotificacionToken(Long idNotificacionToken, Usuario usuario, String claveOneSignalId, String tipoDispositivo, Instant fechaRegistro, boolean activo) {
        this.idNotificacionToken = idNotificacionToken;
        this.usuario = usuario;
        this.claveOneSignalId = claveOneSignalId;
        this.tipoDispositivo = tipoDispositivo;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }

    public Long getIdNotificacionToken() {
        return idNotificacionToken;
    }

    public void setIdNotificacionToken(Long idNotificacionToken) {
        this.idNotificacionToken = idNotificacionToken;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
