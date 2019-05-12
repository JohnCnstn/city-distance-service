package com.itechart.citydistance.exception;

import com.itechart.citydistance.generated.model.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoConnectionBetweenCitiesException.class)
    protected ResponseEntity<Object> handleNoConnectionBetweenCitiesException(
            NoConnectionBetweenCitiesException ex, WebRequest request) {
        return handleExceptionInternal(ex, new Error().message(ex.getMessage()), new HttpHeaders(), ex.getStatus(), request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, new Error().message(ex.getMessage()), new HttpHeaders(), ex.getStatus(), request);
    }

}
