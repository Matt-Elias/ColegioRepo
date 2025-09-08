package com.example.ColegioProyect.Usuarios.Controller;

import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.example.ColegioProyect.Usuarios.Model.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
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

    @GetMapping("/soloEstudiantes")
    public ResponseEntity<Object>listadoEstudiantes(){
        return usuarioService.obtenerSoloEstudiantes();
    }

    @GetMapping("/soloPadres")
    public ResponseEntity<Object>listadoPadres(){
        return usuarioService.obtenerSoloPadres();
    }

    @GetMapping("/soloProfesores")
    public ResponseEntity<Object>listadoProfesores(){
        return usuarioService.obtenerSoloProfesores();
    }

    @GetMapping("/buscarPadre/{idUsuario}")
    public ResponseEntity<Object>listadoIdPadre(@PathVariable Long idUsuario){
        return usuarioService.soloIdPadre(idUsuario);
    }

    @GetMapping("/buscarProfesor/{idUsuario}")
    public ResponseEntity<Object>listadoIdProfesor(@PathVariable Long idUsuario){
        return usuarioService.soloIdProfesor(idUsuario);
    }

    @GetMapping("/buscarEstudiante/{idUsuario}")
    public ResponseEntity<Object>listadoIdEstudiante(@PathVariable Long idUsuario){
        return usuarioService.soloIdEstudiante(idUsuario);
    }

    @PostMapping("/crearUsuario")
    public ResponseEntity<Object> crearUsuario(@Validated({UsuarioDTO.RegistrarUsuario.class})@RequestBody UsuarioDTO usuarioDTO) {
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
