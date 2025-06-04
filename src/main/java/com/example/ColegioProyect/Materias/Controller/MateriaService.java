package com.example.ColegioProyect.Materias.Controller;

import com.example.ColegioProyect.Materias.Model.Materia;
import com.example.ColegioProyect.Materias.Model.MateriaDTO;
import com.example.ColegioProyect.Materias.Model.MateriaRepository;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.example.ColegioProyect.Usuarios.Model.UsuarioRepository;
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
public class MateriaService {
    private static final Logger logger = LoggerFactory.getLogger(MateriaService.class);

    public final MateriaRepository materiaRepository;
    public final UsuarioRepository usuarioRepository;

    @Autowired
    public MateriaService(MateriaRepository materiaRepository, UsuarioRepository usuarioRepository) {
        this.materiaRepository = materiaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ResponseEntity<Object> obtenerTodasLasMaterias(){
        logger.info("Ejecutando funcion de: Obtener todas las materias");
        return new ResponseEntity<>(new Message(materiaRepository.findAll(),"Listado de materias", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> registrarMateria(MateriaDTO materiaDTO){
        logger.info("Ejecutando funcion de: registrar materia");

        materiaDTO.setNombreMateria(materiaDTO.getNombreMateria());
        if (materiaDTO.getNombreMateria().length() > 45){
            return new ResponseEntity<>(new Message("El nombre de la materia excede los 45 caracteres",TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        materiaDTO.setAsignaciones(materiaDTO.getAsignaciones());
        if (materiaDTO.getAsignaciones() == 0 || materiaDTO.getAsignaciones() < 0) {
            return new ResponseEntity<>(new Message("La asignacion no puede ser 0 o un numero negativo",TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(materiaDTO.getProfesor().getIdProfesor());
        if (usuarioOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("El profesor no existe",TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Materia materia = new Materia(materiaDTO.getNombreMateria(), materiaDTO.getAsignaciones(), usuarioOptional.get().getProfesor());
        materia = materiaRepository.saveAndFlush(materia);

        if (materia == null) {
            return new ResponseEntity<>(new Message("Error al registrar materia",TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(materia,"La materia se registro correctamente",TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> actualizarMateria(MateriaDTO materiaDTO){
        logger.info("Ejecutando funcion de: actualizar materia");

        materiaDTO.setNombreMateria(materiaDTO.getNombreMateria());
        if (materiaDTO.getNombreMateria().length() > 45){
            return new ResponseEntity<>(new Message("El nombre de la materia excede los 45 caracteres",TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        materiaDTO.setAsignaciones(materiaDTO.getAsignaciones());
        if (materiaDTO.getAsignaciones() == 0 || materiaDTO.getAsignaciones() < 0) {
            return new ResponseEntity<>(new Message("La asignacion no puede ser 0 o un numero negativo",TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(materiaDTO.getProfesor().getIdProfesor());
        if (usuarioOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("El profesor no existe",TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Materia> optionalMateria = materiaRepository.findById(materiaDTO.getIdMateria());
        if (optionalMateria.isEmpty()) {
            return new ResponseEntity<>(new Message("El materia no existe",TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Materia materia = optionalMateria.get();
        materia.setNombreMateria(materiaDTO.getNombreMateria());
        materia.setAsignaciones(materiaDTO.getAsignaciones());
        materia.setProfesor(usuarioOptional.get().getProfesor());
        materia = materiaRepository.saveAndFlush(materia);

        if (materia == null) {
            return new ResponseEntity<>(new Message("Error al modificar materia",TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(materia,"Se actualizo la materia correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

}
