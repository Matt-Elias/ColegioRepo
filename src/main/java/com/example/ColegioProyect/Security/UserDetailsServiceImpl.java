package com.example.ColegioProyect.Security;

import com.example.ColegioProyect.Roles.Rol;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.example.ColegioProyect.Usuarios.Model.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(correoElectronico)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + correoElectronico));

        Rol role;
        System.out.println("Roles del usuario desde BD: " +
                usuario.getRoles().stream()
                        .map(Rol::getRol)
                        .collect(Collectors.toList()));

        System.out.println("Usuario encontrado: " + usuario.getCorreoElectronico());
        System.out.println("TipoUsuario: " + usuario.getTipoUsuario());
        System.out.println("Roles asociados: " + usuario.getRoles().size());
        usuario.getRoles().forEach(r -> System.out.println(" - " + r.getRol()));

        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreoElectronico(),
                usuario.getContrasena(),
                usuario.getRoles().stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getRol()))
                        .collect(Collectors.toList())
        );
    }
}
