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

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "target_id")
    Target target;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "condition_id")
    Condition condition;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "criteria_id")
    Criteria criteria;

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

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
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

    public Long getMastery_value() {
        return mastery_value;
    }

    public void setMastery_value(Long mastery_value) {
        this.mastery_value = mastery_value;
    }
}
