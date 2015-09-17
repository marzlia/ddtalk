package com.springapp.mvc;

import com.springapp.model.Learner;
import com.springapp.model.LearnerPlan;
import com.springapp.model.LearnerPlanObjective;
import com.springapp.model.LoginUser;
import com.springapp.reports.ReportLearnerPlan;
import com.springapp.service.LearnerPlanService;
import com.springapp.service.LearnerService;
import com.springapp.service.LoginUserService;
import com.springapp.service.ReportService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    LearnerService learnerService;

    @Autowired
    LearnerPlanService learnerPlanService;

    @Autowired
    LoginUserService loginUserService;

    @Autowired
    ReportService reportService;

    @RequestMapping(method = RequestMethod.GET , value = "/plan/{learnerPlanId}")
    public ModelAndView generateLearnerPlanReport(@PathVariable String learnerPlanId,  ModelAndView modelAndView){

        LoginUser loginUser = loginUserService.getLoginUser(httpServletRequest.getUserPrincipal().getName());
        LearnerPlan learnerPlan = learnerPlanService.getLearnerPlan(Long.parseLong(learnerPlanId));

        Map<String,Object> parameterMap = reportService.generateLearnerPlanData(learnerPlan, loginUser);

        modelAndView = new ModelAndView("report_ddtalk_learner_plan", parameterMap);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET , value = "/session/{learnerPlanId}")
    public ModelAndView generateLearnerSessionReport(@PathVariable String learnerPlanId,  ModelAndView modelAndView){

        LoginUser loginUser = loginUserService.getLoginUser(httpServletRequest.getUserPrincipal().getName());
        LearnerPlan learnerPlan = learnerPlanService.getLearnerPlan(Long.parseLong(learnerPlanId));

        Map<String,Object> parameterMap = reportService.generateLearnerSessionData(learnerPlan, loginUser);

        modelAndView = new ModelAndView("report_ddtalk_learner_session", parameterMap);

        return modelAndView;
    }

}
