package com.example.ColegioProyect.Periodos.Controller;

import com.example.ColegioProyect.Niveles.Model.Nivel;
import com.example.ColegioProyect.Niveles.Model.NivelRepository;
import com.example.ColegioProyect.Periodos.Model.Periodo;
import com.example.ColegioProyect.Periodos.Model.PeriodoDTO;
import com.example.ColegioProyect.Periodos.Model.PeriodoRepository;
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
public class PeriodoService {
    private static final Logger logger = LoggerFactory.getLogger(PeriodoService.class);

    public final PeriodoRepository periodoRepository;
    public final NivelRepository nivelRepository;

    @Autowired
    public PeriodoService(PeriodoRepository periodoRepository, NivelRepository nivelRepository) {
        this.periodoRepository = periodoRepository;
        this.nivelRepository = nivelRepository;
    }

    @Transactional
    public ResponseEntity<Object> obtenerTodosLosPeriodos () {
        logger.info("Ejecutando funcion de: Obtener todos los periodos");
        return new ResponseEntity(new Message(periodoRepository.findAll(), "Listado de todos los periodos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> agregarPeriodo (PeriodoDTO periodoDTO) {
        logger.info("Ejecutando funcion de: Agregar periodo");

        periodoDTO.setCalificaciones(periodoDTO.getCalificaciones());
        if (periodoDTO.getCalificaciones().length() > 15) {
            return new ResponseEntity<>(new Message("La calificaciones no pueden exceder mas de 15 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        periodoDTO.setAsistencia(periodoDTO.getAsistencia());
        if (periodoDTO.getAsistencia().length() > 15) {
            return new ResponseEntity<>(new Message("La asistencia no puede exceder mas de 15 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        periodoDTO.setTareas(periodoDTO.getTareas());
        if (periodoDTO.getTareas().length() > 15) {
            return new ResponseEntity<>(new Message("Las tareas no pueden exceder mas de 15 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        periodoDTO.setProyectos(periodoDTO.getProyectos());
        if (periodoDTO.getProyectos().length() > 15) {
            return new ResponseEntity<>(new Message("Los proyectos no pueden exceder mas de 15 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        periodoDTO.setTipoPeriodo(periodoDTO.getTipoPeriodo());
        if (periodoDTO.getTipoPeriodo().length() > 15) {
            return new ResponseEntity<>(new Message("Los tipo de periodo no pueden exceder mas de 15 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Nivel> optionalNivel = nivelRepository.findById(periodoDTO.getNivel().getIdNivel());
        if (!optionalNivel.isPresent()) {
            return new ResponseEntity<>(new Message("No se encotro el nivel o no existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Periodo periodo = new Periodo(periodoDTO.getCalificaciones(), periodoDTO.getAsistencia(), periodoDTO.getTareas(), periodoDTO.getProyectos(), periodoDTO.getTipoPeriodo(), optionalNivel.get());
        periodo = periodoRepository.saveAndFlush(periodo);
        if (periodo == null) {
            return new ResponseEntity<>(new Message("Error al guardar el periodo", TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(periodo,"Se agrego el periodo correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    public ResponseEntity<Object> modificarPeriodo (PeriodoDTO periodoDTO) {
        logger.info("Ejecutando funcion de: Modificar periodo");

        periodoDTO.setCalificaciones(periodoDTO.getCalificaciones());
        if (periodoDTO.getCalificaciones().length() > 15) {
            return new ResponseEntity<>(new Message("La calificaciones no pueden exceder mas de 15 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        periodoDTO.setAsistencia(periodoDTO.getAsistencia());
        if (periodoDTO.getAsistencia().length() > 15) {
            return new ResponseEntity<>(new Message("La asistencia no puede exceder mas de 15 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        periodoDTO.setTareas(periodoDTO.getTareas());
        if (periodoDTO.getTareas().length() > 15) {
            return new ResponseEntity<>(new Message("Las tareas no pueden exceder mas de 15 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        periodoDTO.setProyectos(periodoDTO.getProyectos());
        if (periodoDTO.getProyectos().length() > 15) {
            return new ResponseEntity<>(new Message("Los proyectos no pueden exceder mas de 15 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        periodoDTO.setTipoPeriodo(periodoDTO.getTipoPeriodo());
        if (periodoDTO.getTipoPeriodo().length() > 15) {
            return new ResponseEntity<>(new Message("Los tipo de periodo no pueden exceder mas de 15 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Nivel> optionalNivel = nivelRepository.findById(periodoDTO.getNivel().getIdNivel());
        if (!optionalNivel.isPresent()) {
            return new ResponseEntity<>(new Message("No se encontro el nivel o no existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Periodo> optionalPeriodo = periodoRepository.findById(periodoDTO.getIdPeriodo());
        if (!optionalPeriodo.isPresent()) {
            return new ResponseEntity<>(new Message("No se encontro el id del periodo o no existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Periodo periodo = optionalPeriodo.get();
        periodo.setCalificaciones(periodoDTO.getCalificaciones());
        periodo.setAsistencia(periodoDTO.getAsistencia());
        periodo.setTareas(periodoDTO.getTareas());
        periodo.setProyectos(periodoDTO.getProyectos());
        periodo.setTipoPeriodo(periodoDTO.getTipoPeriodo());
        periodo.setNivel(optionalNivel.get());
        periodo = periodoRepository.saveAndFlush(periodo);

        if (periodo == null) {
            return new ResponseEntity<>(new Message("No se pudo modificar el periodo",TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(periodo, "Se guardo el periodo correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

}
