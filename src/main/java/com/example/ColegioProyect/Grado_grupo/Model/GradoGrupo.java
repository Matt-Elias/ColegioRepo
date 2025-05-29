package com.example.ColegioProyect.Grado_grupo.Model;

import com.example.ColegioProyect.Estudiantes.Model.Estudiante;
import com.example.ColegioProyect.Niveles.Model.Nivel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "gradoGrupo")
public class GradoGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGradoGrupo;

    @Column(name = "gradoGrupo", columnDefinition = "VARCHAR(20)")
    private String gradoGrupo;

    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    @ManyToOne
    private Nivel nivel;

    @OneToMany(mappedBy = "gradoGrupo")
    @JsonIgnore
    private List<Estudiante> estudiante;

    public GradoGrupo() {}

    public GradoGrupo(String gradoGrupo, boolean status, Nivel nivel) {
        this.gradoGrupo = gradoGrupo;
        this.status = status;
        this.nivel = nivel;
    }

    public GradoGrupo(Long idGradoGrupo, String gradoGrupo, boolean status, Nivel nivel) {
        this.idGradoGrupo = idGradoGrupo;
        this.gradoGrupo = gradoGrupo;
        this.status = status;
        this.nivel = nivel;
    }

    public Long getIdGradoGrupo() {
        return idGradoGrupo;
    }

    public void setIdGradoGrupo(Long idGradoGrupo) {
        this.idGradoGrupo = idGradoGrupo;
    }

    public String getGradoGrupo() {
        return gradoGrupo;
    }

    public void setGradoGrupo(String gradoGrupo) {
        this.gradoGrupo = gradoGrupo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public List<Estudiante> getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(List<Estudiante> estudiante) {
        this.estudiante = estudiante;
    }

}
