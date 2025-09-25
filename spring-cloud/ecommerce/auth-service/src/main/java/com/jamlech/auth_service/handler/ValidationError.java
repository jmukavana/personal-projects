package com.jamlech.auth_service.handler;

import lombok.Builder;

@Builder
public record ValidationError(
        String field,
        String code,
        String message
) {
}
