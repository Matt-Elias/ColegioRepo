package com.example.ColegioProyect.Security.DTO;

public class AuthResponse {
    private String token;
    private Long usuarioId;
    private String correoElectronico;
    private long expiracion;

    public AuthResponse(String token, Long usuarioId, String correoElectronico, long expiracion) {
        this.token = token;
        this.usuarioId = usuarioId;
        this.correoElectronico = correoElectronico;
        this.expiracion = expiracion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public long getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(long expiracion) {
        this.expiracion = expiracion;
    }
}
