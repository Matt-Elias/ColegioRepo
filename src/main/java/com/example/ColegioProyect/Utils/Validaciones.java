package com.example.ColegioProyect.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class Validaciones {
    private static final Logger logger = LoggerFactory.getLogger(Validaciones.class);

    public static ResponseEntity<Object> validarMayorLongitud (String nombre_dato, String valor, int longitud) {
        if (valor.length() > longitud) {
            logger.warn("El campo '{}' excede el numero de caracteres ({})", nombre_dato, longitud);
            return new ResponseEntity<>(new Message("El campo: '"+ nombre_dato + "' excede el numero de caracteres (" + longitud+ ")", TypesResponse.WARNING ), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public static ResponseEntity<Object> validarDiferenteLongitud (String nombre_dato, String valor, int longitud_igual) {
        if (valor.length() != longitud_igual) {
            logger.warn("El campo '{}' debe contener ({}) digitos", nombre_dato, longitud_igual);
            return new ResponseEntity<>(new Message("El campo: '" + nombre_dato + "' debe contener (" + longitud_igual + ") digitos", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public static ResponseEntity<Object> validarRangoLongitud (String nombre_dato, String valor, int minimo, int maximo) {
        if (valor.length() < minimo || valor.length() > maximo) {
            logger.warn("El campo '{}' esta fuera del rango del numero de caracteres", nombre_dato);
            return new ResponseEntity<>(new Message("El campo: '" + nombre_dato + "' esta fuera del rango del numero de carateres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public static ResponseEntity<Object> excepcionDatosNulos () {
        logger.error("Verifica tu peticion, algun dato enviado es nulo");
        return new ResponseEntity<>(new Message("Verifica tu peticion, puede que algun dato enviado es nulo", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> excepcionDatoYaRegistrado (String nombre_dato) {
        logger.warn("{} ya esta registrado el dato: ", nombre_dato);
        return new ResponseEntity<>(new Message(nombre_dato + " ya esta registrado", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> excepcionObjetoInexistente (String nombre) {
        logger.warn("No se encontro: {}", nombre);
        return new ResponseEntity<>(new Message("No se encontro: " + nombre, TypesResponse.WARNING), HttpStatus.NOT_FOUND);
    }

    public static boolean sonNulos(ArrayList<String> datos) {
        if (datos == null || datos.isEmpty()) {
            return true;
        }
        for (String dato : datos) {
            if (dato == null || dato.replace( " ", "" ).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static ResponseEntity<Object>esNumerico(String nombre_dato, String valor) {
        try {
            Long.parseLong(nombre_dato);
        } catch (NumberFormatException e) {
            logger.warn("El campo '{}' no es un numero", nombre_dato);
            return new ResponseEntity<>(new Message("El campo: "+ nombre_dato +" no es un numero", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public static boolean esIgualOMenorACero (Long id){
        return id <= 0;
    }


}
