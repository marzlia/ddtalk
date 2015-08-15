package com.springapp.mvc;

import com.springapp.model.*;
import com.springapp.model.request.UpdateObjectiveRequestItem;
import com.springapp.model.request.UpdateSessionObjectiveRequestItem;
import com.springapp.model.request.UpdateSessionObjectiveTargetRequestItem;
import com.springapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/learnerSession")
public class LearnerSessionController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    LearnerService learnerService;

    @Autowired
    LearnerPlanService learnerPlanService;

    @Autowired
    ConditionService conditionService;

    @Autowired
    CriteriaService criteriaService;

    @Autowired
    ObjectiveTypeService objectiveTypeService;

    @Autowired
    DomainService domainService;

    @Autowired
    LearnerSessionService learnerSessionService;

    @RequestMapping(value = "/newLearnerSession/{learnerPlanId}", method = RequestMethod.GET)
    public String createLearnerSession(@PathVariable String learnerPlanId, ModelMap model) {

        LearnerPlan plan = learnerPlanService.getLearnerPlan(Long.parseLong(learnerPlanId));
        Learner learner = learnerService.getLearner(plan.getLearnerId());

        model.addAttribute("learnerPlan", plan);
        model.addAttribute("learner", learner);

        LearnerSession learnerSession = learnerSessionService.createNewSessionForLearnerPlan(plan);
        model.addAttribute("learnerSession", learnerSession);

        List<LearnerPlanObjective> objectives = plan.getLearnerPlanObjectiveList();
        List<Domain> domains = domainService.getAllDomains();

        // populate existing objectives
        Map<Domain, List<LearnerPlanObjective>> domainObjectivesMap = new HashMap<Domain, List<LearnerPlanObjective>>();
        for (LearnerPlanObjective objective : objectives) {
            Domain domain = returnDomainObjectFromArrayWhichMatches(objective.getObjective().getDomain(), domains);
            List<LearnerPlanObjective> domainObjectiveList = domainObjectivesMap.get(domain);
            if (domainObjectiveList == null) {
                domainObjectiveList = new ArrayList<LearnerPlanObjective>();
                domainObjectivesMap.put(domain, domainObjectiveList);
            }
            domainObjectiveList.add(objective);
        }

        model.addAttribute("domainObjectivesMap", domainObjectivesMap);

        List<Integer> masteryPercents = new ArrayList<Integer>();
        for (int i = 60; i <= 100; i += 5) {
            masteryPercents.add(i);
        }
        model.addAttribute("masteryPercents", masteryPercents);



        return "learnerSession";

    }

    private Domain returnDomainObjectFromArrayWhichMatches(Domain domain, List<Domain> domainArray) {
        for (Domain searchDomain : domainArray) {
            if (searchDomain.getDomainId() == domain.getDomainId()) {
                return searchDomain;
            }
        }
        return null;
    }

    @RequestMapping(value = "/updateSessionObjective", method = RequestMethod.POST)
    @ResponseBody
    public String updateSessionObjective(@ModelAttribute(value="updateSessionObjectiveRequestItem") UpdateSessionObjectiveRequestItem updateSessionObjectiveRequestItem, BindingResult errors) {
        learnerSessionService.updateSessionObjective(Long.parseLong(updateSessionObjectiveRequestItem.getLearnerSessionObjectiveId()),
                updateSessionObjectiveRequestItem.getSessionValue());


        return "{}";
    }

    @RequestMapping(value = "/updateSessionObjectiveTarget", method = RequestMethod.POST)
    @ResponseBody
    public String updateSessionObjectiveTarget(@ModelAttribute(value="updateSessionObjectiveTargetRequestItem") UpdateSessionObjectiveTargetRequestItem updateSessionObjectiveTargetRequestItem, BindingResult errors) {
        learnerSessionService.updateSessionObjectiveTarget(Long.parseLong(updateSessionObjectiveTargetRequestItem.getLearnerSessionObjectiveTargetId()),
                Long.parseLong(updateSessionObjectiveTargetRequestItem.getPromptCodeId()),
                updateSessionObjectiveTargetRequestItem.getSessionValue());


        return "{}";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveLearnerPlan(@ModelAttribute(value="learnerSession") LearnerSession learnerSession, BindingResult errors) {

        learnerSessionService.saveLearnerSession(learnerSession);

        LearnerPlan plan = learnerPlanService.getLearnerPlan(learnerSession.getLearnerPlanId());
        return "redirect:/learnerPlan/" + plan.getLearnerPlanId();
    }
}

