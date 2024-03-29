package com.springapp.service;

import com.springapp.model.*;
import com.springapp.model.request.SessionReportRequestDates;
import com.springapp.reports.ReportLearnerPlan;
import com.springapp.reports.ReportLearnerSession;
import com.springapp.reports.ReportSessionData;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");

        learnerPlanService.updateMasteryForLearnerPlan(learnerPlan);

        Learner learner = learnerService.getLearner(learnerPlan.getLearnerId());

        List<ReportLearnerPlan> reportLearnerPlans = new ArrayList<ReportLearnerPlan>();
        ReportLearnerPlan p = new ReportLearnerPlan();
        p.setTreatmentProvider(loginUser.getFullName());

        String fullName = learner.getFirstName() + " " + learner.getLastName();
        p.setStudentName(fullName);

        List<LearnerSession> learnerSessions = learnerSessionService.getSessionsForLearnerPlanId(learnerPlan.getLearnerPlanId());
        if (learnerSessions == null || learnerSessions.size() == 0) {
            p.setInitialAssessmentDate("No sessions yet");
        }
        else {
            Date earliestDate = null;
            for (LearnerSession learnerSession : learnerSessions) {
                if (earliestDate == null ||
                        learnerSession.getSessionDate().before(earliestDate)) {
                    earliestDate = learnerSession.getSessionDate();
                }
            }
            p.setInitialAssessmentDate(simpleDateFormat.format(earliestDate));
        }

        p.setTreatmentPlanTitle("Treatment Plan for " + fullName);
        p.setTreatmentPlanDescription(learnerPlan.getTreatmentDescription());

        p.setSchool(learner.getSchool());
        p.setStudentNumber(learner.getStudentId());
        p.setTreatmentFrequency(learnerPlan.getTreatmentFrequency());

        p.setTreatmentPlanDate(simpleDateFormat.format(learnerPlan.getDateStartPlan()));

        p.setObjectives(learnerPlan.getLearnerPlanObjectiveList());

        reportLearnerPlans.add(p);


        Map<String,Object> parameterMap = new HashMap<String,Object>();
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(reportLearnerPlans, false);
        parameterMap.put("datasource", JRdataSource);

        return parameterMap;
    }

    public Map<String,Object> generateLearnerSessionData(LearnerPlan learnerPlan, LoginUser loginUser, SessionReportRequestDates requestDates) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");

        learnerPlanService.updateMasteryForLearnerPlan(learnerPlan);

        Learner learner = learnerService.getLearner(learnerPlan.getLearnerId());

        List<ReportLearnerSession> reportLearnerSessions = new ArrayList<ReportLearnerSession>();

        for (LearnerPlanObjective learnerPlanObjective : learnerPlan.getLearnerPlanObjectiveList()) {
            boolean isPercentage = (learnerPlanObjective.getObjectiveType().getTypeId().equals("P"));
            ReportLearnerSession p = new ReportLearnerSession();
            p.setTreatmentProvider(loginUser.getFullName());

            String fullName = learner.getFirstName() + " " + learner.getLastName();
            p.setStudentName(fullName);
            p.setDomain(learnerPlanObjective.getObjective().getDomain().getDescription());
            p.setObjective(learnerPlanObjective.getObjective().getDescription());
            p.setCondition(learnerPlanObjective.getCondition().getDescription());
            p.setCriteria(learnerPlanObjective.getCriteria().getDescription());
            p.setMastery(learnerPlanObjective.getMasteryValue().toString() + ((isPercentage) ? "%" : " Target(s)"));

            List<LearnerSessionObjective> objectiveSessions = learnerSessionService.getSessionsForPlanObjective(learnerPlanObjective);
            List<ReportSessionData> reportSessionDataList = new ArrayList<ReportSessionData>();
            for (LearnerSessionObjective sessionObjective : objectiveSessions) {

                Date sessionDate = learnerSessionService.getLearnerSession(sessionObjective.getLearnerSessionId()).getSessionDate();
                if (includeSessionInReport(sessionDate, requestDates)) {
                    ReportSessionData sessionData = new ReportSessionData();
                    sessionData.setSessionDate(simpleDateFormat.format(sessionDate));
                    if (isPercentage) {
                        if (sessionObjective.getSessionValue() != null) {
                            sessionData.setSessionValue(sessionObjective.getSessionValue());
                        }
                        else {
                            sessionData = null;
                        }
                    } else {
                        Long countMastered = 0L;
                        for (LearnerPlanObjectiveTarget planObjectiveTarget : learnerPlanObjective.getLearnerPlanObjectiveTarget()) {
                            //check if it is initially mastered and meets the date range criteria
                            if (planObjectiveTarget.getMastered().equals("Y") &&
                                    !planObjectiveTarget.getMasteryDate().after(sessionDate)) {
                                //check if it is in retention
                                if (planObjectiveTarget.getRetentionState().equals(TargetRetentionState.NO_RETENTION_POLICY)) {
                                    countMastered++;
                                }
                                else {
                                    //has retention policy, so is it mastered yet?
                                    if (planObjectiveTarget.getRetentionState().equals(TargetRetentionState.RETENTION_MASTERED)) {
                                        countMastered++;
                                    }
                                }
                            }
                        }
                        sessionData.setSessionValue(countMastered);
                    }
                    if (sessionData != null) {
                        reportSessionDataList.add(sessionData);
                    }
                }
            }

            Collections.sort(reportSessionDataList, new Comparator<ReportSessionData>() {
                @Override
                public int compare(ReportSessionData session1, ReportSessionData session2) {
                    return session1.getSessionDate().compareTo(session2.getSessionDate());
                }
            });

            p.setReportSessionDataList(reportSessionDataList);

            reportLearnerSessions.add(p);
        }

        Map<String,Object> parameterMap = new HashMap<String,Object>();
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(reportLearnerSessions, false);
        parameterMap.put("datasource", JRdataSource);

        return parameterMap;
    }

    public boolean includeSessionInReport(Date sessionDate, SessionReportRequestDates requestDates) {
        Date startDate = requestDates.getStartDate();
        Date endDate = requestDates.getEndDate();
        if (startDate != null && endDate != null) {
            if (sessionDate.before(startDate) ||
                    sessionDate.after(endDate)) {
                return false;
            }
        }

        return true;
    }
}
