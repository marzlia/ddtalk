package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="learner_plan_objective_target")
public class LearnerPlanObjectiveTarget {

    @Id
    @GeneratedValue
    @Column(name = "internal_id")
    Long internalId;

    @Column(name = "learner_plan_id")
    Long learnerPlanId;

    @Column(name = "domain_id")
    Long domainId;

    @Column(name = "objective_id")
    Long objectiveId;

    @Column(name = "target_id")
    Long targetId;

    @Column(name = "condition_id")
    Long conditionId;

    @Column(name = "criteria_id")
    Long criteriaId;

    @Column(name = "num_targets_required")
    Long numTargetsRequired;

    public Long getLearnerPlanId() {
        return learnerPlanId;
    }

    public void setLearnerPlanId(Long learnerPlanId) {
        this.learnerPlanId = learnerPlanId;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public Long getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
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

    public Long getNumTargetsRequired() {
        return numTargetsRequired;
    }

    public void setNumTargetsRequired(Long numTargetsRequired) {
        this.numTargetsRequired = numTargetsRequired;
    }
}
