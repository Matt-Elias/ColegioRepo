package com.example.ColegioProyect.Padres.Model;

import com.example.ColegioProyect.Conversaciones.Model.Conversacion;
import com.example.ColegioProyect.Estudiantes.Model.Estudiante;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "padre")
public class Padre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPadre;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_padre")
    @JsonBackReference
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    private Estudiante estudiante;

    @OneToMany(mappedBy = "padre")
    @JsonIgnore
    private List<Conversacion> conversacion;

    public Padre() {}

    public Padre(Usuario usuario, Estudiante estudiante) {
        this.usuario = usuario;
        this.estudiante = estudiante;
    }

    public Padre(Usuario usuario){
       this.usuario = usuario;
    }

    public Padre(Long idPadre, Usuario usuario, Estudiante estudiante) {
        this.idPadre = idPadre;
        this.usuario = usuario;
        this.estudiante = estudiante;
    }

    public Long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Conversacion> getConversacion() {
        return conversacion;
    }

    public void setConversacion(List<Conversacion> conversacion) {
        this.conversacion = conversacion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

}
