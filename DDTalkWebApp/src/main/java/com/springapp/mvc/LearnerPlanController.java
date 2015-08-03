package com.springapp.mvc;

import com.springapp.model.*;
import com.springapp.model.request.AddObjectiveRequest;
import com.springapp.service.*;
import com.springapp.csrf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/learnerPlan")
public class LearnerPlanController {

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

    @RequestMapping(value = "/{planId}", method = RequestMethod.GET)
    public String learnerPlan(@PathVariable String planId, ModelMap model) {

        LearnerPlan learnerPlan = learnerPlanService.getLearnerPlan(Long.parseLong(planId));
        List<LearnerPlanObjective> objectives = learnerPlan.getLearnerPlanObjectiveList();
        Learner learner = learnerService.getLearner(learnerPlan.getLearnerId());
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

        //empty domains
        List<Domain> emptyDomains = new ArrayList<Domain>();
        for (Domain domain : domains) {
            List<LearnerPlanObjective> domainObjectiveList = domainObjectivesMap.get(domain);
            if (domainObjectiveList == null) {
                emptyDomains.add(domain);
            }
        }

        model.addAttribute("learner", learner);
        model.addAttribute("plan", learnerPlan);
        model.addAttribute("domainObjectivesMap", domainObjectivesMap);
        model.addAttribute("emptyDomains", emptyDomains);

        List<Condition> conditionList = conditionService.getAllConditions();
        List<Criteria> criteriaList = criteriaService.getAllCriteria();
        List<ObjectiveType> objectiveTypes = objectiveTypeService.getAllTypes();

        model.addAttribute("conditions", conditionList);
        model.addAttribute("criterias", criteriaList);
        model.addAttribute("objectiveTypes", objectiveTypes);

        List<Integer> masteryCounts = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            masteryCounts.add(i);
        }
        model.addAttribute("masteryCounts", masteryCounts);

        List<Integer> masteryPercents = new ArrayList<Integer>();
        for (int i = 60; i <= 100; i += 5) {
            masteryPercents.add(i);
        }
        model.addAttribute("masteryPercents", masteryPercents);

        return "learnerPlan";
    }

    private Domain returnDomainObjectFromArrayWhichMatches(Domain domain, List<Domain> domainArray) {
        for (Domain searchDomain : domainArray) {
            if (searchDomain.getDomainId() == domain.getDomainId()) {
                return searchDomain;
            }
        }
        return null;
    }

    @RequestMapping(value = "/addObjective", method = RequestMethod.POST)
    @ResponseBody
    public String createCriteria(@ModelAttribute(value="addObjectiveRequest") AddObjectiveRequest addObjectiveRequest, BindingResult errors) {
        learnerPlanService.addObjectiveToLearnerPlan(Long.parseLong(addObjectiveRequest.getLearnerPlanId()), Long.parseLong(addObjectiveRequest.getObjectiveId()));

        return "Sucessssss";
    }

}

