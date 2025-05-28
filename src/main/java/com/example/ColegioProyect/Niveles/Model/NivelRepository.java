package com.example.ColegioProyect.Niveles.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NivelRepository extends JpaRepository<Nivel, Long> {

    @Query(value = "SELECT * FROM nivel WHERE nivel_academico LIKE ?1 AND id_nivel != ?2 LIMIT 1", nativeQuery = true)
    Optional<Nivel> searchByNivelAcademicoAndIdNivel(String nivel_academico, Long id_nivel);

    List<Nivel> findAllByStatusIsTrue();

}
