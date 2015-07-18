package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="learner_session_target")
public class learnerSessionTarget {

    @Id
    @GeneratedValue
    @Column(name = "internalId")
    Long internalId;

    @Column(name = "learner_session_id")
    Long learnerSessionId;

    @Column(name = "objective_id")
    Long objectiveId;

    @Column(name = "target_id")
    Long targetId;

    @Column(name = "prompt_code_id")
    Long promptCodeId;

    @Column(name = "result")
    String result;

    public Long getLearnerSessionId() {
        return learnerSessionId;
    }

    public void setLearnerSessionId(Long learnerSessionId) {
        this.learnerSessionId = learnerSessionId;
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

    public Long getPromptCodeId() {
        return promptCodeId;
    }

    public void setPromptCodeId(Long promptCodeId) {
        this.promptCodeId = promptCodeId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
