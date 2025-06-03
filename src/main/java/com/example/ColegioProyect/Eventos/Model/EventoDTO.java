package com.example.ColegioProyect.Eventos.Model;

import com.example.ColegioProyect.Usuarios.Model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EventoDTO {
    @NotNull(groups = {ModificarEvento.class},message = "Es necesario el id del evento")
    private Long idEvento;

    @NotBlank(groups = {RegistrarEvento.class, ModificarEvento.class}, message = "Es necesario el titulo del evento")
    private String titulo;

    private String descripcion;

    @NotBlank(groups = {RegistrarEvento.class, ModificarEvento.class}, message = "Es necesario un color de etiqueta")
    private String colorEtiqueta;

    public interface RegistrarEvento{}
    public interface ModificarEvento{}
    public interface CancelarEvento{}

    private Long idUsuario;

    public EventoDTO() {}

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColorEtiqueta() {
        return colorEtiqueta;
    }

    public void setColorEtiqueta(String colorEtiqueta) {
        this.colorEtiqueta = colorEtiqueta;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

}
