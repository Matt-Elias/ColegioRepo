package com.example.ColegioProyect.Usuarios.Controller;

import com.example.ColegioProyect.Estudiantes.Model.Estudiante;
import com.example.ColegioProyect.Estudiantes.Model.EstudianteRepository;
import com.example.ColegioProyect.Padres.Model.Padre;
import com.example.ColegioProyect.Padres.Model.PadreRepository;
import com.example.ColegioProyect.Profesores.Model.Profesor;
import com.example.ColegioProyect.Profesores.Model.ProfesorRespository;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.example.ColegioProyect.Usuarios.Model.UsuarioDTO;
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
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public final UsuarioRepository usuarioRepository;
    public final EstudianteRepository estudianteRepository;
    public final PadreRepository padreRepository;
    public final ProfesorRespository profesorRespository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, EstudianteRepository estudianteRepository, PadreRepository padreRepository, ProfesorRespository profesorRespository) {
        this.usuarioRepository = usuarioRepository;
        this.estudianteRepository = estudianteRepository;
        this.padreRepository = padreRepository;
        this.profesorRespository = profesorRespository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> obtenerTodosLosUsuarios() {
        logger.info("Ejecutando funcion de: Obteniendo los usuarios");
        return new ResponseEntity<>(new Message(usuarioRepository.findAll(), "Listado de usuarios", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> estudiantesConPadres(){
        logger.info("Ejecutando funcion de: Estudiantes con padres");
        List<Object[]> resultado = usuarioRepository.findEstudiantesConPadresRaw();

        if (resultado.isEmpty()) {
            return new ResponseEntity<>(new Message("No se encontraron datos de estudiantes con padres", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Message(usuarioRepository.findAll(), "Listado de estudiantes con padres", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> padresConEstudiantes(){
        logger.info("Ejecutando funcion de: Padres con estudiantes");
        List<Object[]> resultado = usuarioRepository.findPadresConEstudiantesRaw();

        if (resultado.isEmpty()) {
            return new ResponseEntity<>(new Message("No se encontraron datos de padres con estudiantes", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Message(usuarioRepository.findAll(),"Listado de padres con estudidantes", TypesResponse.SUCCESS), HttpStatus.OK);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> registrarUsuario(UsuarioDTO usuarioDTO) {
        logger.info("Ejecutando funcion de: Registrar usuario");

        usuarioDTO.setNombreCompleto(usuarioDTO.getNombreCompleto());
        if (usuarioDTO.getNombreCompleto().length() > 45) {
            return new ResponseEntity<>(new Message("El nombre completo no puede exceder los 45 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (usuarioDTO.getNombreCompleto().isEmpty()) {
            return new ResponseEntity<>(new Message("El nombre no puede quedar vacio", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        usuarioDTO.setCorreoElectronico(usuarioDTO.getCorreoElectronico());
        if (usuarioDTO.getCorreoElectronico().length() > 60) {
            return new ResponseEntity<>(new Message("El correo no puede exceder los 60 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        usuarioDTO.setTipoUsuario(usuarioDTO.getTipoUsuario());
        if (usuarioDTO.getTipoUsuario().length() > 40) {
            return new ResponseEntity<>(new Message("El tipo de usuario no puede exceder los 40 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (usuarioDTO.getTipoUsuario().isEmpty()) {
            return new ResponseEntity<>(new Message("El tipo de usuario no puede quedar vacio", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        usuarioDTO.setContrasena(usuarioDTO.getContrasena());
        if (usuarioDTO.getContrasena().length() > 30) {
            return new ResponseEntity<>(new Message("La contraseña excede los 30 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        usuarioDTO.setUrlImagen(usuarioDTO.getUrlImagen());
        if (usuarioDTO.getUrlImagen().length() > 70) {
            return new ResponseEntity<>(new Message("La url de la imagen no puede exceder los 70 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> optionalUsuario = usuarioRepository.searchByCorreoElectronico(usuarioDTO.getCorreoElectronico(), 0L);
        if (optionalUsuario.isPresent()) {
            return new ResponseEntity<>(new Message("El correo ya existe, porfavor cambielo por otro", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario(usuarioDTO.getNombreCompleto(), usuarioDTO.getCorreoElectronico(),usuarioDTO.getTipoUsuario(), true, usuarioDTO.getContrasena(), usuarioDTO.getUrlImagen());
        usuario = usuarioRepository.saveAndFlush(usuario);

        if (usuario == null) {
            return new ResponseEntity<>(new Message("Error al registrar usuario", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        switch (usuarioDTO.getTipoUsuario().trim().toLowerCase()) {
            case "estudiante":

                Estudiante estudiante = new Estudiante();
                estudiante.setUsuario(usuario);
                estudiante.setMatricula(usuarioDTO.getEstudiante().getMatricula());
                estudiante.setTipo(usuarioDTO.getEstudiante().getTipo());
                estudiante.setGradoGrupo(usuarioDTO.getEstudiante().getGradoGrupo());
                estudiante = estudianteRepository.saveAndFlush(estudiante);
                break;

            case "padre":

                //Recuperamos el id del estudiante (objeto estudiante) ya creado y lo validamos
                Optional<Estudiante> estudiantePadre = estudianteRepository.findById(usuarioDTO.getEstudiante().getIdEstudiante());
                if (!estudiantePadre.isPresent()) {
                    return new ResponseEntity<>(new Message("El estudiante no existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
                }

                Padre padre = new Padre(usuario, estudiantePadre.get());
                padre = padreRepository.saveAndFlush(padre);

                if (padre == null) {
                    return new ResponseEntity<>(new Message("Error al registrar padre", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
                }

                break;

            case "profesor":
                Profesor profesor = new Profesor();
                profesor.setUsuario(usuario);
                profesor = profesorRespository.saveAndFlush(profesor);
                break;

                default:
                    return new ResponseEntity<>(new Message("Tipo de usuario desconicido", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(usuario, "Se registro el usuario correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<Object> actualizarUsuarios(UsuarioDTO usuarioDTO) {
        logger.info("Ejecutando funcion de: Actualizar usuarios");

        usuarioDTO.setNombreCompleto(usuarioDTO.getNombreCompleto());
        if (usuarioDTO.getNombreCompleto().length() > 45) {
            return new ResponseEntity<>(new Message("El nombre completo no puede exceder los 45 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (usuarioDTO.getNombreCompleto().isEmpty()) {
            return new ResponseEntity<>(new Message("El nombre no puede quedar vacio", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        usuarioDTO.setCorreoElectronico(usuarioDTO.getCorreoElectronico());
        if (usuarioDTO.getCorreoElectronico().length() > 60) {
            return new ResponseEntity<>(new Message("El correo no puede exceder los 60 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        usuarioDTO.setTipoUsuario(usuarioDTO.getTipoUsuario());
        if (usuarioDTO.getTipoUsuario().length() > 40) {
            return new ResponseEntity<>(new Message("El tipo de usuario no puede exceder los 40 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (usuarioDTO.getTipoUsuario().isEmpty()) {
            return new ResponseEntity<>(new Message("El tipo de usuario no puede quedar vacio", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        usuarioDTO.setContrasena(usuarioDTO.getContrasena());
        if (usuarioDTO.getContrasena().length() > 30) {
            return new ResponseEntity<>(new Message("La contraseña excede los 30 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        usuarioDTO.setUrlImagen(usuarioDTO.getUrlImagen());
        if (usuarioDTO.getUrlImagen().length() > 70) {
            return new ResponseEntity<>(new Message("La url de la imagen no puede exceder los 70 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> optional = usuarioRepository.findById(usuarioDTO.getIdUsuario());
        if (!optional.isPresent()) {
            return new ResponseEntity<>(new Message("El usuario no se encontro o no existe",TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Usuario usuario = optional.get();
        usuario.setNombreCompleto(usuarioDTO.getNombreCompleto());
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
        usuario.setContrasena(usuarioDTO.getContrasena());
        usuario.setUrlImagen(usuarioDTO.getUrlImagen());
        usuario = usuarioRepository.saveAndFlush(usuario);

        if (usuario == null) {
            return new ResponseEntity<>(new Message("Error al registrar usuario", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(usuario,"Se modifico el usuario correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> cambiarStatusUsuario(UsuarioDTO usuarioDTO) {
        logger.info("Ejecutando funcion de: Cambiar Status Usuario");

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioDTO.getIdUsuario());
        if (!optionalUsuario.isPresent()) {
            return new ResponseEntity<>(new Message("No se encontro el usuario o no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Usuario usuario = optionalUsuario.get();
        usuario.setStatus(!usuario.isStatus());
        usuario = usuarioRepository.saveAndFlush(usuario);

        if (usuario == null) {
            return new ResponseEntity<>(new Message("Error al registrar usuario", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(usuario, "Se guardo correctamente el usuario", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    /*@Transactional(readOnly = true)
    public ResponseEntity<Object> obtenerSoloEstudiantes(UsuarioDTO usuarioDTO) {
        logger.info("Ejecutando la funcion de: obtener solo estudiantes");

        Optional<Estudiante> optionalEstudiante = usuarioRepository.searchByEstudiante(usuarioDTO.getEstudiante().getIdEstudiante());
        if (!optionalEstudiante.isPresent()) {
            return new ResponseEntity<>(new Message("No se encontro el id del estudiante o no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new Message("Se encontro el estudiante", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> obtenerSoloPadres(UsuarioDTO usuarioDTO) {
        logger.info("Ejecutando funcion de: obtener solo padres");

        Optional<Padre> optionalPadre = usuarioRepository.searchByPadre(usuarioDTO.getPadre().getIdPadre());
        if (!optionalPadre.isPresent()) {
            return new ResponseEntity<>(new Message("El id del padre nose encontro o no existe",TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new Message("Se encontro el padre", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> obtenerSoloProfesores(UsuarioDTO usuarioDTO) {
        logger.info("Ejecutando funcion de: obtener solo profesores");

        Optional<Profesor> optionalProfesor = usuarioRepository.searchByProfesor(usuarioDTO.getProfesor().getIdProfesor());
        if (!optionalProfesor.isPresent()) {
            return new ResponseEntity<>(new Message("El id del profesor no se encontro o no existe", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new Message("Se encontro el profesor", TypesResponse.SUCCESS), HttpStatus.OK);
    }*/

}
