package com.example.ColegioProyect.Materias.Controller;

import com.example.ColegioProyect.Materias.Model.MateriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materia")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class MateriaController {

    public final MateriaService materiaService;

    @Autowired
    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping("/obtenerMaterias")
    public ResponseEntity<Object> obtenerMaterias(){
     return materiaService.obtenerTodasLasMaterias();
    }

    @PostMapping("/crearMateria")
    public ResponseEntity<Object> crearMateria(@Validated({MateriaDTO.RegistrarMateria.class}) @RequestBody MateriaDTO materiaDTO){
        return materiaService.registrarMateria(materiaDTO);
    }

    @PutMapping("/modificarMateria")
    public ResponseEntity<Object> actualizarMateria(@Validated({MateriaDTO.ModificarMateria.class}) @RequestBody MateriaDTO materiaDTO){
        return materiaService.actualizarMateria(materiaDTO);
    }


}
