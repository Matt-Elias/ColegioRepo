package com.example.ColegioProyect.Mensajes.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "mensaje")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMensaje;

    @Column(name = "contenido", columnDefinition = "VARCHAR(300)")
    private String contenido;

    @Column(name = "enviadoEn", insertable = false , columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" )
    @Temporal(TemporalType.TIMESTAMP)
    private Date enviadoEn;

    @Column(name = "leido", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean leido;

    public Mensaje() {}


}
