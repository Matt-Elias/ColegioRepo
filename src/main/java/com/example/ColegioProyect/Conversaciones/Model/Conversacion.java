package com.example.ColegioProyect.Conversaciones.Model;

import com.example.ColegioProyect.Mensajes.Model.Mensaje;
import com.example.ColegioProyect.Padres.Model.Padre;
import com.example.ColegioProyect.Profesores.Model.Profesor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

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

    @ManyToOne
    private Padre padre;

    @OneToMany(mappedBy = "conversaciones")
    @JsonIgnore
    private List<Mensaje> mensaje;

    @ManyToOne
    private Profesor profesor;

    public Conversacion() {}

}
