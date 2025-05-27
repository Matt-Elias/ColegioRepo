package com.example.ColegioProyect.Idiomas.Model;

import com.example.ColegioProyect.Materias.Model.Materia;
import com.example.ColegioProyect.Niveles.Model.Nivel;
import jakarta.persistence.*;

@Entity
@Table(name = "idioma")
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIdioma;

    @Column(name = "nivelIdioma", columnDefinition = "VARCHAR(45)")
    private String nivelIdioma;

    @Column(name = "metodoCalificacion", columnDefinition = "VARCHAR(45)")
    private String metodoCalificacion;

    @OneToOne
    private Nivel nivel;

    @OneToOne
    private Materia materia;

    public Idioma() {}

}
