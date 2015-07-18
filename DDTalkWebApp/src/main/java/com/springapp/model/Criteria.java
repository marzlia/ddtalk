package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="criteria")
public class Criteria {

    @Id
    @GeneratedValue
    @Column(name = "criteria_id")
    Long criteriaId;

    @Column(name = "description")
    String description;

    public Long getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Long criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
