package com.springapp.model.request;

/**
 * Created by peter on 8/2/15.
 */
public class AddObjectiveTargetsRequest {

    String learnerPlanId;
    String planObjectiveId;
    String targets;

    public String getLearnerPlanId() {
        return learnerPlanId;
    }

    public void setLearnerPlanId(String learnerPlanId) {
        this.learnerPlanId = learnerPlanId;
    }

    public String getPlanObjectiveId() {
        return planObjectiveId;
    }

    public void setPlanObjectiveId(String planObjectiveId) {
        this.planObjectiveId = planObjectiveId;
    }

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }
}
