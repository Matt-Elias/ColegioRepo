package com.example.ColegioProyect.Padres.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "padre")
public class Padre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPadre;


    public Padre() {}

}
