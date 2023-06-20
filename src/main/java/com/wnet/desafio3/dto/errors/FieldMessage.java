package com.wnet.desafio3.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FieldMessage {
    private String fieldName;
    private String message;
}
