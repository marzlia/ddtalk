package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="learner_plan_objective_target")
public class LearnerPlanObjectiveTarget {

    @Id
    @GeneratedValue
    @Column(name = "learner_plan_objective_target_id")
    Long learnerPlanObjectiveDataId;

    @Column(name = "learner_plan_objective_id")
    Long learnerPlanObjectiveId;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "target_id")
    Target target;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "prompt_code_id")
    PromptCode promptCode;

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

    public PromptCode getPromptCode() {
        return promptCode;
    }

    public void setPromptCode(PromptCode promptCode) {
        this.promptCode = promptCode;
    }
}
