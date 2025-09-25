package com.jamlech.auth_service.user.request;

import java.time.LocalDate;

public record ProfileUpdateRequest(
        String firstName,
        String lastName,
        LocalDate dateOfBirth
) {
}
