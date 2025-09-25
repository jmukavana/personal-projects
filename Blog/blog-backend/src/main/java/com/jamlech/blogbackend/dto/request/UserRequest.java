package com.jamlech.blogbackend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record UserRequest(
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message="Last name is required")
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,
        @NotBlank(message = "Password is required")
        String password,
        String bio,
        String profilePictureUrl,
       List<Long> roleId
) {
}
