package com.example.ColegioProyect.Periodos.Model;

import jakarta.persistence.*;

@Entity
@Table (name = "periodo")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeriodo;

    @Column(name = "calificaciones", columnDefinition = "VARCHAR(15)")
    private String calificaciones;

    @Column(name = "asistencia", columnDefinition = "VARCHAR(15)")
    private String asistencia;

    @Column(name = "tareas", columnDefinition = "VARCHAR(15)")
    private String tareas;

    @Column(name = "proyectos", columnDefinition = "VARCHAR(15)")
    private String proyectos;

    @Column(name = "tipoPeriodo", columnDefinition = "VARCHAR(20)")
    private String tipoPeriodo;

    public Periodo() {}

}
