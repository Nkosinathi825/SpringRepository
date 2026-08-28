package com.registrationandloginspring.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(

        @NotBlank(message = "Username is required")
        @Size(
                min = 5,
                max = 20,
                message = "Username must be between 5 and 20 characters"
        )
        String
                name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email address is invalid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(
                min = 8,
                message = "Password must be at least 8 characters"
        )
        String password

) {}