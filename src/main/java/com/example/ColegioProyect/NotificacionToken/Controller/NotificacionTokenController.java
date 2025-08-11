package com.example.ColegioProyect.NotificacionToken.Controller;

import com.example.ColegioProyect.NotificacionToken.Model.NotificacionTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/notificacionToken")
public class NotificacionTokenController {

    private final NotificacionTokenService notificacionTokenService;

    @Autowired
    public NotificacionTokenController(NotificacionTokenService notificacionTokenService) {
        this.notificacionTokenService = notificacionTokenService;
    }

    @PostMapping("/registrarDispositivoToken")
    public ResponseEntity<Object> registrarDispositivoToken (@Validated({NotificacionTokenDTO.RegistrarToken.class}) @RequestBody NotificacionTokenDTO notificacionTokenDTO) {
        return notificacionTokenService.registrarDispositivoToken(notificacionTokenDTO);
    }

    @PostMapping("/enviarNotificacionAsistencia")
    public ResponseEntity<Object> enviarNotificacionAsistencia (@RequestBody Map<String, Object> request) {
        Long idEstudiante = Long.valueOf(request.get("idEstudiante").toString());
        String nombreEstudiante = request.get("nombreEstudiante").toString();
        String tipoRegistro = request.get("tipoRegistro").toString();
        Instant fechaHora = Instant.parse(request.get("fechaHora").toString());

        return notificacionTokenService.enviarNotificacionAsistencia(idEstudiante, nombreEstudiante, tipoRegistro, fechaHora);
    }

    @PostMapping("/limpiarTokensInvalidos")
    public ResponseEntity<Object> limpiarTokensInvalidos(@RequestBody NotificacionTokenDTO notificacionTokenDTO) {
        return notificacionTokenService.limpiarTokensInvalidosUsuario(notificacionTokenDTO);
    }

}
