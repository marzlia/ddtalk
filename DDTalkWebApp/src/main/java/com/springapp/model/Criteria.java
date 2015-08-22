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

    @Column(name = "consecutive_to_mastered")
    Long consecutiveToMastered;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "objective_type_id")
    ObjectiveType objectiveType;

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

    public Long getConsecutiveToMastered() {
        return consecutiveToMastered;
    }

    public void setConsecutiveToMastered(Long consecutiveToMastered) {
        this.consecutiveToMastered = consecutiveToMastered;
    }

    public ObjectiveType getObjectiveType() {
        return objectiveType;
    }

    public void setObjectiveType(ObjectiveType objectiveType) {
        this.objectiveType = objectiveType;
    }
}
