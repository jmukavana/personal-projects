package com.jamlech.auth_service.handler;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponse(
        String code, String message,
        List<ValidationError> validationErrors
) {
}
