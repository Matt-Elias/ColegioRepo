package com.example.ColegioProyect.Estudiantes.Model;

import com.example.ColegioProyect.Grado_grupo.Model.GradoGrupo;
import com.example.ColegioProyect.Padres.Model.Padre;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstudiante;

    @Column(name = "matricula", columnDefinition = "VARCHAR(20)")
    private String matricula;

    @Column(name = "tipo", columnDefinition = "VARCHAR(15)")
    private String tipo;

    @ManyToOne
    private GradoGrupo gradoGrupo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_estudiante")
    @JsonIgnore
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "estudiante")
    //@JoinColumn(name = "estudiante_padre", referencedColumnName = "id_estudiante")
    @JsonIgnore
    private List<Padre> padre;

    public Estudiante () {}

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

    public List<Padre> getPadre() {
        return padre;
    }

    public void setPadre(List<Padre> padre) {
        this.padre = padre;
    }

}
