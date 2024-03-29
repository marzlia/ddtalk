package com.springapp.model.request;

import com.springapp.model.LearnerPlanObjective;
import com.springapp.model.LearnerSessionObjective;

/**
 * Created by peter on 8/2/15.
 */
public class MapSessionObjectiveItem {

    LearnerPlanObjective planObjective;
    LearnerSessionObjective sessionObjective;
    String tableRowClass;

    public LearnerPlanObjective getPlanObjective() {
        return planObjective;
    }

    public void setPlanObjective(LearnerPlanObjective planObjective) {
        this.planObjective = planObjective;
    }

    public LearnerSessionObjective getSessionObjective() {
        return sessionObjective;
    }

    public void setSessionObjective(LearnerSessionObjective sessionObjective) {
        this.sessionObjective = sessionObjective;
    }

    public String getTableRowClass() {
        return tableRowClass;
    }

    public void setTableRowClass(String tableRowClass) {
        this.tableRowClass = tableRowClass;
    }
}
