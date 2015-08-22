package com.springapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="learner_plan_objective")
public class LearnerPlanObjective {

    @Id
    @GeneratedValue
    @Column(name = "learner_plan_objective_id")
    Long learnerPlanObjectiveId;

    @Column(name = "learner_plan_id")
    Long learnerPlanId;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "objective_id")
    Objective objective;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "objective_type_id")
    ObjectiveType objectiveType;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "condition_id")
    Condition condition;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "criteria_id")
    Criteria criteria;

    @Column(name = "retention_probe_days_to_recheck")
    Long retentionProbeDaysToRecheck;

    @Column(name = "retention_probe_enabled")
    String retentionProbeEnabled;

    @Column(name = "mastery_value")
    Long masteryValue;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "learner_plan_objective_id")
    List<LearnerPlanObjectiveTarget> learnerPlanObjectiveTarget;

    public Long getLearnerPlanObjectiveId() {
        return learnerPlanObjectiveId;
    }

    public void setLearnerPlanObjectiveId(Long learnerPlanObjectiveId) {
        this.learnerPlanObjectiveId = learnerPlanObjectiveId;
    }

    public Long getLearnerPlanId() {
        return learnerPlanId;
    }

    public void setLearnerPlanId(Long learnerPlanId) {
        this.learnerPlanId = learnerPlanId;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public ObjectiveType getObjectiveType() {
        return objectiveType;
    }

    public void setObjectiveType(ObjectiveType objectiveType) {
        this.objectiveType = objectiveType;
    }

    public List<LearnerPlanObjectiveTarget> getLearnerPlanObjectiveTarget() {
        return learnerPlanObjectiveTarget;
    }

    public void setLearnerPlanObjectiveTarget(List<LearnerPlanObjectiveTarget> learnerPlanObjectiveTarget) {
        this.learnerPlanObjectiveTarget = learnerPlanObjectiveTarget;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public Long getRetentionProbeDaysToRecheck() {
        return retentionProbeDaysToRecheck;
    }

    public void setRetentionProbeDaysToRecheck(Long retentionProbeDaysToRecheck) {
        this.retentionProbeDaysToRecheck = retentionProbeDaysToRecheck;
    }

    public String getRetentionProbeEnabled() {
        return retentionProbeEnabled;
    }

    public void setRetentionProbeEnabled(String retentionProbeEnabled) {
        this.retentionProbeEnabled = retentionProbeEnabled;
    }

    public Long getMasteryValue() {
        return masteryValue;
    }

    public void setMasteryValue(Long masteryValue) {
        this.masteryValue = masteryValue;
    }
}
