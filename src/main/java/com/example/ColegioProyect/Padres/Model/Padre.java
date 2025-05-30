package com.example.ColegioProyect.Padres.Model;

import com.example.ColegioProyect.Conversaciones.Model.Conversacion;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "padre")
public class Padre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPadre;

    @OneToOne (mappedBy = "padre")
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "padre")
    @JsonIgnore
    private List<Conversacion> conversacion;

    public Padre() {}

    public Long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }

    public Padre(Usuario usuario) {
        this.usuario = usuario;
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
}
