package br.com.fiap.traffic_management_spring_boot.repository;

import br.com.fiap.traffic_management_spring_boot.model.TrafficSensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrafficSensorRepository extends JpaRepository<TrafficSensor, Long> {
}
