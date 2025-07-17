package com.example.ColegioProyect.Cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.ColegioProyect.Utils.Message;
import com.example.ColegioProyect.Utils.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@Transactional
public class CloudinaryService {
    private static final Logger logger = LoggerFactory.getLogger(CloudinaryService.class);

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> subirImagen (MultipartFile file) {
        logger.info("Ejecutando funcion: Subir imagen");

        try {
            Map subirResultado = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return new ResponseEntity<>(new Message(subirResultado, "Imagen subida exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Erro al subir imagen: {}" , e.getMessage());
            return new ResponseEntity<>(new Message(null, "Error al subir imagen", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> eliminarImagen (String publicId) {
        logger.info("Ejecutando funcion: Eliminar imagen");

        try {
            Map elimiarResultado = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return new ResponseEntity<>(new Message(elimiarResultado, "Imagen eliminada exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Erro al eliminar imagen: {}" , e.getMessage());
            return new ResponseEntity<>(new Message(null, e.getMessage(), TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

}
