package br.com.fiap.traffic_management_spring_boot.repository;

import br.com.fiap.traffic_management_spring_boot.model.TrafficLight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TrafficLightRepository extends JpaRepository<TrafficLight, Long> {

    //select * from traffic_light tl where (tl.lat = 1 AND tl.lng = 1);
    @Query("select tl from TrafficLight tl where (tl.lat = :lat AND tl.lng = :lng)")
    Page<TrafficLight> findManyByProximity(Double lat, Double lng, Pageable pagination);

    @Query("select tl from TrafficLight tl where tl.createdAt between :init AND :end")
    Page<TrafficLight> findManyByCreatedAtPeriod(Date init, Date end, Pageable pagination);
}
