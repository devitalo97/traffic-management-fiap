package br.com.fiap.traffic_management_spring_boot.model.base;


import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public class BaseModel {

    private Date createdAt;

    private Date updatedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date created_at) {
        this.createdAt = created_at;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updatedAt = updated_at;
    }

}
