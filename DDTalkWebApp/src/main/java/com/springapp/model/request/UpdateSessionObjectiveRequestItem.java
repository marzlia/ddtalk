package com.springapp.model.request;

/**
 * Created by peter on 8/2/15.
 */
public class UpdateSessionObjectiveRequestItem {

    String learnerSessionObjectiveId;
    String learnerSessionId;
    String learnerPlanObjectiveId;
    Long sessionValue;

    public String getLearnerSessionObjectiveId() {
        return learnerSessionObjectiveId;
    }

    public void setLearnerSessionObjectiveId(String learnerSessionObjectiveId) {
        this.learnerSessionObjectiveId = learnerSessionObjectiveId;
    }

    public String getLearnerSessionId() {
        return learnerSessionId;
    }

    public void setLearnerSessionId(String learnerSessionId) {
        this.learnerSessionId = learnerSessionId;
    }

    public String getLearnerPlanObjectiveId() {
        return learnerPlanObjectiveId;
    }

    public void setLearnerPlanObjectiveId(String learnerPlanObjectiveId) {
        this.learnerPlanObjectiveId = learnerPlanObjectiveId;
    }

    public Long getSessionValue() {
        return sessionValue;
    }

    public void setSessionValue(Long sessionValue) {
        this.sessionValue = sessionValue;
    }
}
