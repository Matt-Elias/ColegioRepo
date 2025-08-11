package com.example.ColegioProyect.Utils;

import com.example.ColegioProyect.Roles.Rol;
import com.example.ColegioProyect.Roles.RoleRepository;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.example.ColegioProyect.Usuarios.Model.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Crear rol si no existe
            Rol rolAministrador = roleRepository.findByRol("ADMINISTRADOR")
                    .orElseGet(() -> {
                        Rol nuevoRol = new Rol("ADMINISTRADOR");
                        nuevoRol.setRol("ADMINISTRADOR");
                        return roleRepository.save(nuevoRol);
                    });

            Rol rolSubAdmin = roleRepository.findByRol("SUBADMIN")
                    .orElseGet(() -> {
                        Rol nuevoRol = new Rol("SUBADMIN");
                        nuevoRol.setRol("SUBADMIN");
                        return roleRepository.save(nuevoRol);
                    });

            // Crear usuario admin si no existe
                if (!usuarioRepository.existsByCorreoElectronico("20233tn100@utez.edu.mx")) {
                    Usuario usuarioAdministrador = new Usuario();
                    usuarioAdministrador.setNombreCompleto("Elias Manuel Marquez Bailon");
                    usuarioAdministrador.setCorreoElectronico("20233tn100@utez.edu.mx");
                    usuarioAdministrador.setTipoUsuario("ADMINISTRADOR");
                    usuarioAdministrador.setContrasena(passwordEncoder.encode("pablo1"));
                    usuarioAdministrador.setUrlImagen("Algo");
                    usuarioAdministrador.setStatus(true);

                    if (usuarioAdministrador.getRoles() == null) {
                        usuarioAdministrador.setRoles(new HashSet<>());
                    }
                    usuarioAdministrador.getRoles().add(rolAministrador);

                    usuarioRepository.save(usuarioAdministrador);
                    System.out.println("Usuario administrador creado exitosamente");
                }

                if (!usuarioRepository.existsByCorreoElectronico("adminBilingue@gmail.com")) {
                    Usuario subAdmin = new Usuario();
                    subAdmin.setNombreCompleto("Admin Bilingue");
                    subAdmin.setCorreoElectronico("adminBilingue@gmail.com");
                    subAdmin.setTipoUsuario("SUBADMIN");
                    subAdmin.setContrasena(passwordEncoder.encode("Bilingue62575"));
                    subAdmin.setStatus(true);

                    if (subAdmin.getRoles() == null) {
                        subAdmin.setRoles(new HashSet<>());
                    }
                    subAdmin.getRoles().add(rolSubAdmin);

                    usuarioRepository.save(subAdmin);
                }
        };
    }
}
