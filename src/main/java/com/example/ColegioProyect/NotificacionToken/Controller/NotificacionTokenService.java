package com.example.ColegioProyect.NotificacionToken.Controller;

import com.example.ColegioProyect.Estudiantes.Model.EstudianteRepository;
import com.example.ColegioProyect.NotificacionToken.Model.NotificacionToken;
import com.example.ColegioProyect.NotificacionToken.Model.NotificacionTokenDTO;
import com.example.ColegioProyect.NotificacionToken.Model.NotificacionTokenRepository;
import com.example.ColegioProyect.Padres.Model.PadreRepository;
import com.example.ColegioProyect.RegistroAsistencia.Model.RegistroAsistenciaRespository;
import com.example.ColegioProyect.Usuarios.Model.Usuario;
import com.example.ColegioProyect.Usuarios.Model.UsuarioRepository;
import com.example.ColegioProyect.Utils.Message;
import com.example.ColegioProyect.Utils.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificacionTokenService {
    private static final Logger logger = LoggerFactory.getLogger(NotificacionTokenService.class);

    public final NotificacionTokenRepository notificacionTokenRepository;
    public final UsuarioRepository usuarioRepository;
    public final PadreRepository padreRepository;
    public final EstudianteRepository estudianteRepository;
    public final RegistroAsistenciaRespository registroAsistenciaRespository;

    private static final String ONESIGNAL_APP_ID = "72342681-80f1-46bb-8e88-db8bfe80c758";
    private static final String ONESIGNAL_REST_API_KEY = "key os_v2_app_oi2cnama6fdlxdui3of75aghldekay74olruvzf5zodcp2tgdlrzziaihg64mn5bpqm6v4ndpoqgu2vfv5zyd3mxav37jc7su6infxa";
    private static final String ONESIGNAL_URL = "https://api.onesignal.com/notifications";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public NotificacionTokenService (NotificacionTokenRepository notificacionTokenRepository, UsuarioRepository usuarioRepository, PadreRepository padreRepository, EstudianteRepository estudianteRepository, RegistroAsistenciaRespository registroAsistenciaRespository) {
        this.notificacionTokenRepository = notificacionTokenRepository;
        this.usuarioRepository = usuarioRepository;
        this.padreRepository = padreRepository;
        this.estudianteRepository = estudianteRepository;
        this.registroAsistenciaRespository = registroAsistenciaRespository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> registrarDispositivoToken (NotificacionTokenDTO notificacionTokenDTO) {
        logger.info("Ejecutando funcion: Registrar dispositivo token");

        try {
            // Validar que el UUID sea válido
            String uuidRegex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";

            if (!notificacionTokenDTO.getClaveOneSignalId().matches(uuidRegex)) {
                logger.warn("UUID inválido recibido: {}", notificacionTokenDTO.getClaveOneSignalId());

                // Generar UUID válido
                String nuevoUUID = java.util.UUID.randomUUID().toString();
                notificacionTokenDTO.setClaveOneSignalId(nuevoUUID);

                logger.info("UUID corregido a: {}", nuevoUUID);
            }

            // Verificar si ya existe un token para este usuario y dispositivo
            Optional<NotificacionToken> tokenExistente = notificacionTokenRepository
                    .findByIdUsuarioAndTipoDispositivo(notificacionTokenDTO.getIdUsuario(), notificacionTokenDTO.getTipoDispositivo());

            if (tokenExistente.isPresent()) {
                // Actualizar token existente
                NotificacionToken token = tokenExistente.get();
                token.setClaveOneSignalId(notificacionTokenDTO.getClaveOneSignalId());
                notificacionTokenRepository.save(token);
                logger.info("Token actualizado para usuario: {}", notificacionTokenDTO.getIdUsuario());
            } else {
                // Crear nuevo token
                //Optional<NotificacionToken> notificacionUsuario = notificacionTokenRepository.findById(notificacionTokenDTO.getIdUsuario());
                Optional<Usuario> usuarioNotificacion = usuarioRepository.findById(notificacionTokenDTO.getIdUsuario());

                NotificacionToken nuevoToken = new NotificacionToken();
                nuevoToken.setUsuario(usuarioNotificacion.get());
                //nuevoToken.setIdUsuario(notificacionTokenDTO.getIdUsuario());
                nuevoToken.setClaveOneSignalId(notificacionTokenDTO.getClaveOneSignalId());
                nuevoToken.setTipoDispositivo(notificacionTokenDTO.getTipoDispositivo());
                notificacionTokenRepository.save(nuevoToken);
                logger.info("Nuevo token registrado para usuario: {}", notificacionTokenDTO.getIdUsuario());
            }

            return new ResponseEntity<>(new Message("Token registrado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error registrando token para usuario: {}", notificacionTokenDTO.getIdUsuario(), e);
            return new ResponseEntity<>(new Message("Error al registrar token", TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> enviarNotificacionAsistencia (Long idEstudiante, String nombreEstudiante, String tipoRegistro, Instant fechaHora) {
        logger.info("Ejecutando funcion de: Enviar notificacion asistencia");

        if (!tipoRegistro.equals("Entrada") && !tipoRegistro.equals("Salida")) {
            return new ResponseEntity<>(new Message("Error el registro tiene que ser Entrada o Salida", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        List<NotificacionToken> tokensPadres = notificacionTokenRepository.findTokensPadresByEstudiante(idEstudiante);
        if (tokensPadres.isEmpty()) {
            logger.warn("No se encontraron padres registrados para el estudiante: {}", idEstudiante);
            return new ResponseEntity<>(new Message("No se encontraron padres registrados para el estudiante", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        String mensaje = tipoRegistro.equals("Entrada") ?
                "Acaba de llegar al colegio" :
                "Acaba de salir del colegio";

        String titulo = tipoRegistro + " - " + nombreEstudiante;

        List<String> playersIds = tokensPadres.stream()
                .map(NotificacionToken::getClaveOneSignalId)
                .collect(Collectors.toList());

        logger.info("Tokens encontrados para estudiante {}: {}", idEstudiante, playersIds.size());
        logger.info("Player IDs a enviar: {}", playersIds);

        boolean notificacionEnviada = enviarNotificacionOneSignal(playersIds, titulo, mensaje, fechaHora);

        if (notificacionEnviada) {
            return new ResponseEntity<>(new Message("Notificacion enviado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("Error al enviar la notificacion", TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para enviar al servicio de OneSignal API
    private boolean enviarNotificacionOneSignal (List<String> playersIds, String titulo, String mensaje, Instant fechaHora) {
        try {

            String uuidRegex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";

            List<String> playersIdsValidos = playersIds.stream()
                    .filter(id -> id.matches(uuidRegex))
                    .collect(Collectors.toList());

            List<String> playersIdsInvalidos = playersIds.stream()
                    .filter(id -> !id.matches(uuidRegex))
                    .collect(Collectors.toList());

            if (!playersIdsInvalidos.isEmpty()) {
                logger.error("Player IDs inválidos encontrados: {}", playersIdsInvalidos);
            }

            if (playersIdsValidos.isEmpty()) {
                logger.error("No hay player IDs válidos para enviar notificación");
                return false;
            }

            logger.info("Enviando notificación a {} player IDs válidos", playersIdsValidos.size());

            // Crear headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Basic " + ONESIGNAL_REST_API_KEY);

            // Crear el body de la notificación
            Map<String, Object> notificationBody  = new HashMap<>();
            notificationBody .put("app_id", ONESIGNAL_APP_ID);
            notificationBody .put("include_player_ids", playersIdsValidos);

            // Contenido de la notificación
            Map<String, String> contents = new HashMap<>();
            contents.put("es", mensaje);
            contents.put("en", mensaje);
            notificationBody.put("contents", contents);

            Map<String, String> headings = new HashMap<>();
            headings.put("es", titulo);
            headings.put("en", titulo);
            notificationBody.put("headings", headings);

            // Datos adicionales (opcional)
            Map<String, Object> data = new HashMap<>();
            data.put("tipo", "asistencia");
            data.put("fechaHora", fechaHora.toString());
            notificationBody.put("data", data);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(notificationBody, headers);

            logger.info("Enviando request a OneSignal: {}", notificationBody);

            ResponseEntity<String> response = restTemplate.postForEntity(ONESIGNAL_URL, request, String.class);

            logger.info("Respuesta OneSignal Status: {}", response.getStatusCode());
            logger.info("Respuesta OneSignal Body: {}", response.getBody());

            return response.getStatusCode().is2xxSuccessful();

            /*logger.info(" SIMULANDO envio a OneSignal:");
            String playerIds = "";
            logger.info(" Player IDs: " + playerIds);
            logger.info(" Titulo: " + titulo);
            logger.info(" Mensaje: " + mensaje);
            logger.info(" Fecha: " + fechaHora);
            return true;*/
        } catch (Exception e) {
            logger.error("Error enviando notificacion a OneSignal: {}", e.getMessage());
            logger.error("Stack trace completo: ", e);
            return false;
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> limpiarTokensInvalidosUsuario (NotificacionTokenDTO notificacionTokenDTO) {
        logger.info("Ejecutando funcion: Limpiar tokens invalidos del usuario");

        try {
            String uuidRegex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";

            List<NotificacionToken> tokensUsuario = notificacionTokenRepository.findByIdUsuario(notificacionTokenDTO.getIdUsuario());

            if (tokensUsuario.isEmpty()) {
                logger.info("No se encontraron tokens para el usuario: {}", notificacionTokenDTO.getIdUsuario());
                return new ResponseEntity<>(new Message("No se encontraron tokens para el usuario", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
            }

            List<NotificacionToken> tokensInvalidos = tokensUsuario.stream()
                    .filter(token -> !token.getClaveOneSignalId().matches(uuidRegex))
                    .collect(Collectors.toList());

            if (!tokensInvalidos.isEmpty()) {
                logger.info("Eliminando {} tokens inválidos para usuario {}", tokensInvalidos.size(), notificacionTokenDTO.getIdUsuario());
                notificacionTokenRepository.deleteAll(tokensInvalidos);

                return new ResponseEntity<>(new Message("Tokens inválidos eliminados correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
            } else {
                logger.info("No se encontraron tokens inválidos para el usuario: {}", notificacionTokenDTO.getIdUsuario());
                return new ResponseEntity<>(new Message("No se encontraron tokens inválidos", TypesResponse.SUCCESS), HttpStatus.OK);
            }

        } catch (Exception e) {
            logger.error("Error limpiando tokens inválidos para usuario: {}", notificacionTokenDTO.getIdUsuario(), e);
            return new ResponseEntity<>(new Message("Error al limpiar tokens inválidos", TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
