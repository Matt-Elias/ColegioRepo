package com.example.ColegioProyect.Usuarios.Model;

import com.example.ColegioProyect.Estudiantes.Model.Estudiante;
import com.example.ColegioProyect.Padres.Model.Padre;
import com.example.ColegioProyect.Profesores.Model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.validation.ObjectError;

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

    @Query(value = "select\n" +
            "    u_estudiante.id_usuario as \"id_usuario\",\n" +
            "    u_estudiante.contrasena as \"contrasena\",\n" +
            "    u_estudiante.correo_electronico as \"correo_electronico\",\n" +
            "    u_estudiante.fecha_alta as \"fecha_alta\",\n" +
            "    u_estudiante.status as \"estado\",\n" +
            "    u_estudiante.tipo_usuario as \"tipo_de_usuario\",\n" +
            "    e.grado_grupo_id_grado_grupo as \"id_grado_grupo\"    \n" +
            "from usuario u_estudiante\n" +
            "join estudiante e\n" +
            "on u_estudiante.id_usuario = e.id_estudiante", nativeQuery = true)
    List<Object[]>findAllByEstudiante();

    @Query(value = "select\n" +
            "    u_padre.id_usuario as \"id_usuario\",\n" +
            "    u_padre.contrasena as \"contrasena\",\n" +
            "    u_padre.correo_electronico as \"correo_electronico\",\n" +
            "    u_padre.nombre_completo as \"nombre_completo\",\n" +
            "    u_padre.fecha_alta as \"fecha_alta\",\n" +
            "    u_padre.status as \"estado\",\n" +
            "    u_padre.tipo_usuario as \"tipo_de_usuario\",\n" +
            "    u_padre.url_imagen as \"url_imagen\",\n" +
            "    p.estudiante_id_estudiante as \"id_estudiante\"\n" +
            "from usuario u_padre\n" +
            "join padre p\n" +
            "on u_padre.id_usuario = p.id_padre", nativeQuery = true)
    List<Object[]>findAllByPadre();

    @Query(value = "select\n" +
            "    u_profesor.id_usuario as \"id_usuario\",\n" +
            "    u_profesor.contrasena as \"contrasena\",\n" +
            "    u_profesor.correo_electronico as \"correo_electronico\",\n" +
            "    u_profesor.nombre_completo as \"nombre_completo\",\n" +
            "    u_profesor.fecha_alta as \"fecha_alta\",\n" +
            "    u_profesor.status as \"estado\",\n" +
            "    u_profesor.tipo_usuario as \"tipo_usuario\",\n" +
            "    u_profesor.url_imagen as \"url_imagen\",\n" +
            "    p.id_profesor as \"id_profesor\"\n" +
            "    from usuario u_profesor\n" +
            "join profesor p\n" +
            "on u_profesor.id_usuario = p.id_profesor", nativeQuery = true)
    List<Object[]>findAllByProfesor();

    @Query(value = "select\n" +
            "    u.id_usuario,\n" +
            "    u.contrasena,\n" +
            "    u.correo_electronico,\n" +
            "    u.nombre_completo,\n" +
            "    u.fecha_alta,\n" +
            "    u.status,\n" +
            "    u.tipo_usuario,\n" +
            "    u.url_imagen,\n" +
            "    p.estudiante_id_estudiante,\n" +
            "    e.grado_grupo_id_grado_grupo\n" +
            "from usuario u\n" +
            "join padre p on u.id_usuario = p.id_padre\n" +
            "join estudiante e on p.estudiante_id_estudiante = e.id_estudiante\n" +
            "where u.id_usuario = ?;" , nativeQuery = true)
    List<Object[]> findByIdUsuarioAndPadre(@Param("idUsuario") Long idUsuario);

    @Query(value = "select\n" +
            "    u.id_usuario,\n" +
            "    u.contrasena,\n" +
            "    u.correo_electronico,\n" +
            "    u.nombre_completo,\n" +
            "    u.fecha_alta,\n" +
            "    u.status,\n" +
            "    u.tipo_usuario,\n" +
            "    u.url_imagen,\n" +
            "    pr.id_profesor,\n" +
            "    m.id_materia,\n" +
            "    m.nombre_materia\n" +
            "from usuario u\n" +
            "join profesor pr on u.id_usuario = pr.id_profesor\n" +
            "join materia m on pr.id_profesor = m.profesor_id_profesor\n" +
            "where u.id_usuario = ?;", nativeQuery = true)
    List<Object[]> findByIdUsuarioAndProfesor(@Param("idUsuario") Long idUsuario);

    @Query(value= "select\n" +
            "    u.id_usuario,\n" +
            "    u.contrasena,\n" +
            "    u.correo_electronico,\n" +
            "    u.nombre_completo,\n" +
            "    u.fecha_alta,\n" +
            "    u.status,\n" +
            "    u.tipo_usuario,\n" +
            "    u.url_imagen,\n" +
            "    e.id_estudiante,\n" +
            "    e.grado_grupo_id_grado_grupo,\n" +
            "    e.matricula,\n" +
            "    e.tipo,\n" +
            "    gg.id_grado_grupo,\n" +
            "    gg.grado_grupo,\n" +
            "    n.id_nivel,\n" +
            "    n.nivel_academico\n" +
            "from usuario u\n" +
            "join estudiante e\n" +
            "on u.id_usuario = e.id_estudiante\n" +
            "join grado_grupo gg on e.grado_grupo_id_grado_grupo = gg.id_grado_grupo\n" +
            "join nivel n on gg.nivel_id_nivel = n.id_nivel\n" +
            "where u.id_usuario = ?;", nativeQuery = true)
    List<Object[]> findByIdUsuarioAndEstudiante(@Param("idUsuario") Long idUsuario);

    Optional<Usuario> findByCorreoElectronico(String correoElectronico);

    boolean existsByCorreoElectronico(String correoElectronico);
}
