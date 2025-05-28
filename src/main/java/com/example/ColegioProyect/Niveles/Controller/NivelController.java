package com.example.ColegioProyect.Niveles.Controller;

import com.example.ColegioProyect.Niveles.Model.NivelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nivel")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class NivelController {

    private final NivelService nivelService;

    @Autowired
    public NivelController(NivelService nivelService) {
        this.nivelService = nivelService;
    }

    @GetMapping("/consultarNiveles")
    public ResponseEntity<Object> todosLosNiveles() {
        return nivelService.encontrarTodos();
    }

    @PostMapping("/crearNivel")
    public ResponseEntity<Object> guardarNivel (@Validated ({NivelDTO.RegistrarNivel.class}) @RequestBody NivelDTO nivelDTO) {
        return nivelService.crearNivel(nivelDTO);
    }

    @PutMapping("/modificarNivel")
    public ResponseEntity<Object> modificarNivel (@Validated (NivelDTO.ModificarNivel.class) @RequestBody NivelDTO nivelDTO) {
        return nivelService.actualizarNivel(nivelDTO);
    }

    @PutMapping("/cambiarStatus")
    public ResponseEntity<Object> cambiarStatus (@Validated(NivelDTO.CambiarStatusNivel.class) @RequestBody NivelDTO nivelDTO) {
        return nivelService.cambiarStatus(nivelDTO);
    }

}
