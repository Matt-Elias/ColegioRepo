package com.example.ColegioProyect.Grado_grupo.Model;

import com.example.ColegioProyect.Niveles.Model.Nivel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GradoGrupoDTO {

    @NotNull(groups = {ModificarGradoGrupo.class, CambiarStatusGradoGrupo.class}, message = "Es necesario el id del grado y grupo")
    private Long idGradoGrupo;

    @NotBlank(groups = {RegistradGradoGrupo.class, ModificarGradoGrupo.class}, message = "Es necesario el grado y grupo")
    private String gradoGrupo;

    @NotNull(groups = {RegistradGradoGrupo.class, ModificarGradoGrupo.class}, message = "Es necesario el objeto de nivel")
    private Nivel nivel;

    public GradoGrupoDTO() {}

    public Long getIdGradoGrupo() {
        return idGradoGrupo;
    }

    public void setIdGradoGrupo(Long idGradoGrupo) {
        this.idGradoGrupo = idGradoGrupo;
    }

    public String getGradoGrupo() {
        return gradoGrupo;
    }

    public void setGradoGrupo(String gradoGrupo) {
        this.gradoGrupo = gradoGrupo;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public interface RegistradGradoGrupo {}
    public interface ModificarGradoGrupo {}
    public interface CambiarStatusGradoGrupo {}

}
