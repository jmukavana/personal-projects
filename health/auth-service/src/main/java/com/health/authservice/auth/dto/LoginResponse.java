package com.health.authservice.auth.dto;

public record LoginResponse(
        String token
) {
    public LoginResponse( final String token) {
        this.token = token;
    }
}
