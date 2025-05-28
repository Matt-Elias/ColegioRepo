package com.example.ColegioProyect.Niveles.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NivelDTO {

    @NotNull(groups = {ModificarNivel.class, CambiarStatusNivel.class}, message = "Es necesario el Id del nivel")
    private Long idNivel;

    @NotBlank(groups = {RegistrarNivel.class, ModificarNivel.class}, message = "Es nescesario el nombre del nivel academico")
    private String nivelAcademico;

    public NivelDTO() {}

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

    public interface RegistrarNivel {}
    public interface ModificarNivel{}
    public interface CambiarStatusNivel{}

}
