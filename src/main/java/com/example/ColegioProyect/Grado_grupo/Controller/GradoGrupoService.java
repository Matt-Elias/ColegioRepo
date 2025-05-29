package com.example.ColegioProyect.Grado_grupo.Controller;

import com.example.ColegioProyect.Grado_grupo.Model.GradoGrupo;
import com.example.ColegioProyect.Grado_grupo.Model.GradoGrupoDTO;
import com.example.ColegioProyect.Grado_grupo.Model.GradoGrupoRepository;
import com.example.ColegioProyect.Niveles.Model.Nivel;
import com.example.ColegioProyect.Niveles.Model.NivelRepository;
import com.example.ColegioProyect.Utils.Message;
import com.example.ColegioProyect.Utils.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GradoGrupoService {
    private static final Logger logger = LoggerFactory.getLogger(GradoGrupoService.class);

    private final GradoGrupoRepository gradoGrupoRepository;
    private final NivelRepository nivelRepository;

    @Autowired
    public GradoGrupoService (GradoGrupoRepository gradoGrupoRepository, NivelRepository nivelRepository) {
        this.gradoGrupoRepository = gradoGrupoRepository;
        this.nivelRepository = nivelRepository;
    }

    @Transactional (readOnly = true)
    public ResponseEntity<Object> obtenerTodosLosGradosGrupos () {
        logger.info("Ejecutando funcion de: Obtener todos los grados y grupos");
        return new ResponseEntity<>(new Message(gradoGrupoRepository.findAll(), "Listado de grados y grupos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> agregarGradoGrupo (GradoGrupoDTO gradoGrupoDTO) {
        logger.info("Ejecutando funcion de: Agregar grado grupo");

        gradoGrupoDTO.setGradoGrupo(gradoGrupoDTO.getGradoGrupo().toLowerCase());
        if (gradoGrupoDTO.getGradoGrupo().length() > 20) {
            return new ResponseEntity<>(new Message("El nombre del grado y grupo no puede exceder los 20 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<GradoGrupo> gradoGrupoOptional = gradoGrupoRepository.findByGradoGrupoaAndIdGradoGrupo(gradoGrupoDTO.getGradoGrupo(), 0L);
        if (gradoGrupoOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El grado y grupo ya existe, porfavor seleccione otros datos", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Nivel> optionalNivel = nivelRepository.findById(gradoGrupoDTO.getNivel().getIdNivel());
        if (!optionalNivel.isPresent()) {
            return new ResponseEntity<>(new Message("No se encontro el nivel o no existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        GradoGrupo gradoGrupo = new GradoGrupo(gradoGrupoDTO.getGradoGrupo(), true, optionalNivel.get());
        gradoGrupo = gradoGrupoRepository.saveAndFlush(gradoGrupo);

        if (gradoGrupo == null) {
            return new ResponseEntity<>(new Message("No se pudo guardar el grado y el grupo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(gradoGrupo, "Se guardo el grado y el grupo correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> modificarGradoGrupo (GradoGrupoDTO gradoGrupoDTO) {
        logger.info("Ejecutando funcion de: Modificar grado grupo");

        gradoGrupoDTO.setGradoGrupo(gradoGrupoDTO.getGradoGrupo().toLowerCase());
        if (gradoGrupoDTO.getGradoGrupo().length() > 20) {
            return new ResponseEntity<>(new Message("El grado y el grupo no deben tener mas de 20 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        /*gradoGrupoDTO.setIdGradoGrupo(gradoGrupoDTO.getIdGradoGrupo());
        if (gradoGrupoDTO.getIdGradoGrupo().equals(0L) || gradoGrupoDTO.getIdGradoGrupo() == null) {
            return new ResponseEntity<>(new Message("El id no puede ser 0 o nullo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }*/

        Optional<Nivel> optionalNivel = nivelRepository.findById(gradoGrupoDTO.getNivel().getIdNivel());
        if (!optionalNivel.isPresent()) {
            return new ResponseEntity<>(new Message("El id del nivel no existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        /*NivelDTO nivelDTO = new NivelDTO();
        nivelDTO.setIdNivel(nivelDTO.getIdNivel());
        if (nivelDTO.getIdNivel().equals(0L) || nivelDTO.getIdNivel() == null) {
            return new ResponseEntity<>(new Message("El id del nivel no puede ser 0 o nullo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }*/

        //GradoGrupo gradoGrupo = optionalGradoGrupo.get();
        Optional<GradoGrupo> optionalGradoGrupo = gradoGrupoRepository.findById(gradoGrupoDTO.getIdGradoGrupo());
        if (!optionalGradoGrupo.isPresent()) {
            return new ResponseEntity<>(new Message("El id del grado grupo no existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        GradoGrupo gradoGrupo = optionalGradoGrupo.get();
        gradoGrupo.setGradoGrupo(gradoGrupoDTO.getGradoGrupo());
        gradoGrupo.setNivel(optionalNivel.get());
        gradoGrupo = gradoGrupoRepository.saveAndFlush(gradoGrupo);
        if (gradoGrupo == null) {
            return new ResponseEntity<>(new Message("Error al guardar el grado y el grupo", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(gradoGrupo,"Se modifico el grado y el grupo correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> cambiarStatusGradoGrupo (GradoGrupoDTO gradoGrupoDTO) {
        logger.info("Ejecutando funcion de: Cambiar status grado grupo");

        Optional<GradoGrupo> optionalGradoGrupo = gradoGrupoRepository.findById(gradoGrupoDTO.getIdGradoGrupo());
        if (!optionalGradoGrupo.isPresent()) {
            return new ResponseEntity<>(new Message("No se encotro el id del grado y del grupo", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        GradoGrupo gradoGrupo = optionalGradoGrupo.get();
        gradoGrupo.setStatus(!gradoGrupo.isStatus());
        gradoGrupo = gradoGrupoRepository.saveAndFlush(gradoGrupo);
        if (gradoGrupo == null) {
            return new ResponseEntity<>(new Message("Error al cambiar el status del grado y grupo", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message("Se cambio el estatus de grado y el grupo correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

}



