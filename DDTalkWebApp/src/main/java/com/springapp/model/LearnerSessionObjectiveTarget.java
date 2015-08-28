package com.springapp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="learner_session_objective_target")
public class LearnerSessionObjectiveTarget {

    @Id
    @GeneratedValue
    @Column(name = "learner_session_objective_target_id")
    Long learnerSessionObjectiveTargetId;

    @Column(name = "learner_session_objective_id")
    Long learnerSessionObjectiveId;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "learner_plan_objective_target_id")
    LearnerPlanObjectiveTarget learnerPlanObjectiveTarget;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "prompt_code_id")
    PromptCode promptCode;

    @Column(name = "session_value")
    Long sessionValue;

    @Column(name = "invalidated_by_retention")
    String invalidatedByRetention;

    @Column(name = "mastered")
    String mastered;

    @Column(name = "mastery_date")
    Date masteryDate;

    public Long getLearnerSessionObjectiveTargetId() {
        return learnerSessionObjectiveTargetId;
    }

    public void setLearnerSessionObjectiveTargetId(Long learnerSessionObjectiveTargetId) {
        this.learnerSessionObjectiveTargetId = learnerSessionObjectiveTargetId;
    }

    public Long getLearnerSessionObjectiveId() {
        return learnerSessionObjectiveId;
    }

    public void setLearnerSessionObjectiveId(Long learnerSessionObjectiveId) {
        this.learnerSessionObjectiveId = learnerSessionObjectiveId;
    }

    public LearnerPlanObjectiveTarget getLearnerPlanObjectiveTarget() {
        return learnerPlanObjectiveTarget;
    }

    public void setLearnerPlanObjectiveTarget(LearnerPlanObjectiveTarget learnerPlanObjectiveTarget) {
        this.learnerPlanObjectiveTarget = learnerPlanObjectiveTarget;
    }

    public PromptCode getPromptCode() {
        return promptCode;
    }

    public void setPromptCode(PromptCode promptCode) {
        this.promptCode = promptCode;
    }

    public Long getSessionValue() {
        return sessionValue;
    }

    public void setSessionValue(Long sessionValue) {
        this.sessionValue = sessionValue;
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

    public String getInvalidatedByRetention() {
        return invalidatedByRetention;
    }

    public void setInvalidatedByRetention(String invalidatedByRetention) {
        this.invalidatedByRetention = invalidatedByRetention;
    }
}
