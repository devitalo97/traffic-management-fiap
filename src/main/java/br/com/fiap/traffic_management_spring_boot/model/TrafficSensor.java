package br.com.fiap.traffic_management_spring_boot.model;

import br.com.fiap.traffic_management_spring_boot.model.base.BaseModel;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TRAFFIC_SENSOR")
public class TrafficSensor extends BaseModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TRAFFIC_SENSOR_SEQ"
    )
    @SequenceGenerator(
            name = "TRAFFIC_SENSOR_SEQ",
            sequenceName = "TRAFFIC_SENSOR_SEQ",
            allocationSize = 1
    )
    private Long id;
    private Double lat;
    private Double lng;
    private Float amount;

    public TrafficSensor() {
    }

    public TrafficSensor(Double lat, Double lng, Float amount, Date createdAt, Date updatedAt) {
        this.lat = lat;
        this.lng = lng;
        this.amount = amount;
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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TrafficSensor{" +
                "id=" + id +
                ", lat=" + lat +
                ", lng=" + lng +
                ", amount=" + amount +
                ", createdAt=" + this.getCreatedAt() +
                ", updatedAt=" + this.getUpdatedAt() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrafficSensor that = (TrafficSensor) o;
        return Objects.equals(id, that.id) && Objects.equals(lat, that.lat) && Objects.equals(lng, that.lng) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lat, lng, amount);
    }
}
