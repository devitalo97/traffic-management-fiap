package br.com.fiap.traffic_management_spring_boot.dto.traffic_light;

public record TrafficLightUpdateDto(
        Long id,
        String status,
        Double lat,
        Double lng
) {

}
