package com.example.ColegioProyect.Usuarios.Controller;

import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.example.ColegioProyect.Usuarios.Model.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class UsuarioController {

    public final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/consultarUsuario")
    public ResponseEntity<Object> consultarUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @PostMapping("/crearUsuario")
    public ResponseEntity<Object> crearUsuario(@Validated({UsuarioDTO.RegistrarUsuario.class, UsuarioDTO.RegistrarNombre.class, UsuarioDTO.RegistrarTipoUsuario.class})@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.registrarUsuario(usuarioDTO);
    }

    @PutMapping("/modificarUsuario")
    public ResponseEntity<Object> actualizarUsuario(@Validated(UsuarioDTO.ModificarUsuario.class)@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.actualizarUsuarios(usuarioDTO);
    }

    @PutMapping("/cambiarStatusUsuario")
    public ResponseEntity<Object> cambiarStatusUsuario(@Validated(UsuarioDTO.CambiarStatus.class)@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.cambiarStatusUsuario(usuarioDTO);
    }

}
