package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="learner_plan_objective_target")
public class LearnerPlanObjectiveTarget {

    @Id
    @GeneratedValue
    @Column(name = "learner_plan_objective_target_id")
    Long learnerPlanObjectiveTargetId;

    @Column(name = "learner_plan_objective_id")
    Long learnerPlanObjectiveId;

    @Column(name = "description")
    String targetDescription;

    public Long getLearnerPlanObjectiveTargetId() {
        return learnerPlanObjectiveTargetId;
    }

    public void setLearnerPlanObjectiveTargetId(Long learnerPlanObjectiveTargetId) {
        this.learnerPlanObjectiveTargetId = learnerPlanObjectiveTargetId;
    }

    public Long getLearnerPlanObjectiveId() {
        return learnerPlanObjectiveId;
    }

    public void setLearnerPlanObjectiveId(Long learnerPlanObjectiveId) {
        this.learnerPlanObjectiveId = learnerPlanObjectiveId;
    }

    public String getTargetDescription() {
        return targetDescription;
    }

    public void setTargetDescription(String targetDescription) {
        this.targetDescription = targetDescription;
    }
}
