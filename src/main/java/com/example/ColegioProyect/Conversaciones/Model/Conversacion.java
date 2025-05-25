package com.example.ColegioProyect.Conversaciones.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "conversacion")
public class Conversacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConversacion;

    @Column(name = "fechaCreacion", insertable = false ,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ultimoMensaje", insertable = false ,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoMensaje;

    public Conversacion() {}


}
