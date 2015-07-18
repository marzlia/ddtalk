package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="targets")
public class Target {

    @Id
    @GeneratedValue
    @Column(name = "target_id")
    Long targetId;

    @Column(name = "description")
    String description;

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
