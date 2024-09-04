package com.Coding.SecurityApplication.SecurityApplication.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecordAlreadyAvailableException.class)
    public ResponseEntity<String> handleRecordAlreadyAvailableException(RecordAlreadyAvailableException exception) {
        log.info("Error from handleRecordAlreadyAvailableException ---" + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<String> handleRecordNotFoundException(RecordNotFoundException exception) {
        log.info("Error from handleRecordNotFoundException ---" + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        log.info("Run time exception is " + e.getMessage());
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public  ResponseEntity<String> handleAccessDenied(ResourceAccessException e) {
        log.info("Don't have access to call this API ---" + e.getMessage());
        return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
    }
}
