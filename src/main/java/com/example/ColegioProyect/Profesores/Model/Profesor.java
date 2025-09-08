package com.example.ColegioProyect.Profesores.Model;

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

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_profesor")
    //@JsonBackReference
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "profesor")
    @JsonIgnore
    private List<Materia> materia;

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

}
