package com.springapp.model.request;

import com.springapp.model.Condition;
import com.springapp.model.Criteria;

/**
 * Created by peter on 8/2/15.
 */
public class UpdateObjectiveRequestItem {

    String planObjectiveId;
    String conditionId;
    Long masteryValue;

    public String getPlanObjectiveId() {
        return planObjectiveId;
    }

    public void setPlanObjectiveId(String planObjectiveId) {
        this.planObjectiveId = planObjectiveId;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public Long getMasteryValue() {
        return masteryValue;
    }

    public void setMasteryValue(Long masteryValue) {
        this.masteryValue = masteryValue;
    }
}
