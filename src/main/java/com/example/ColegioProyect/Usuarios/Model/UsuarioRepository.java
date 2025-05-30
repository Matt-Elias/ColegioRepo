package com.example.ColegioProyect.Usuarios.Model;

import com.example.ColegioProyect.Estudiantes.Model.Estudiante;
import com.example.ColegioProyect.Padres.Model.Padre;
import com.example.ColegioProyect.Profesores.Model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT * FROM usuario WHERE correo_electronico LIKE ?1 AND id_usuario != ?2 LIMIT 1", nativeQuery = true)
    Optional<Usuario> searchByCorreoElectronico(String correo_electronico, Long id_usuario);

    /*Optional<Estudiante> searchByEstudiante(Long idEstudiante);

    Optional<Padre> searchByPadre(Long idPadre);

    Optional<Profesor> searchByProfesor(Long idProfesor);*/

    /*@Query(value = "SELECT * FROM usuario WHERE tipo_usuario LIKE '%Estud'", nativeQuery = true)
    Optional<Usuario> searchByTipoUsuario(String tipoUsuario);*/

}
