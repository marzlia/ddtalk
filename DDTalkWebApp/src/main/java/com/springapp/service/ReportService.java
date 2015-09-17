package com.springapp.service;

import com.springapp.model.*;
import com.springapp.reports.ReportLearnerPlan;
import com.springapp.reports.ReportLearnerSession;
import com.springapp.reports.ReportSessionData;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by peter on 8/29/15.
 */
@Service
public class ReportService {

    @Autowired
    LearnerService learnerService;

    @Autowired
    LearnerSessionService learnerSessionService;

    @Autowired
    LearnerPlanService learnerPlanService;

    public Map<String,Object> generateLearnerPlanData(LearnerPlan learnerPlan, LoginUser loginUser) {

        learnerPlanService.updateMasteryForLearnerPlan(learnerPlan);

        Learner learner = learnerService.getLearner(learnerPlan.getLearnerId());

        List<ReportLearnerPlan> reportLearnerPlans = new ArrayList<ReportLearnerPlan>();
        ReportLearnerPlan p = new ReportLearnerPlan();
        p.setTreatmentProvider(loginUser.getFullName());

        String fullName = learner.getFirstName() + " " + learner.getLastName();
        p.setStudentName(fullName);
        p.setInitialAssessmentDate("Not started");
        p.setTreatmentPlanTitle("Treatment Plan for " + fullName);
        p.setTreatmentPlanDescription(learnerPlan.getTreatmentDescription());

        p.setSchool(learner.getSchool());
        p.setStudentNumber(learner.getStudentId());
        p.setTreatmentFrequency(learnerPlan.getTreatmentFrequency());
        p.setTreatmentPlanDate(learnerPlan.getDateStartPlan().toString());

        p.setObjectives(learnerPlan.getLearnerPlanObjectiveList());

        reportLearnerPlans.add(p);


        Map<String,Object> parameterMap = new HashMap<String,Object>();
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(reportLearnerPlans, false);
        parameterMap.put("datasource", JRdataSource);

        return parameterMap;
    }

    public Map<String,Object> generateLearnerSessionData(LearnerPlan learnerPlan, LoginUser loginUser) {

        learnerPlanService.updateMasteryForLearnerPlan(learnerPlan);

        Learner learner = learnerService.getLearner(learnerPlan.getLearnerId());

        List<ReportLearnerSession> reportLearnerSessions = new ArrayList<ReportLearnerSession>();

        for (LearnerPlanObjective learnerPlanObjective : learnerPlan.getLearnerPlanObjectiveList()) {

            ReportLearnerSession p = new ReportLearnerSession();
            p.setTreatmentProvider(loginUser.getFullName());

            String fullName = learner.getFirstName() + " " + learner.getLastName();
            p.setStudentName(fullName);
            p.setInitialAssessmentDate("Not started");
            p.setTreatmentPlanTitle("Treatment Plan for " + fullName);
            p.setTreatmentPlanDescription(learnerPlan.getTreatmentDescription());

            p.setSchool(learner.getSchool());
            p.setStudentNumber(learner.getStudentId());
            p.setTreatmentFrequency(learnerPlan.getTreatmentFrequency());
            p.setTreatmentPlanDate(learnerPlan.getDateStartPlan().toString());

            p.setObjective(learnerPlanObjective);

            List<LearnerSessionObjective> objectiveSessions = learnerSessionService.getSessionsForPlanObjective(learnerPlanObjective);
            List<ReportSessionData> reportSessionDataList = new ArrayList<ReportSessionData>();
            for (LearnerSessionObjective sessionObjective : objectiveSessions) {
                ReportSessionData sessionData = new ReportSessionData();
                Date sessionDate = learnerSessionService.getLearnerSession(sessionObjective.getLearnerSessionId()).getSessionDate();
                sessionData.setSessionDate(sessionDate);
                sessionData.setSessionValue(sessionObjective.getSessionValue());
                reportSessionDataList.add(sessionData);
            }
            p.setReportSessionDataList(reportSessionDataList);

            reportLearnerSessions.add(p);
        }

        Map<String,Object> parameterMap = new HashMap<String,Object>();
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(reportLearnerSessions, false);
        parameterMap.put("datasource", JRdataSource);

        return parameterMap;
    }

}
