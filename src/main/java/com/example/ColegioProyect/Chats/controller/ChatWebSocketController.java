package com.example.ColegioProyect.Chats.controller;

import com.example.ColegioProyect.Chats.DTO.ChatMessageDto;
import com.example.ColegioProyect.Chats.DTO.WebSocketResponse;
import com.example.ColegioProyect.Chats.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@Controller
public class ChatWebSocketController {
    /*
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    //@Autowired
    private ChatService chatService;

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessageDto chatMessageDto) {
        ChatMessageDto savedMessage = chatService.saveMessage(chatMessageDto);

        String destination = "/queue/" + chatMessageDto.getIdDestinatario();
        messagingTemplate.convertAndSend(destination, savedMessage);

        String senderDestination = "/queue/" + chatMessageDto.getIdRemitente();
        messagingTemplate.convertAndSend(senderDestination, savedMessage);
    }

    @MessageMapping("/chat.register")
    @SendTo
    public WebSocketResponse register(@Payload ChatMessageDto chatMessageDto, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessageDto.getIdRemitente());

        WebSocketResponse response = new WebSocketResponse();
        response.setStatus("OK");
        response.setMessage("Usuario registrado correctamente");
        return response;
    }*/

}
