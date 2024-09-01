package br.com.fiap.traffic_management_spring_boot.dto.traffic_light;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TrafficLightCreateDto(
        @NotBlank(message = "Status field is required")
        String status,

        @NotNull(message = "Lat field is required")
        Double lat,

        @NotNull(message = "Lat field is required")
        Double lng
) {
}
