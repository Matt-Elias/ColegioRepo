package com.example.ColegioProyect.NotificacionToken.Model;


import com.example.ColegioProyect.Usuarios.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificacionTokenRepository extends JpaRepository<NotificacionToken, Long> {

    @Query(value = "select * from notificacion_token where usuario_id = ? and activo = true\n", nativeQuery = true)
    Optional<NotificacionToken> findTokenActivoByUsuario(Long idUsuario);

    @Query(value = "select nt.* from notificacion_token nt\n" +
            "join  usuario u on nt.usuario_id = u.id_usuario\n" +
            "join padre p on u.id_usuario = p.id_padre\n" +
            "join estudiante e on p.estudiante_id_estudiante = e.id_estudiante\n" +
            "where e.id_estudiante = ?;", nativeQuery = true)
    List<NotificacionToken> findTokensPadresByEstudiante(Long idEstudiante);

    @Query(value = "select nt.*, u.id_usuario from notificacion_token nt join usuario u on nt.usuario_id = u.id_usuario;", nativeQuery = true)
    List<NotificacionToken> findByIdUsuario(Long idUsuario);

    @Query(value = "select u.* , nt.* from notificacion_token nt\n" +
            "join usuario u\n" +
            "on nt.usuario_id = u.id_usuario;", nativeQuery = true)
    Optional<NotificacionToken> findByIdUsuarioAndTipoDispositivo(Long idUsuario, String tipoDispositivo);
}
