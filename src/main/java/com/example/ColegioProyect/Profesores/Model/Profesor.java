package com.example.ColegioProyect.Profesores.Model;

import com.example.ColegioProyect.Conversaciones.Model.Conversacion;
import com.example.ColegioProyect.Materias.Model.Materia;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfesor;

    @OneToOne(mappedBy = "profesor")
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "profesor")
    @JsonIgnore
    private List<Materia> materia;

    @OneToMany(mappedBy = "profesor")
    @JsonIgnore
    private List<Conversacion> conversacion;

    public Profesor() {}

    public Long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Materia> getMateria() {
        return materia;
    }

    public void setMateria(List<Materia> materia) {
        this.materia = materia;
    }

    public List<Conversacion> getConversacion() {
        return conversacion;
    }

    public void setConversacion(List<Conversacion> conversacion) {
        this.conversacion = conversacion;
    }

}
