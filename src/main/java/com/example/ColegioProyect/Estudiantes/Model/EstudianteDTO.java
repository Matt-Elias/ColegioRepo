package com.example.ColegioProyect.Estudiantes.Model;

import com.example.ColegioProyect.Grado_grupo.Model.GradoGrupo;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EstudianteDTO {

    @NotNull(groups = {RegistrarEstudiante.class, ModificarEstudiante.class}, message = "Es necesario el id del estudiante")
    private Long idEstudiante;

    @NotBlank(groups = {RegistrarMatricula.class, ModificarMatricula.class}, message = "Es necesario la matricula del estudiante")
    private String matricula;

    @NotBlank(groups = {RegistrarTipo.class, ModificarTipo.class}, message = "Es necesario el tipo")
    private String tipo;

    @NotNull(groups = {RegistrarEstudiante.class, ModificarEstudiante.class}, message = "Es necesario el grado y el grupo")
    private GradoGrupo gradoGrupo;

    @NotNull(groups = {RegistrarEstudiante.class, ModificarEstudiante.class}, message = "Es necesario el usuario")
    private Usuario usuario;

    public EstudianteDTO() {}

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public GradoGrupo getGradoGrupo() {
        return gradoGrupo;
    }

    public void setGradoGrupo(GradoGrupo gradoGrupo) {
        this.gradoGrupo = gradoGrupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public interface RegistrarEstudiante {}
    public interface ModificarEstudiante {}
    public interface RegistrarMatricula {}
    public interface ModificarMatricula {}
    public interface RegistrarTipo {}
    public interface ModificarTipo {}

}
