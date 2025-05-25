package com.example.ColegioProyect.Niveles.Model;

import jakarta.persistence.*;

import java.util.Date;

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

    public Nivel() {}

    public Nivel(String nombreNivelAcademico, Date cicloEscolar) {
        this.nombreNivelAcademico = nombreNivelAcademico;
        this.cicloEscolar = cicloEscolar;
    }

    public Nivel(Long idNivel, String nombreNivelAcademico, Date cicloEscolar) {
        this.idNivel = idNivel;
        this.nombreNivelAcademico = nombreNivelAcademico;
        this.cicloEscolar = cicloEscolar;
    }

    public Long getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Long idNivel) {
        this.idNivel = idNivel;
    }

    public String getNombreNivelAcademico() {
        return nombreNivelAcademico;
    }

    public void setNombreNivelAcademico(String nombreNivelAcademico) {
        this.nombreNivelAcademico = nombreNivelAcademico;
    }

    public Date getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(Date cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

}
