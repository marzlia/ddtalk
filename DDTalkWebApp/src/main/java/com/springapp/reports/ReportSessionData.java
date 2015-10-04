package com.springapp.reports;

import java.util.Date;

/**
 * Created by peter on 9/16/15.
 */
public class ReportSessionData {
    String sessionDate;
    Long sessionValue;

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Long getSessionValue() {
        return sessionValue;
    }

    public void setSessionValue(Long sessionValue) {
        this.sessionValue = sessionValue;
    }
}
