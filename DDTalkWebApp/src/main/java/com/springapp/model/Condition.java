package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="conditions")
public class Condition {

    @Id
    @GeneratedValue
    @Column(name = "condition_id")
    Long conditionId;

    @Column(name = "description")
    String description;

    public Long getConditionId() {
        return conditionId;
    }

    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

