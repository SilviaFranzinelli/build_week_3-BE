package it.epicode.EpicEnergyService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> gestisciResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse errore = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errore, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> gestisciEccezioniGeneriche(Exception ex) {
        ErrorResponse errore = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Errore interno del server: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errore, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
