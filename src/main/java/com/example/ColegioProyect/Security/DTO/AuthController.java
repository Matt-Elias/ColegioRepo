package com.example.ColegioProyect.Security.DTO;

import com.example.ColegioProyect.Security.JwtUtil;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.example.ColegioProyect.Usuarios.Model.UsuarioRepository;
import com.example.ColegioProyect.Utils.Message;
import com.example.ColegioProyect.Utils.TypesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getCorreoElectronico(), authRequest.getContrasena())
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new Message("Correo electronico o contraseña incorrectos, vuelva a intentarlo", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getCorreoElectronico());
        final String jwt = jwtUtil.generateToken(userDetails);

        Usuario usuario = usuarioRepository.findByCorreoElectronico(authRequest.getCorreoElectronico())
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        long expirationTime = jwtUtil.getExpirationTime();
        AuthResponse authResponse = new AuthResponse(jwt, usuario.getIdUsuario(), usuario.getCorreoElectronico(), expirationTime);

        return new ResponseEntity<>(new Message(authResponse, "Sesion iniciada correctamente. Bienvenido", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
