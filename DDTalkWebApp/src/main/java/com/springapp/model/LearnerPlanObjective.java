package com.springapp.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    @Column(name = "objective_type")
    String objectiveType;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "learner_plan_objective_id")
    LearnerPlanObjectiveData learnerPlanObjectiveData;

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

    public String getObjectiveType() {
        return objectiveType;
    }

    public void setObjectiveType(String objectiveType) {
        this.objectiveType = objectiveType;
    }

    public LearnerPlanObjectiveData getLearnerPlanObjectiveData() {
        return learnerPlanObjectiveData;
    }

    public void setLearnerPlanObjectiveData(LearnerPlanObjectiveData learnerPlanObjectiveData) {
        this.learnerPlanObjectiveData = learnerPlanObjectiveData;
    }
}
