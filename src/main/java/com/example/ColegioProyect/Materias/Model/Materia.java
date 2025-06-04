package com.example.ColegioProyect.Materias.Model;

import com.example.ColegioProyect.Profesores.Model.Profesor;
import jakarta.persistence.*;

@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMateria;

    @Column(name = "nombreMateria", columnDefinition = "VARCHAR(45)")
    private String nombreMateria;

    @Column(name = "asignaciones", columnDefinition = "INT")
    private int asignaciones;

    @ManyToOne
    private Profesor profesor;

    public Materia() {}

    public Materia(String nombreMateria, int asignaciones, Profesor profesor) {
        this.nombreMateria = nombreMateria;
        this.asignaciones = asignaciones;
        this.profesor = profesor;
    }

    public Materia(int idMateria, String nombreMateria, int asignaciones, Profesor profesor) {
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.asignaciones = asignaciones;
        this.profesor = profesor;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(int asignaciones) {
        this.asignaciones = asignaciones;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
