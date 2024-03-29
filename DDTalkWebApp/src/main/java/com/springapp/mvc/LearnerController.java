package com.springapp.mvc;

import com.springapp.model.*;
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
        List<LearnerPlan> plans = learnerPlanService.getPlansForLearnerId(learner.getLearnerId());
        LearnerNote learnerNote = learnerService.noteForLearner(Long.parseLong(learnerId));

        model.addAttribute("learner", learner);
        model.addAttribute("plans", plans);
        if (learnerNote != null) {
            model.addAttribute("learnerNote", learnerNote);
        }

        return "learnerMain";
    }

    @RequestMapping(value = "/note", method = RequestMethod.POST)
    @ResponseBody
    public String learnerNoteUpdate(@ModelAttribute(value="learnerNote") LearnerNote learnerNote, BindingResult errors) {
        learnerService.saveNoteForLearner(learnerNote);
        return "{}";
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
        if (loginUser.getRole().equals("ADMIN")) {
            model.addAttribute("learners", learnerService.getAllLearners());
        }
        else {
            model.addAttribute("learners", loginUser.getLearners());
        }

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

        LoginUser loginUser = loginUserService.getLoginUser(httpServletRequest.getUserPrincipal().getName());
        learnerService.saveLearner(learner);

        learnerService.linkLearnerToLoginUser(learner, loginUser);

        return "redirect:/learner/user";
    }

    @RequestMapping(value = "/edit/{learnerId}", method = RequestMethod.GET)
    public String editLearner(@PathVariable String learnerId, ModelMap model) {

        Learner learner = learnerService.getLearner(Long.parseLong(learnerId));
        model.addAttribute("learner", learner);
        model.addAttribute("savePath", "../");
        return "learnerProfile";
    }

    @RequestMapping(value = "/deleteConfirm/{learnerId}", method = RequestMethod.GET)
    public String deleteConfirmLearner(@PathVariable String learnerId, ModelMap model) {

        Learner learner = learnerService.getLearner(Long.parseLong(learnerId));
        model.addAttribute("learner", learner);
        return "learnerDelete";
    }

    @RequestMapping(value = "/delete/{learnerId}", method = RequestMethod.GET)
    public String deleteLearner(@PathVariable String learnerId) {

        learnerService.deleteLearner(Long.parseLong(learnerId));

        return "redirect:/learner/user";
    }

}

