package org.viettel.ocs.rulemgt.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.google.gson.JsonSyntaxException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionhandler {
    @ExceptionHandler(CorrelationException.class)
    public ResponseEntity<String> handleCorrelationException(Exception e){
        return ResponseEntity.status(432).body(e.getMessage());
    }

    @ExceptionHandler(JsonSyntaxException.class)
    public ResponseEntity<String> handleJsonSyntaxException(Exception e){
        log.warn(e.getMessage());
        return ResponseEntity.status(400).body("Invalid JSON format");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnwantedException(Exception e){
        log.error("Unknow error", e);
        return ResponseEntity.status(500).body("Unknow error");
    }
}
