package com.example.ColegioProyect.Usuarios.Model;

import com.example.ColegioProyect.Estudiantes.Model.Estudiante;
import com.example.ColegioProyect.Padres.Model.Padre;
import com.example.ColegioProyect.Profesores.Model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT * FROM usuario WHERE correo_electronico LIKE ?1 AND id_usuario != ?2 LIMIT 1", nativeQuery = true)
    Optional<Usuario> searchByCorreoElectronico(String correo_electronico, Long id_usuario);

    @Query(value = "SELECT * FROM usuario WHERE tipo_usuario like 'Profesor%'", nativeQuery = true)
    Optional<Usuario> searchByTipoUsuarioAndProfesor(String tipo_usuario);

    @Query(value = "SELECT " +
            "u_padre.id_usuario AS padre_id, u_padre.nombre_completo AS padre_nombre, u_padre.correo_electronico AS padre_correo, " +
            "p.id_padre, " +
            "e.id_estudiante, e.matricula, " +
            "u_estudiante.id_usuario AS estudiante_id, u_estudiante.nombre_completo AS estudiante_nombre, u_estudiante.correo_electronico AS estudiante_correo " +
            "FROM usuario u_padre " +
            "JOIN padre p ON u_padre.id_usuario = p.id_padre " +
            "JOIN estudiante e ON p.estudiante_id_estudiante = e.id_estudiante " +
            "JOIN usuario u_estudiante ON e.id_estudiante = u_estudiante.id_usuario",
            nativeQuery = true)
    List<Object[]> findPadresConEstudiantesRaw();

    @Query(value = "SELECT " +
            "u_estudiante.id_usuario AS idEstudiante, " +
            "u_estudiante.nombre_completo AS nombreEstudiante, " +
            "u_estudiante.correo_electronico AS correoEstudiante, " +
            "u_padre.id_usuario AS idPadre, " +
            "u_padre.nombre_completo AS nombrePadre, " +
            "u_padre.correo_electronico AS correoPadre " +
            "FROM usuario u_estudiante " +
            "JOIN estudiante e ON u_estudiante.id_usuario = e.id_estudiante " +
            "JOIN padre p ON e.id_estudiante = p.estudiante_id_estudiante " +
            "JOIN usuario u_padre ON p.id_padre = u_padre.id_usuario",
            nativeQuery = true)
    List<Object[]> findEstudiantesConPadresRaw();

}
