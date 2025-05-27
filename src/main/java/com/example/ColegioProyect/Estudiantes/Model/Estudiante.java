package com.example.ColegioProyect.Estudiantes.Model;

import com.example.ColegioProyect.Grado_grupo.Model.GradoGrupo;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
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

    @ManyToOne
    private GradoGrupo gradoGrupo;

    @OneToOne(mappedBy = "estudiante")
    private Usuario usuario;

    public Estudiante () {}



}
