package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="learner_plan_objective_data")
public class LearnerPlanObjectiveData {

    @Id
    @GeneratedValue
    @Column(name = "learner_plan_objective_data_id")
    Long learnerPlanObjectiveDataId;

    @Column(name = "learner_plan_objective_id")
    Long learnerPlanObjectiveId;

    @Column(name = "target_id")
    Long targetId;

    @Column(name = "condition_id")
    Long conditionId;

    @Column(name = "criteria_id")
    Long criteriaId;

    @Column(name = "mastery_value")
    Long mastery_value;

    public Long getLearnerPlanObjectiveDataId() {
        return learnerPlanObjectiveDataId;
    }

    public void setLearnerPlanObjectiveDataId(Long learnerPlanObjectiveDataId) {
        this.learnerPlanObjectiveDataId = learnerPlanObjectiveDataId;
    }

    public Long getLearnerPlanObjectiveId() {
        return learnerPlanObjectiveId;
    }

    public void setLearnerPlanObjectiveId(Long learnerPlanObjectiveId) {
        this.learnerPlanObjectiveId = learnerPlanObjectiveId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getConditionId() {
        return conditionId;
    }

    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }

    public Long getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Long criteriaId) {
        this.criteriaId = criteriaId;
    }

    public Long getMastery_value() {
        return mastery_value;
    }

    public void setMastery_value(Long mastery_value) {
        this.mastery_value = mastery_value;
    }
}
