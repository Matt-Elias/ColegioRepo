package com.example.ColegioProyect.Security;

import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.example.ColegioProyect.Usuarios.Model.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreoElectronico(),
                usuario.getContrasena(),
                usuario.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getRol()))
                        .collect(Collectors.toList())
        );
    }
}
