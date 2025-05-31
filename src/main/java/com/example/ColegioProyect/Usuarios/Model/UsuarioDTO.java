package com.example.ColegioProyect.Usuarios.Model;

import com.example.ColegioProyect.Estudiantes.Model.Estudiante;
import com.example.ColegioProyect.Padres.Model.Padre;
import com.example.ColegioProyect.Profesores.Model.Profesor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTO {

    @NotNull(groups = {ModificarUsuario.class, CambiarStatus.class, CerrarSesion.class}, message = "Es necesario el id del usuario")
    private Long idUsuario;

    @NotBlank(groups = {RegistrarUsuario.class, ModificarUsuario.class}, message = "Es necesario el nombre")
    private String nombreCompleto;

    @NotBlank(groups = {RegistrarUsuario.class, ModificarUsuario.class, IniciarSesion.class}, message = "Es necesario un correo electronico")
    @Email(groups = {RegistrarUsuario.class, ModificarUsuario.class, IniciarSesion.class}, message = "Es necesario que el formato sea un correo electronico")
    private String correoElectronico;

    @NotBlank(groups = {RegistrarUsuario.class, ModificarUsuario.class}, message = "Es necesario un tipo de usuario")
    private String tipoUsuario;

    @NotBlank(groups = {RegistrarUsuario.class, IniciarSesion.class}, message = "Es necesario la contraseña")
    private String contrasena;

    //@NotNull(groups = {RegistrarUsuario.class, ModificarUsuario.class}, message = "Es necesario el estudiante")
    private Estudiante estudiante;

    //@NotNull(groups = {RegistrarUsuario.class, ModificarUsuario.class}, message = "Es necesario el padre")
    private Padre padre;

    //@NotNull(groups = {RegistrarUsuario.class, ModificarUsuario.class}, message = "Es necesario el profesor")
    private Profesor profesor;

    public UsuarioDTO () {}

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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
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

    public interface RegistrarUsuario {}
    public interface ModificarUsuario {}
    public interface CambiarStatus {}
    public interface IniciarSesion {}
    public interface CerrarSesion {}

}
