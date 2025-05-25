package com.example.ColegioProyect.Estudiantes.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstudiante;

    @Column(name = "matricula", columnDefinition = "VARCHAR(20)")
    private String matricula;

    @Column(name = "tipo", columnDefinition = "VARCHAR(15)")
    private String tipo;



}
