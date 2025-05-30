package com.example.ColegioProyect.Profesores.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRespository extends JpaRepository<Profesor, Integer> {
}
