package com.itechart.citydistance.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class NoConnectionBetweenCitiesException extends RuntimeException {

    @Getter
    private HttpStatus status;

    public NoConnectionBetweenCitiesException(String first, String second) {
        super(String.format("There is no connection between %s and %s.", first, second));
        this.status = UNPROCESSABLE_ENTITY;
    }

}
