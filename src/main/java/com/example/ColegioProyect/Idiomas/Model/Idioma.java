package com.example.ColegioProyect.Idiomas.Model;

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

    @Column(name = "conceptosPeriodo", columnDefinition = "VARCHAR(45)")
    private String conceptosPeriodo;

    public Idioma() {}

}
