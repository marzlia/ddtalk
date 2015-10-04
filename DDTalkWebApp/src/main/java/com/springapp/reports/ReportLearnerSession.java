package com.springapp.reports;

import com.springapp.model.LearnerPlanObjective;
import com.springapp.model.LearnerSession;

import java.util.List;

/**
 * Created by peter on 9/15/15.
 */
public class ReportLearnerSession {

    String studentName;
    String treatmentProvider;
    String domain;
    String objective;
    String condition;
    String criteria;
    String mastery;


    List<ReportSessionData> reportSessionDataList;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTreatmentProvider() {
        return treatmentProvider;
    }

    public void setTreatmentProvider(String treatmentProvider) {
        this.treatmentProvider = treatmentProvider;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getMastery() {
        return mastery;
    }

    public void setMastery(String mastery) {
        this.mastery = mastery;
    }

    public List<ReportSessionData> getReportSessionDataList() {
        return reportSessionDataList;
    }

    public void setReportSessionDataList(List<ReportSessionData> reportSessionDataList) {
        this.reportSessionDataList = reportSessionDataList;
    }
}
