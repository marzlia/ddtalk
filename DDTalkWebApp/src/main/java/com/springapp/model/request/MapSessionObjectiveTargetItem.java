package com.springapp.model.request;

import com.springapp.model.LearnerPlanObjective;
import com.springapp.model.LearnerPlanObjectiveTarget;
import com.springapp.model.LearnerSessionObjective;
import com.springapp.model.LearnerSessionObjectiveTarget;

import java.util.Date;

/**
 * Created by peter on 8/2/15.
 */
public class MapSessionObjectiveTargetItem {

    LearnerPlanObjectiveTarget planObjectiveTarget;
    LearnerSessionObjectiveTarget sessionObjectiveTarget;

    String editState; // Y or N
    String tableRowClass;
    Date retentionTestDate;


    public LearnerPlanObjectiveTarget getPlanObjectiveTarget() {
        return planObjectiveTarget;
    }

    public void setPlanObjectiveTarget(LearnerPlanObjectiveTarget planObjectiveTarget) {
        this.planObjectiveTarget = planObjectiveTarget;
    }

    public LearnerSessionObjectiveTarget getSessionObjectiveTarget() {
        return sessionObjectiveTarget;
    }

    public void setSessionObjectiveTarget(LearnerSessionObjectiveTarget sessionObjectiveTarget) {
        this.sessionObjectiveTarget = sessionObjectiveTarget;
    }

    public String getEditState() {
        return editState;
    }

    public void setEditState(String editState) {
        this.editState = editState;
    }

    public String getTableRowClass() {
        return tableRowClass;
    }

    public void setTableRowClass(String tableRowClass) {
        this.tableRowClass = tableRowClass;
    }

    public Date getRetentionTestDate() {
        return retentionTestDate;
    }

    public void setRetentionTestDate(Date retentionTestDate) {
        this.retentionTestDate = retentionTestDate;
    }
}
