package com.example.ColegioProyect.Usuarios.Model;

import com.example.ColegioProyect.Estudiantes.Model.Estudiante;
import com.example.ColegioProyect.Eventos.Model.Evento;
import com.example.ColegioProyect.Padres.Model.Padre;
import com.example.ColegioProyect.Profesores.Model.Profesor;
import com.example.ColegioProyect.Roles.Role;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "fecha_alta", insertable = false , columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @OneToOne
    private Estudiante estudiante;

    @OneToOne
    private Evento evento;

    @OneToOne
    private Padre padre;

    @OneToOne
    private Profesor profesor;

    public Usuario() {}

}
