package com.springapp.mvc;

import com.springapp.model.*;
import com.springapp.model.request.MapSessionObjectiveItem;
import com.springapp.model.request.UpdateSessionObjectiveItem;
import com.springapp.model.request.UpdateSessionObjectiveTargetItem;
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

    @RequestMapping(value = "/{learnerPlanId}", method = RequestMethod.GET)
    public String learnerSessions(@PathVariable String learnerPlanId, ModelMap model) {

        LearnerPlan plan = learnerPlanService.getLearnerPlan(Long.parseLong(learnerPlanId));
        Learner learner = learnerService.getLearner(plan.getLearnerId());

        model.addAttribute("plan", plan);
        model.addAttribute("learner", learner);

        List<LearnerSession> learnerSessions = learnerSessionService.getSessionsForLearnerPlanId(Long.parseLong(learnerPlanId));
        model.addAttribute("learnerSessions", learnerSessions);

        return "learnerSessionMain";
    }

    @RequestMapping(value = "/newLearnerSession/{learnerPlanId}", method = RequestMethod.GET)
    public String createLearnerSession(@PathVariable String learnerPlanId, ModelMap model) {

        LearnerPlan plan = learnerPlanService.getLearnerPlan(Long.parseLong(learnerPlanId));
        LearnerSession learnerSession = learnerSessionService.createNewSessionForLearnerPlan(plan);
        return "redirect:../" + learnerPlanId + "/" + learnerSession.getLearnerSessionId();
    }

    @RequestMapping(value = "/{learnerPlanId}/{learnerSessionId}", method = RequestMethod.GET)
    public String editLearnerSession(@PathVariable String learnerPlanId, @PathVariable String learnerSessionId, ModelMap model) {

        LearnerPlan plan = learnerPlanService.getLearnerPlan(Long.parseLong(learnerPlanId));
        Learner learner = learnerService.getLearner(plan.getLearnerId());

        model.addAttribute("learnerPlan", plan);
        model.addAttribute("learner", learner);

        LearnerSession learnerSession = learnerSessionService.getLearnerSession(Long.parseLong(learnerSessionId));
        model.addAttribute("learnerSession", learnerSession);

        List<LearnerPlanObjective> objectives = plan.getLearnerPlanObjectiveList();
        List<Domain> domains = domainService.getAllDomains();

        // populate existing objectives
        Map<Domain, List<MapSessionObjectiveItem>> domainObjectivesMap = new HashMap<Domain, List<MapSessionObjectiveItem>>();
        for (LearnerPlanObjective objective : objectives) {
            Domain domain = returnDomainObjectFromArrayWhichMatches(objective.getObjective().getDomain(), domains);
            List<MapSessionObjectiveItem> domainObjectiveList = domainObjectivesMap.get(domain);
            if (domainObjectiveList == null) {
                domainObjectiveList = new ArrayList<MapSessionObjectiveItem>();
                domainObjectivesMap.put(domain, domainObjectiveList);
            }
            MapSessionObjectiveItem mapSessionObjectiveItem = new MapSessionObjectiveItem();
            mapSessionObjectiveItem.setPlanObjective(objective);
            LearnerSessionObjective learnerSessionObjective = learnerSessionService.getSessionObjective(objective, learnerSession.getLearnerSessionId());
            mapSessionObjectiveItem.setSessionObjective(learnerSessionObjective);

            domainObjectiveList.add(mapSessionObjectiveItem);
        }

        Map<Domain, List<MapSessionObjectiveItem>> treeMap = new TreeMap<Domain, List<MapSessionObjectiveItem>>(domainObjectivesMap);
        model.addAttribute("domainObjectivesMap", treeMap);

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
    public String updateSessionObjective(@ModelAttribute(value="updateSessionObjectiveItem") UpdateSessionObjectiveItem updateSessionObjectiveItem, BindingResult errors) {
        learnerSessionService.updateSessionObjective(Long.parseLong(updateSessionObjectiveItem.getSessionObjectiveId()),
                updateSessionObjectiveItem.getSessionValue());


        return "{}";
    }

    @RequestMapping(value = "/updateSessionObjectiveTarget", method = RequestMethod.POST)
    @ResponseBody
    public String updateSessionObjectiveTarget(@ModelAttribute(value="updateSessionObjectiveTargetRequestItem") UpdateSessionObjectiveTargetItem updateSessionObjectiveTargetItem, BindingResult errors) {
        learnerSessionService.updateSessionObjectiveTarget(Long.parseLong(updateSessionObjectiveTargetItem.getLearnerSessionObjectiveTargetId()),
                Long.parseLong(updateSessionObjectiveTargetItem.getPromptCodeId()),
                updateSessionObjectiveTargetItem.getSessionValue());


        return "{}";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveLearnerPlan(@ModelAttribute(value="learnerSession") LearnerSession learnerSession, BindingResult errors) {

        learnerSessionService.saveLearnerSession(learnerSession);

        LearnerPlan plan = learnerPlanService.getLearnerPlan(learnerSession.getLearnerPlanId());
        return "redirect:/learnerPlan/" + plan.getLearnerPlanId();
    }
}

