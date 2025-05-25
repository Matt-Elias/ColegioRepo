package com.example.ColegioProyect.Grado_grupo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "gradoGrupo")
public class GradoGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGradoGrupo;

    @Column(name = "gradoGrupo", columnDefinition = "VARCHAR(20)")
    private String gradoGrupo;



}
