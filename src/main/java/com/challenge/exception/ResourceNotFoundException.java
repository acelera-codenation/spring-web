package com.challenge.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException implements Serializable {

    public static final String MSG_RECURSO_NAO_ENCONTRADO = "Recurso não encontrado.";

    public String getMessage() {
        return MSG_RECURSO_NAO_ENCONTRADO;
    }
}