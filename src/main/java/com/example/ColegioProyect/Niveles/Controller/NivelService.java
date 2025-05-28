package com.example.ColegioProyect.Niveles.Controller;

import com.example.ColegioProyect.Niveles.Model.Nivel;
import com.example.ColegioProyect.Niveles.Model.NivelDTO;
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

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class NivelService {
    private static final Logger logger = LoggerFactory.getLogger(NivelService.class);

    private final NivelRepository nivelRepository;

    @Autowired
    public NivelService(NivelRepository nivelRepository ) {
        this.nivelRepository = nivelRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> encontrarTodos(){
        return new ResponseEntity<>(new Message(nivelRepository.findAll(), "Lista de estados activos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> crearNivel(NivelDTO nivelDTO){
        logger.info("Ejecutando funcion de Crear Nivel");

        nivelDTO.setNivelAcademico(nivelDTO.getNivelAcademico());

        if (nivelDTO.getNivelAcademico().length() > 60) {
            logger.warn("El nombre del nivel academico excede los 60 caracteres");
            return new ResponseEntity<>(new Message("El nombre academico excede los 60 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Nivel> optionalNivel = nivelRepository.searchByNivelAcademicoAndIdNivel(nivelDTO.getNivelAcademico(), 0L);
        if (optionalNivel.isPresent()) {
            return new ResponseEntity<>(new Message("El nivel ya se registro anteriormente, favor de usar otro nombre", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Nivel nivel = new Nivel(nivelDTO.getNivelAcademico(), true);
        nivel = nivelRepository.saveAndFlush(nivel);

        if (nivel == null) {
            logger.error("Error al guardar nivel");
            return new ResponseEntity<>(new Message("Error al guardar nivel", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(nivel, "Se ha registrado el nivel exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> actualizarNivel(NivelDTO nivelDTO){
        logger.info("Ejecutando funcion de Actualizar Nivel");

        nivelDTO.setNivelAcademico(nivelDTO.getNivelAcademico().toLowerCase());

        if (nivelDTO.getNivelAcademico().length() > 60) {
            logger.info("El nivel no puede exceder los 60 caracteres");
            return new ResponseEntity<>(new Message("El nivel no puede exceder los 60 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Nivel> optional = nivelRepository.findById(nivelDTO.getIdNivel());
        if (!optional.isPresent()) {
            return new ResponseEntity<>(new Message("El nivel no se encontro o no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Optional<Nivel> optionalNivel = nivelRepository.searchByNivelAcademicoAndIdNivel(nivelDTO.getNivelAcademico(), 0L);
        if (optionalNivel.isPresent()) {
            return new ResponseEntity<>(new Message("El nivel ya existe, favor de seleccionar otro", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Nivel nivel = optional.get();
        nivel.setNivelAcademico(nivelDTO.getNivelAcademico());
        nivel = nivelRepository.saveAndFlush(nivel);

        if (nivel == null) {
            logger.error("Error al guardar modificacion del nivel");
            return new ResponseEntity<>(new Message("Error al guardar modificacion", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(nivel, "Se guardo correctamente el nivel", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> cambiarStatus(NivelDTO nivelDTO){
        logger.info("Ejecutando funcion de Cambiar Nivel");

        Optional<Nivel> optionalNivel = nivelRepository.findById(nivelDTO.getIdNivel());
        if (!optionalNivel.isPresent()) {
            return new ResponseEntity<>(new Message("No se encontro el nivel", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Nivel nivel = optionalNivel.get();
        nivel.setStatus(!nivel.isStatus());
        nivel = nivelRepository.saveAndFlush(nivel);

        if (nivel == null) {
            logger.error("Error al guardar y cambiar el estatus del nivel");
        }
        return new ResponseEntity<>(new Message(nivel, "Se guardo correctamente el nivel", TypesResponse.SUCCESS), HttpStatus.OK);
    }


}
