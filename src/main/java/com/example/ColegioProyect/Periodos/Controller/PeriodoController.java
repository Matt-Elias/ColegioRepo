package com.example.ColegioProyect.Periodos.Controller;

import com.example.ColegioProyect.Periodos.Model.PeriodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/periodo")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class PeriodoController {

    private final PeriodoService periodoService;

    @Autowired
    public PeriodoController(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    @GetMapping("/consultarPeriodo")
    public ResponseEntity<Object> consultarPeriodos() {
        return periodoService.obtenerTodosLosPeriodos();
    }

    @PostMapping("/registrarPeriodo")
    public ResponseEntity<Object> registrarPeriodos(@Validated({PeriodoDTO.RegistrarPeriodo.class}) @RequestBody PeriodoDTO periodoDTO) {
        return periodoService.agregarPeriodo(periodoDTO);
    }

    @PutMapping("/modificarPeriodo")
    public ResponseEntity<Object> modificarPeriodo(@Validated({PeriodoDTO.ModificarPeriodo.class})@RequestBody PeriodoDTO periodoDTO) {
        return periodoService.modificarPeriodo(periodoDTO);
    }
    
}
