package com.example.ColegioProyect.Eventos.Controller;

import com.example.ColegioProyect.Eventos.Model.Evento;
import com.example.ColegioProyect.Eventos.Model.EventoDTO;
import com.example.ColegioProyect.Eventos.Model.EventoRespository;
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
public class EventoService {
    private static final Logger logger = LoggerFactory.getLogger(EventoService.class);

    public final EventoRespository eventoRepository;
    public final UsuarioRepository usuarioRepository;

    @Autowired
    public EventoService(EventoRespository eventoRepository, UsuarioRepository usuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> obtenerTodosLosEventos () {
        logger.info("Ejecutando funcion: Obtener todos los eventos");
        return new ResponseEntity<>(new Message(eventoRepository.findAll(),"Listado de eventos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> registrarEvento(EventoDTO eventoDTO){
        logger.info("Ejecutando funcion: Registrar evento");

        eventoDTO.setTitulo(eventoDTO.getTitulo());
        if (eventoDTO.getTitulo().length() > 45){
            return new ResponseEntity<>(new Message("El titulo excede los 45 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        eventoDTO.setDescripcion(eventoDTO.getDescripcion());
        if (eventoDTO.getDescripcion().length() > 150) {
            return new ResponseEntity<>(new Message("La descripcion excede los 150 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        eventoDTO.setColorEtiqueta(eventoDTO.getColorEtiqueta());
        if (eventoDTO.getColorEtiqueta().length() > 12) {
            return new ResponseEntity<>(new Message("El color de la etiqueta excede los 12 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(eventoDTO.getIdUsuario());
        if (usuarioOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("El usuario no se encontro o existente", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioOptional.get();

        if (!"administrador".equalsIgnoreCase(usuario.getTipoUsuario())) {
            return new ResponseEntity<>(new Message("El usuario no es correcto", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Evento evento = new Evento(eventoDTO.getTitulo(), eventoDTO.getDescripcion(), eventoDTO.getColorEtiqueta(), usuario);
        evento = eventoRepository.saveAndFlush(evento);

        if (evento == null) {
            return new ResponseEntity<>(new Message("Error al guardar evento", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(evento, "Se registro el evento correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> editarEvento(EventoDTO eventoDTO){
        logger.info("Ejecutando funcion: Editar evento");

        eventoDTO.setTitulo(eventoDTO.getTitulo());
        if (eventoDTO.getTitulo().length() > 45){
            return new ResponseEntity<>(new Message("El titulo excede los 45 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        eventoDTO.setDescripcion(eventoDTO.getDescripcion());
        if (eventoDTO.getDescripcion().length() > 150) {
            return new ResponseEntity<>(new Message("La descripcion excede los 150 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        eventoDTO.setColorEtiqueta(eventoDTO.getColorEtiqueta());
        if (eventoDTO.getColorEtiqueta().length() > 12) {
            return new ResponseEntity<>(new Message("El color de la etiqueta excede los 12 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(eventoDTO.getIdUsuario());
        if (usuarioOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("El usuario no se encontro o existente", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioOptional.get();

        if (!"administrador".equalsIgnoreCase(usuario.getTipoUsuario())) {
            return new ResponseEntity<>(new Message("El usuario no es correcto", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Evento> eventoOptional = eventoRepository.findById(eventoDTO.getIdEvento());
        if (eventoOptional.isEmpty()) {
            return new ResponseEntity<>(new Message("No se encontro el id del evento",TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }

        Evento evento = eventoOptional.get();
        evento.setTitulo(eventoDTO.getTitulo());
        evento.setDescripcion(eventoDTO.getDescripcion());
        evento.setColorEtiqueta(eventoDTO.getColorEtiqueta());
        if (evento == null) {
            return new ResponseEntity<>(new Message("Error al modificar evento", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(evento,"El evento se modifico correctamente",TypesResponse.SUCCESS), HttpStatus.OK);
    }

}
