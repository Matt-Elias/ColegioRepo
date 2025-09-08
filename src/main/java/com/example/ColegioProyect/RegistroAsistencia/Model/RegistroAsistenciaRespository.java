package com.example.ColegioProyect.RegistroAsistencia.Model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroAsistenciaRespository extends JpaRepository<RegistroAsistencia, Long> {
    @Query(value="" +
            "select registro from registro_asistencia;", nativeQuery = true)
    Optional<RegistroAsistencia> findByAsistencia(String registro);

    /*@Query(value = "select ra.*, u.*, e.*\n" +
            "from registro_asistencia ra\n" +
            "join usuario u on ra.usuario_id = u.id_usuario\n" +
            "join estudiante e on ra.estudiante_id = e.id_estudiante\n" +
            "order by fecha_hora desc;", nativeQuery = true)
    List<Object[]> findRegistroAsistenciaByActually();*/

    @Query(value = "select ra.* from registro_asistencia ra\n" +
            "join usuario u on ra.usuario_id = u.id_usuario\n" +
            "join estudiante e on ra.estudiante_id = e.id_estudiante\n" +
            "order by id_registro_asistencia desc;", nativeQuery = true)
        List<RegistroAsistencia> findRegistroAsistenciaByActually();

}
