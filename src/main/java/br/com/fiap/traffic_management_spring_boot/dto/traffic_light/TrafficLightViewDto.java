package br.com.fiap.traffic_management_spring_boot.dto.traffic_light;

import br.com.fiap.traffic_management_spring_boot.model.TrafficLight;

import java.util.Date;

public record TrafficLightViewDto(
        Long id,
        String status,
        Double lat,
        Double lng,
        Date createdAt,
        Date updatedAt
) {

    public TrafficLightViewDto(TrafficLight trafficLight){
        this(
                trafficLight.getId(),
                trafficLight.getStatus(),
                trafficLight.getLat(),
                trafficLight.getLng(),
                trafficLight.getCreatedAt(),
                trafficLight.getUpdatedAt()
        );
    }
}
