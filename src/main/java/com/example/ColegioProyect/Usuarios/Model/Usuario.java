package com.example.ColegioProyect.Usuarios.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nombreCompleto", columnDefinition = "VARCHAR(45)")
    private String nombreCompleto;

    @Column(name = "correoElectronico", columnDefinition = "VARCHAR(30)")
    private String correoElectronico;

    @Column(name = "tipoUsuario", columnDefinition = "VARCHAR(30)")
    private String tipoUsuario;

    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    @Column(name = "contrasena", columnDefinition = "VARCHAR(45)")
    private String contrasena;

    //APARTADO PARA ROL

    @Column(name = "fecha_alta", insertable = false , columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    public Usuario() {}

}
