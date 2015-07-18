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


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/learner")
public class LearnerController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    LearnerService learnerService;

    @Autowired
    LearnerPlanService learnerPlanService;

    @Autowired
    LoginUserService loginUserService;

    @Autowired
    DomainService domainService;

    @RequestMapping(value = "/{learnerId}", method = RequestMethod.GET)
    public String getLearnerUI(@PathVariable String learnerId, ModelMap model) {

        Learner learner = learnerService.getLearner(Long.parseLong(learnerId));
        List<LearnerPlan> objectivePlans = learnerPlanService.getObjectivePlansForLearnerId(learner.getLearnerId());
        List<LearnerPlan> targetPlans = learnerPlanService.getTargetPlansForLearnerId(learner.getLearnerId());

        model.addAttribute("learner", learner);
        model.addAttribute("objectivePlans", objectivePlans);
        model.addAttribute("targetPlans", targetPlans);

        return "learnerMain";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getLearnerUI(ModelMap model) {

        List<Learner> learners = learnerService.getAllLearners();
        model.addAttribute("learners", learners);

        return "learnerlist";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getLearnersForUser(ModelMap model) {

        LoginUser loginUser = loginUserService.getLoginUser(httpServletRequest.getUserPrincipal().getName());
        model.addAttribute("learners", loginUser.getLearners());

        return "learnerlist";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLearner(ModelMap model) {

        model.addAttribute("learner", new Learner());
        model.addAttribute("savePath", "");
        return "learnerProfile";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveLearner(@ModelAttribute(value="learner") Learner learner, BindingResult errors) {

        learnerService.saveLearner(learner);

        return "redirect:/learner/user";
    }

    @RequestMapping(value = "/edit/{learnerId}", method = RequestMethod.GET)
    public String editLearner(@PathVariable String learnerId, ModelMap model) {

        Learner learner = learnerService.getLearner(Long.parseLong(learnerId));
        model.addAttribute("learner", learner);
        model.addAttribute("savePath", "../");
        return "learnerProfile";
    }

    @RequestMapping(value = "/objectivePlan/{planId}", method = RequestMethod.GET)
    public String learnerObjectivePlan(@PathVariable String planId, ModelMap model) {

        LearnerPlan learnerPlan = learnerPlanService.getLearnerPlan(Long.parseLong(planId));
        Learner learner = learnerService.getLearner(learnerPlan.getLearnerId());
        List<Domain> domains = domainService.getAllDomains();
        model.addAttribute("learner", learner);
        model.addAttribute("objectivePlan", learnerPlan);
        model.addAttribute("domains", domains);

        return "learnerObjectivePlan";
    }

    @RequestMapping(value = "/objectivePlan/addDomain", method = RequestMethod.POST)
    public String addDomain(@RequestBody String planId, ModelMap model) {


        return "OK";
    }

}

