package com.example.ColegioProyect.Niveles.Model;

import com.example.ColegioProyect.Grado_grupo.Model.GradoGrupo;
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

    @Column(name = "nivelAcademico", columnDefinition = "VARCHAR(60)")
    private String nivelAcademico;

    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    @Column(name = "cicloEscolar", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cicloEscolar;

    @OneToOne(mappedBy = "nivel")
    @JsonIgnore
    private Periodo periodo;

    @OneToMany(mappedBy = "nivel")
    @JsonIgnore
    private List<GradoGrupo> gradoGrupo;

    public Nivel() {}

    public Nivel(String nivelAcademico, boolean status) {
        this.nivelAcademico = nivelAcademico;
        this.status = status;
    }

    public Nivel(Long idNivel, String nivelAcademico, boolean status) {
        this.idNivel = idNivel;
        this.nivelAcademico = nivelAcademico;
        this.status = status;
    }

    public Long getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Long idNivel) {
        this.idNivel = idNivel;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(Date cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public List<GradoGrupo> getGradoGrupo() {
        return gradoGrupo;
    }

    public void setGradoGrupo(List<GradoGrupo> gradoGrupo) {
        this.gradoGrupo = gradoGrupo;
    }

}
