package br.com.fiap.traffic_management_spring_boot.model;


import br.com.fiap.traffic_management_spring_boot.model.base.BaseModel;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TRAFFIC_LIGHT")
public class TrafficLight extends BaseModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TRAFFIC_LIGHT_SEQ"
    )
    @SequenceGenerator(
            name = "TRAFFIC_LIGHT_SEQ",
            sequenceName = "TRAFFIC_LIGHT_SEQ",
            allocationSize = 1
    )
    private Long id;
    private Double lat;
    private Double lng;
    private String status;

    public TrafficLight() {
    }

    public TrafficLight(
            Double lat,
            Double lng,
            String status,
            Date createdAt,
            Date updatedAt
    ) {
        this.lat = lat;
        this.lng = lng;
        this.status = status;
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TrafficLight{" +
                "id=" + id +
                ", lat=" + lat +
                ", lng=" + lng +
                ", status='" + status + '\'' +
                ", createdAt=" + this.getCreatedAt() +
                ", updatedAt=" + this.getUpdatedAt() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrafficLight that = (TrafficLight) o;
        return Objects.equals(id, that.id) && Objects.equals(lat, that.lat) && Objects.equals(lng, that.lng) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lat, lng, status);
    }
}


