package com.example.ColegioProyect.RegistroAsistencia.Controller;

import com.example.ColegioProyect.RegistroAsistencia.Model.RegistroAsistenciaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registroAsistencia")
public class RegistroAsistenciaController {

    private final RegistroAsistenciaService registroAsistenciaService;

    @Autowired
    public RegistroAsistenciaController(RegistroAsistenciaService registroAsistenciaService) {
        this.registroAsistenciaService = registroAsistenciaService;
    }

    @GetMapping("/listadoAsistencia")
    public ResponseEntity<Object> listadoAsistencia() {
        return registroAsistenciaService.registrosDeAsistencia();
    }

    @PostMapping("/crearAsistencia")
    public ResponseEntity<Object> guardarAsistencia ( @Validated(RegistroAsistenciaDTO.RegistrarAsistencia.class) @RequestBody RegistroAsistenciaDTO registroAsistenciaDTO) {
        return registroAsistenciaService.registrarAsistencia(registroAsistenciaDTO);
    }

    @GetMapping("/asistenciaActual")
    public ResponseEntity<Object> AsistenciaActual() {
        return registroAsistenciaService.listaAsistenciaActual();
    }

}
