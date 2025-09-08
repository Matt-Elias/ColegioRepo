package com.example.ColegioProyect.Cloudinary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cloudinary/imagen")
public class CloudinaryController {

    private final CloudinaryService cloudinaryService;

    @Autowired
    public CloudinaryController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/subir")
    public ResponseEntity<Object> subirImagen (@RequestParam("file") MultipartFile file) {
        return cloudinaryService.subirImagen(file);
    }

    @DeleteMapping("/eliminar/{publicId}")
    public ResponseEntity<Object> eliminarImagen (@PathVariable String publicId) {
        return cloudinaryService.eliminarImagen(publicId);
    }

}
