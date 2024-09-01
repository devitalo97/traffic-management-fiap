package br.com.fiap.traffic_management_spring_boot.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthSignInInputDto(
        @NotNull(message = "Email field is required")
        @Email(message = "Email field is invalid")
        String email,

        @NotNull(message = "Password field is required")
        @Size(min = 6, max = 20, message = "Password field needed at least 6 characters")
        String password
) {
}
