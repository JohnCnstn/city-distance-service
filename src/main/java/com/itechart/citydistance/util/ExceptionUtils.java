package com.itechart.citydistance.util;

import com.itechart.citydistance.exception.EntityNotFoundException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionUtils {

    public static EntityNotFoundException notFoundEntity(String message) {
        return new EntityNotFoundException(message);
    }

}
