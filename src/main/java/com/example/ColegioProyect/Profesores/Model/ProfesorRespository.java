package com.example.ColegioProyect.Profesores.Model;

import com.example.ColegioProyect.Usuarios.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesorRespository extends JpaRepository<Profesor, Long> {

}
