package com.registrationandloginspring.model.dto;

public record UserResponse(
        Long id,
        String name,
        String email
) {}
