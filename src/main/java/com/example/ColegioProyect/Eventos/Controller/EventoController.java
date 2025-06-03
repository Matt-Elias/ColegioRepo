package com.example.ColegioProyect.Eventos.Controller;

import com.example.ColegioProyect.Eventos.Model.EventoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evento")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class EventoController {

    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/obtenerEventos")
    public ResponseEntity<Object> obtenerEventos() {
        return eventoService.obtenerTodosLosEventos();
    }

    @PostMapping("/crearEvento")
    public ResponseEntity<Object> crearEvento(@Validated(EventoDTO.RegistrarEvento.class) @RequestBody EventoDTO eventoDTO) {
        return eventoService.registrarEvento(eventoDTO);
    }

    @PutMapping("/modificarEvento")
    public ResponseEntity<Object> modificarEvento(@Validated(EventoDTO.ModificarEvento.class) @RequestBody EventoDTO eventoDTO) {
        return eventoService.editarEvento(eventoDTO);
    }

}
