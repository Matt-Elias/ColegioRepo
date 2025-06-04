package com.example.ColegioProyect.Materias.Model;

import com.example.ColegioProyect.Profesores.Model.Profesor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MateriaDTO {
    @NotNull(groups = {ModificarMateria.class}, message = "Es necesario el id del usuario")
    private Long idMateria;

    @NotBlank(groups = {RegistrarMateria.class, ModificarMateria.class}, message = "Es necesario el nombre de la materia")
    private String nombreMateria;

    @NotNull(groups = {RegistrarMateria.class, ModificarMateria.class}, message = "Es necesario una asignacion")
    private int asignaciones;

    @NotNull
    private Profesor profesor;
    //private Long idUsuario;

    public interface RegistrarMateria {}
    public interface ModificarMateria {}
    public interface EliminarMateria {}

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(int asignaciones) {
        this.asignaciones = asignaciones;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

}
