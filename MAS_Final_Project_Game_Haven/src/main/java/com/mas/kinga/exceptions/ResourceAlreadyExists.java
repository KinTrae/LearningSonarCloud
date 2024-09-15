package com.mas.kinga.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@Getter
@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class ResourceAlreadyExists extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public ResourceAlreadyExists(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exists with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
