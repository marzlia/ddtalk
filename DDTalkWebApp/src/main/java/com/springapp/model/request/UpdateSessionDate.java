package com.springapp.model.request;

import java.util.Date;

/**
 * Created by peter on 9/8/15.
 */
public class UpdateSessionDate {
    String sessionId;
    Date sessionDate;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }
}
