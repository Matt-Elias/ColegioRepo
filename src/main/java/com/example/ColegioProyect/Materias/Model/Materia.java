package com.example.ColegioProyect.Materias.Model;

import com.example.ColegioProyect.Idiomas.Model.Idioma;
import com.example.ColegioProyect.Profesores.Model.Profesor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMateria;

    @Column(name = "nombreMateria", columnDefinition = "VARCHAR(45)")
    private String nombreMateria;

    @Column(name = "asignaciones", columnDefinition = "INT")
    private int asignaciones;

    @ManyToOne
    private Profesor profesor;

    @OneToOne(mappedBy = "materia")
    @JsonIgnore
    private Idioma idioma;

    public Materia() {}

}
