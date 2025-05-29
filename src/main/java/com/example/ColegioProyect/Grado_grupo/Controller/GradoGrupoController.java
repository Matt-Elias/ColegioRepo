package com.example.ColegioProyect.Grado_grupo.Controller;

import com.example.ColegioProyect.Grado_grupo.Model.GradoGrupoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gradoGrupo")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class GradoGrupoController {

    private final GradoGrupoService gradoGrupoService;

    @Autowired
    public GradoGrupoController(GradoGrupoService gradoGrupoService) {
        this.gradoGrupoService = gradoGrupoService;
    }

    @GetMapping("/consultarGradoGrupos")
    public ResponseEntity<Object> obtenerTodosLosGradosGrupos () {
        return gradoGrupoService.obtenerTodosLosGradosGrupos();
    }

    @PostMapping("/registrarGradoGrupos")
    public ResponseEntity<Object> crearGradoGrupos (@Validated({GradoGrupoDTO.RegistradGradoGrupo.class})@RequestBody GradoGrupoDTO gradoGrupoDTO) {
        return gradoGrupoService.agregarGradoGrupo(gradoGrupoDTO);
    }

    @PutMapping("/modificarGradoGrupos")
    public ResponseEntity<Object> modificarGradoGrupos (@Validated({GradoGrupoDTO.ModificarGradoGrupo.class})@RequestBody GradoGrupoDTO gradoGrupoDTO) {
        return gradoGrupoService.modificarGradoGrupo(gradoGrupoDTO);
    }

    @PutMapping("/cambiarStatusGradosGrupos")
    public ResponseEntity<Object> cambiarStatusGradoGrupos (@Validated({GradoGrupoDTO.CambiarStatusGradoGrupo.class})@RequestBody GradoGrupoDTO gradoGrupoDTO) {
        return gradoGrupoService.cambiarStatusGradoGrupo(gradoGrupoDTO);
    }

}
