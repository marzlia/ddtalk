package com.springapp.model;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "retention_state")
    String retentionState;

    @Column(name = "mastered")
    String mastered;

    @Column(name = "mastery_date")
    Date masteryDate;

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

    public String getMastered() {
        return mastered;
    }

    public void setMastered(String mastered) {
        this.mastered = mastered;
    }

    public Date getMasteryDate() {
        return masteryDate;
    }

    public void setMasteryDate(Date masteryDate) {
        this.masteryDate = masteryDate;
    }

    //See TargetRetentionState for retention states
    public String getRetentionState() {
        return retentionState;
    }

    public void setRetentionState(String retentionState) {
        this.retentionState = retentionState;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(this.learnerPlanObjectiveTargetId).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LearnerPlanObjectiveTarget other = (LearnerPlanObjectiveTarget) obj;
        if (!this.learnerPlanObjectiveTargetId.equals(other.learnerPlanObjectiveTargetId))
            return false;
        return true;
    }
}
