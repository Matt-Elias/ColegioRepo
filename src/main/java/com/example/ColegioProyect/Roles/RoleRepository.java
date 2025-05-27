package com.example.ColegioProyect.Roles;

import com.example.ColegioProyect.Usuarios.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Usuario> findByUserName(String name);
}
