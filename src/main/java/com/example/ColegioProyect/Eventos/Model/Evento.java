package com.example.ColegioProyect.Eventos.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @Column(name = "titulo", columnDefinition = "VARCHAR(45)")
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "VARCHAR(150)")
    private String descripcion;

    @Column (name = "fechaInicio", insertable = false ,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Column (name = "fechaFin", insertable = false ,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    @Column(name = "colorEtiqueta", columnDefinition = "VARCHAR(12)")
    private String colorEtiqueta;

    public Evento() {}




}
