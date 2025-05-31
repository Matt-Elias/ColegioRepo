package com.example.ColegioProyect.Usuarios.Model;

import com.example.ColegioProyect.Estudiantes.Model.Estudiante;
import com.example.ColegioProyect.Eventos.Model.Evento;
import com.example.ColegioProyect.Padres.Model.Padre;
import com.example.ColegioProyect.Profesores.Model.Profesor;
import com.example.ColegioProyect.Roles.Rol;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nombreCompleto", columnDefinition = "VARCHAR(45)")
    private String nombreCompleto;

    @Column(nullable = false, unique = true)
    private String correoElectronico;

    @Column(name = "tipoUsuario", columnDefinition = "VARCHAR(30)")
    private String tipoUsuario;

    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    @Column(name = "contrasena", columnDefinition = "VARCHAR(45)")
    private String contrasena;

    @Column(name = "contrasena", columnDefinition = "VARCHAR(70)")
    private String urlImagen;

    //APARTADO PARA ROL
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Rol> roles = new HashSet<>();

    @Column(name = "fecha_alta", insertable = false , columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    //@JsonManagedReference
    private Estudiante estudiante;

    @OneToOne
    private Evento evento;

    //Con cascadeType hara que las operaciones aplicadas a una entidad se progaguen automaticamente a sus entidades relacionadas
    @OneToOne(mappedBy = "usuario")
    //@JsonManagedReference
    private Padre padre;

    @OneToOne(mappedBy = "usuario")
    @JsonManagedReference
    private Profesor profesor;

    public Usuario() {}

    public Usuario(String nombreCompleto, String correoElectronico, String tipoUsuario, boolean status, String contrasena, String urlImagen) {
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.tipoUsuario = tipoUsuario;
        this.status = status;
        this.contrasena = contrasena;
        this.urlImagen = urlImagen;
    }

    public Usuario(Long idUsuario, String nombreCompleto, String correoElectronico, String tipoUsuario, boolean status, String contrasena, String urlImagen) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.tipoUsuario = tipoUsuario;
        this.status = status;
        this.contrasena = contrasena;
        this.urlImagen = urlImagen;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Padre getPadre() {
        return padre;
    }

    public void setPadre(Padre padre) {
        this.padre = padre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

}
