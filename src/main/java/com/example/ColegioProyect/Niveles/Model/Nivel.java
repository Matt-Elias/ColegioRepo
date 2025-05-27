package com.example.ColegioProyect.Niveles.Model;

import com.example.ColegioProyect.Grado_grupo.Model.GradoGrupo;
import com.example.ColegioProyect.Idiomas.Model.Idioma;
import com.example.ColegioProyect.Periodos.Model.Periodo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nivel")
public class Nivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNivel;

    @Column(name = "nombreNivelAcademico", columnDefinition = "VARCHAR(45)")
    private String nombreNivelAcademico;

    @Column(name = "cicloEscolar", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cicloEscolar;

    @OneToOne(mappedBy = "nivel")
    @JsonIgnore
    private Periodo periodo;

    @OneToMany(mappedBy = "nivel")
    @JsonIgnore
    private List<GradoGrupo> gradoGrupo;

    @OneToOne(mappedBy = "nivel")
    @JsonIgnore
    private Idioma idioma;

    public Nivel() {}





}
