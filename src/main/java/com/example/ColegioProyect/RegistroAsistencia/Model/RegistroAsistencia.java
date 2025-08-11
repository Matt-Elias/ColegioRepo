package com.example.ColegioProyect.RegistroAsistencia.Model;


import com.example.ColegioProyect.Estudiantes.Model.Estudiante;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name= "registroAsistencia" )
public class RegistroAsistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistroAsistencia;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "fechaHora", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant fechaHora;

    @Column(name = "registro", columnDefinition = "VARCHAR(20)")
    private String registro;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    public RegistroAsistencia(Usuario usuario, Instant fechaHora, String registro) {
        this.usuario = usuario;
        this.fechaHora = fechaHora;
        this.registro = registro;
    }

    public RegistroAsistencia(Usuario usuario, Instant fechaHora, String registro, Estudiante estudiante) {
        this.usuario = usuario;
        this.fechaHora = fechaHora;
        this.registro = registro;
        this.estudiante = estudiante;
    }

    public RegistroAsistencia() {
    }

    public Long getIdRegistroAsistencia() {
        return idRegistroAsistencia;
    }

    public void setIdRegistroAsistencia(Long idRegistroAsistencia) {
        this.idRegistroAsistencia = idRegistroAsistencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Instant getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Instant fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

}
