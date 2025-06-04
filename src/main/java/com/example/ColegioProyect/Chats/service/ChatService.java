package com.example.ColegioProyect.Chats.service;

import com.example.ColegioProyect.Chats.DTO.ChatMessageDto;
import com.example.ColegioProyect.Conversaciones.Model.Conversacion;
import com.example.ColegioProyect.Conversaciones.Model.ConversacionRepository;
import com.example.ColegioProyect.Mensajes.Model.Mensaje;
import com.example.ColegioProyect.Mensajes.Model.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ChatService {
    /*
    //@Autowired
    private ConversacionRepository conversacionRepository;

    private MensajeRepository mensajeRepository;

    public ChatMessageDto saveMessage(ChatMessageDto chatMessageDto) {
        Conversacion conversacion = conversacionRepository.
                findById(chatMessageDto.getIdConversacion())
                .orElse(() -> {
                    Conversacion newConversacion = new Conversacion();

                    if ("profesor".equals(chatMessageDto.getTipoUsuario())) {
                        newConversacion.setFkProfesorIdProfesor(chatMessageDto.getIdRemitente());
                        newConversacion.setFkPadresIdPadre(chatMessageDto.getIdDestinatario());
                    } else {
                        newConversacion.setFkPadresIdPadre(chatMessageDto.getIdRemitente());
                        newConversacion.setFkProfesorIdProfesor(chatMessageDto.getIdDestinatario());
                    }
                    newConversacion.setFechaCreacion(new Date());
                    return conversacionRepository.save(newConversacion);
                });

        conversacion.setUltimoMensaje(new Date());
        conversacionRepository.save(conversacion);

        Mensaje mensaje = new Mensaje();
        mensaje.setContenido(chatMessageDto.getContenido());
        mensaje.setEnviadoEn(new Date());
        mensaje.setLeido(false);
        mensaje.setFkConversacion(conversacion);

        Mensaje savedMensaje = mensajeRepository.save(mensaje);

        chatMessageDto.setIdConversacion(conversacion.getIdConversacion());
        chatMessageDto.setEnviadoEn(savedMensaje.getEnviadoEn());
        chatMessageDto.setLeido(savedMensaje.getLeido());

        return chatMessageDto;
    }

    public List<ChatMessageDto> getConversacion(Long idConversacion) {
        // Implementar lógica para obtener historial de mensajes
    }*/

}
