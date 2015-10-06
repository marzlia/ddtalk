package com.springapp.model.request;

import java.util.Date;

/**
 * Created by peter on 8/2/15.
 */
public class SessionReportRequestDates {
    Date startDate;
    Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
