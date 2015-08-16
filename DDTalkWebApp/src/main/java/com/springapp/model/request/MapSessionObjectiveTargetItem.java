package com.springapp.model.request;

import com.springapp.model.LearnerPlanObjective;
import com.springapp.model.LearnerPlanObjectiveTarget;
import com.springapp.model.LearnerSessionObjective;
import com.springapp.model.LearnerSessionObjectiveTarget;

/**
 * Created by peter on 8/2/15.
 */
public class MapSessionObjectiveTargetItem {

    LearnerPlanObjectiveTarget planObjectiveTarget;
    LearnerSessionObjectiveTarget sessionObjectiveTarget;

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
}
