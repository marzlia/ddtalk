package com.springapp.reports;

import com.springapp.model.LearnerPlanObjective;

import java.util.List;

/**
 * Created by peter on 8/24/15.
 */
public class ReportLearnerPlan {

    String studentName;
    String initialAssessmentDate;
    String studentNumber;
    String treatmentProvider;
    String treatmentPlanDate;
    String treatmentFrequency;
    String school;
    String treatmentPlanDescription;
    String treatmentPlanTitle;

    List<LearnerPlanObjective> objectives;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getInitialAssessmentDate() {
        return initialAssessmentDate;
    }

    public void setInitialAssessmentDate(String initialAssessmentDate) {
        this.initialAssessmentDate = initialAssessmentDate;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getTreatmentProvider() {
        return treatmentProvider;
    }

    public void setTreatmentProvider(String treatmentProvider) {
        this.treatmentProvider = treatmentProvider;
    }

    public String getTreatmentPlanDate() {
        return treatmentPlanDate;
    }

    public void setTreatmentPlanDate(String treatmentPlanDate) {
        this.treatmentPlanDate = treatmentPlanDate;
    }

    public String getTreatmentFrequency() {
        return treatmentFrequency;
    }

    public void setTreatmentFrequency(String treatmentFrequency) {
        this.treatmentFrequency = treatmentFrequency;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTreatmentPlanDescription() {
        return treatmentPlanDescription;
    }

    public void setTreatmentPlanDescription(String treatmentPlanDescription) {
        this.treatmentPlanDescription = treatmentPlanDescription;
    }

    public String getTreatmentPlanTitle() {
        return treatmentPlanTitle;
    }

    public void setTreatmentPlanTitle(String treatmentPlanTitle) {
        this.treatmentPlanTitle = treatmentPlanTitle;
    }

    public List<LearnerPlanObjective> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<LearnerPlanObjective> objectives) {
        this.objectives = objectives;
    }
}
