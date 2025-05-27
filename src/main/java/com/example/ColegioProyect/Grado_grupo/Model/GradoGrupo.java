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

    @ManyToOne
    private Nivel nivel;

    @OneToMany(mappedBy = "gradoGrupo")
    @JsonIgnore
    private List<Estudiante> estudiante;



}
