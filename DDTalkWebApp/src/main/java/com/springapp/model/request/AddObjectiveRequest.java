package com.springapp.model.request;

/**
 * Created by peter on 8/2/15.
 */
public class AddObjectiveRequest {

    String learnerPlanId;
    String objectiveId;
    String domainId;
    String objectiveTypeId;
    String criteriaId;
    String retentionProbeEnabled;
    String retentionProbeNumDays;

    public String getLearnerPlanId() {
        return learnerPlanId;
    }

    public void setLearnerPlanId(String learnerPlanId) {
        this.learnerPlanId = learnerPlanId;
    }

    public String getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(String objectiveId) {
        this.objectiveId = objectiveId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getObjectiveTypeId() {
        return objectiveTypeId;
    }

    public void setObjectiveTypeId(String objectiveTypeId) {
        this.objectiveTypeId = objectiveTypeId;
    }

    public String getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(String criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getRetentionProbeEnabled() {
        return retentionProbeEnabled;
    }

    public void setRetentionProbeEnabled(String retentionProbeEnabled) {
        this.retentionProbeEnabled = retentionProbeEnabled;
    }

    public String getRetentionProbeNumDays() {
        return retentionProbeNumDays;
    }

    public void setRetentionProbeNumDays(String retentionProbeNumDays) {
        this.retentionProbeNumDays = retentionProbeNumDays;
    }
}
