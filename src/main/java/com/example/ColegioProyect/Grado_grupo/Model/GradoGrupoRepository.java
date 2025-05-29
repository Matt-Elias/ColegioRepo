package com.example.ColegioProyect.Grado_grupo.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradoGrupoRepository extends JpaRepository<GradoGrupo, Long> {
    @Query(value = "SELECT * FROM grado_grupo WHERE grado_grupo LIKE ?1 AND id_grado_grupo != ?2 LIMIT 1", nativeQuery = true)
    Optional <GradoGrupo> findByGradoGrupoaAndIdGradoGrupo(String grado_grupo, long id_grado_grupo);

}
