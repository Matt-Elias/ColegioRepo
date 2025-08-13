package com.example.ColegioProyect.RegistroAsistencia.Controller;

import com.example.ColegioProyect.Estudiantes.Model.Estudiante;
import com.example.ColegioProyect.Estudiantes.Model.EstudianteRepository;
import com.example.ColegioProyect.RegistroAsistencia.Model.RegistroAsistencia;
import com.example.ColegioProyect.RegistroAsistencia.Model.RegistroAsistenciaDTO;
import com.example.ColegioProyect.RegistroAsistencia.Model.RegistroAsistenciaRespository;
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

import java.sql.SQLException;
import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
public class RegistroAsistenciaService {
    private static final Logger logger = LoggerFactory.getLogger(RegistroAsistenciaService.class);

    public final RegistroAsistenciaRespository registroAsistenciaRespository;
    public final UsuarioRepository usuarioRepository;
    public final EstudianteRepository estudianteRepository;

    @Autowired
    public RegistroAsistenciaService(RegistroAsistenciaRespository registroAsistenciaRespository ,UsuarioRepository usuarioRepository, EstudianteRepository estudianteRepository) {
        this.registroAsistenciaRespository = registroAsistenciaRespository;
        this.usuarioRepository = usuarioRepository;
        this.estudianteRepository = estudianteRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> registrosDeAsistencia () {
        return new ResponseEntity<>(new Message(registroAsistenciaRespository.findAll(), "Listado de todos los registros de asistencia", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> registrarAsistencia (RegistroAsistenciaDTO registroAsistenciaDTO) {
        logger.info("Ejecutando funcion de: Registrar asistencia");

        registroAsistenciaDTO.setRegistro(registroAsistenciaDTO.getRegistro());
        if (registroAsistenciaDTO.getRegistro().length() > 20 || registroAsistenciaDTO.getRegistro().isEmpty()) {
            return new ResponseEntity<>(new Message("El registro no puede exceder los 20 caracteres o estar vacio", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(registroAsistenciaDTO.getIdUsuario());
        if (usuarioOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("El usuario no se encontro o existente", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioOptional.get();

        if (!"SUBADMIN".equalsIgnoreCase(usuario.getTipoUsuario())) {
            return new ResponseEntity<>(new Message("El tipo de usuario no es correcto, debe ser ADMINISTRADOR o SUBADMIN", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(registroAsistenciaDTO.getIdEstudiante());
        if (estudianteOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("No se encontro el id del estudiante", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        // Validar que la fecha/hora no sea nula
        if (registroAsistenciaDTO.getFechaHora() == null) {
            return new ResponseEntity<>(new Message("La fecha y hora son obligatorias", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Estudiante estudiante = estudianteOptional.get();

        RegistroAsistencia registroAsistencia = new RegistroAsistencia(usuario, registroAsistenciaDTO.getFechaHora() ,registroAsistenciaDTO.getRegistro(), estudiante);
        registroAsistencia = registroAsistenciaRespository.saveAndFlush(registroAsistencia);

        if (registroAsistencia == null) {
            return new ResponseEntity<>(new Message("Error al guardar el registro de asistencia", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(registroAsistencia,"Asistencia registrada correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> listaAsistenciaActual () {
        logger.info("Ejecutando funcion de: Lista de asistencia actual");
        return new ResponseEntity<>(new Message(registroAsistenciaRespository.findRegistroAsistenciaByActually(), "Listado de registro de asistencias actuales", TypesResponse.SUCCESS), HttpStatus.OK);
    }

}
