package com.springapp.model.request;

/**
 * Created by peter on 8/2/15.
 */
public class UpdateSessionObjectiveItem {

    String sessionObjectiveId;
    Long sessionValue;
    String forcedMastered;

    public String getSessionObjectiveId() {
        return sessionObjectiveId;
    }

    public void setSessionObjectiveId(String sessionObjectiveId) {
        this.sessionObjectiveId = sessionObjectiveId;
    }

    public Long getSessionValue() {
        return sessionValue;
    }

    public void setSessionValue(Long sessionValue) {
        this.sessionValue = sessionValue;
    }

    public String getForcedMastered() {
        return forcedMastered;
    }

    public void setForcedMastered(String forcedMastered) {
        this.forcedMastered = forcedMastered;
    }
}
