package org.nicolas.optimaltech.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de excepciones de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    // Manejo de excepciones de autenticación
    @ExceptionHandler({AuthenticationException.class, AuthenticationCredentialsNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleAuthenticationExceptions(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "No está autenticado");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    // Manejo de excepciones de autorización
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "No tiene permisos para acceder a este recurso");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    // Manejo de excepciones de clave duplicada o violaciones de integridad de datos
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Violación de integridad de datos: Clave duplicada o restricción violada");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT); // 409 Conflict
    }

    // Manejo de ConstraintViolationException 
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Violación de restricciones: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Manejo de excepciones SQL
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error de integridad de base de datos: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT); // 409 Conflict
    }

    // Manejo de otras excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Ha ocurrido un error interno");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
