package com.springapp.model.request;

/**
 * Created by peter on 8/2/15.
 */
public class UpdateSessionObjectiveTargetRequestItem {

    String learnerSessionObjectiveTargetId;
    String promptCodeId;
    Long sessionValue;

    public String getLearnerSessionObjectiveTargetId() {
        return learnerSessionObjectiveTargetId;
    }

    public void setLearnerSessionObjectiveTargetId(String learnerSessionObjectiveTargetId) {
        this.learnerSessionObjectiveTargetId = learnerSessionObjectiveTargetId;
    }

    public String getPromptCodeId() {
        return promptCodeId;
    }

    public void setPromptCodeId(String promptCodeId) {
        this.promptCodeId = promptCodeId;
    }

    public Long getSessionValue() {
        return sessionValue;
    }

    public void setSessionValue(Long sessionValue) {
        this.sessionValue = sessionValue;
    }
}
