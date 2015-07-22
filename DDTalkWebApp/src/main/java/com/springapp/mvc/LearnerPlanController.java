package com.springapp.mvc;

import com.springapp.model.Domain;
import com.springapp.model.Learner;
import com.springapp.model.LearnerPlan;
import com.springapp.model.LoginUser;
import com.springapp.service.DomainService;
import com.springapp.service.LearnerPlanService;
import com.springapp.service.LearnerService;
import com.springapp.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/learnerPlan")
public class LearnerPlanController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    LearnerService learnerService;

    @Autowired
    LearnerPlanService learnerPlanService;

    @RequestMapping(value = "/{planId}", method = RequestMethod.GET)
    public String learnerObjectivePlan(@PathVariable String planId, ModelMap model) {

        LearnerPlan learnerPlan = learnerPlanService.getLearnerPlan(Long.parseLong(planId));
        Learner learner = learnerService.getLearner(learnerPlan.getLearnerId());

        model.addAttribute("learner", learner);
        model.addAttribute("plan", learnerPlan);

        return "learnerPlan";
    }
}

