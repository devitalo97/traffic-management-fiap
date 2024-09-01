package br.com.fiap.traffic_management_spring_boot.dto.account;

import br.com.fiap.traffic_management_spring_boot.model.account.Role;
import jakarta.validation.constraints.*;

public record AccountCreateDto(
        @NotBlank(message = "Name field is required")
        String name,

        @NotNull(message = "Email field is required")
        @Email(message = "Email field is invalid")
        String email,

        @NotNull(message = "Password field is required")
        @Size(min = 6, max = 20, message = "Password field needed at least 6 characters")
        String password,

        @NotNull(message = "Role field is required")
        @Pattern(regexp = "ACCOUNT|ADMIN", message = "Role field must be ACCOUNT or ADMIN")
        String role
) {
}
