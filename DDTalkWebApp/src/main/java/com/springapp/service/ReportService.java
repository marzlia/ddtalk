package com.springapp.service;

import com.springapp.model.Learner;
import com.springapp.model.LearnerPlan;
import com.springapp.model.LoginUser;
import com.springapp.reports.ReportLearnerPlan;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by peter on 8/29/15.
 */
@Service
public class ReportService {

    @Autowired
    LearnerService learnerService;

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
}
