package com.springapp.mvc;

import com.springapp.model.*;
import com.springapp.model.request.AddObjectiveRequest;
import com.springapp.model.request.AddObjectiveTargetsRequest;
import com.springapp.model.request.UpdateObjectiveRequestItem;
import com.springapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

        model.addAttribute("learner", learner);
        model.addAttribute("plan", learnerPlan);
        model.addAttribute("emptyDomains", domains);

        Map<Domain, List<LearnerPlanObjective>> treeMap = new TreeMap<Domain, List<LearnerPlanObjective>>(domainObjectivesMap);
        model.addAttribute("domainObjectivesMap", treeMap);

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

    @RequestMapping(value = "/updateObjectives", method = RequestMethod.POST)
    @ResponseBody
    public String updateObjectives(@ModelAttribute(value="updateObjectiveRequestItem") UpdateObjectiveRequestItem updateObjectiveRequestItem, BindingResult errors) {
        learnerPlanService.updatePlanObjective(Long.parseLong(updateObjectiveRequestItem.getPlanObjectiveId()),
                Long.parseLong(updateObjectiveRequestItem.getConditionId()),
                Long.parseLong(updateObjectiveRequestItem.getCriteriaId()),
                updateObjectiveRequestItem.getMasteryValue());


        return "{}";
    }

    @RequestMapping(value = "/addObjective", method = RequestMethod.POST)
    @ResponseBody
    public String addObjective(@ModelAttribute(value="addObjectiveRequest") AddObjectiveRequest addObjectiveRequest, BindingResult errors) {
        learnerPlanService.addObjectiveToLearnerPlan(addObjectiveRequest.getLearnerPlanId(),
                addObjectiveRequest.getDomainId(),
                addObjectiveRequest.getObjectiveId(),
                addObjectiveRequest.getObjectiveTypeId());

        return "{}";
    }

    @RequestMapping(value = "/newLearnerPlan/{learnerId}", method = RequestMethod.GET)
    public String createLearnerPlan(@PathVariable String learnerId, ModelMap model) {

        LearnerPlan plan = learnerPlanService.createNewPlanForLearnerId(Long.parseLong(learnerId));
        Learner learner = learnerService.getLearner(Long.parseLong(learnerId));

        model.addAttribute("learnerPlan", plan);
        model.addAttribute("learner", learner);

        List<String> treatmentFrequencyOptions = new ArrayList<String>();
        treatmentFrequencyOptions.add("1 time per week");
        treatmentFrequencyOptions.add("2 times per week");
        treatmentFrequencyOptions.add("3 times per week");
        treatmentFrequencyOptions.add("4 times per week");
        treatmentFrequencyOptions.add("5 times per week");
        treatmentFrequencyOptions.add("Weekly");
        treatmentFrequencyOptions.add("Bi-Monthly");
        treatmentFrequencyOptions.add("Monthly");
        model.addAttribute("treatmentFrequencyOptions", treatmentFrequencyOptions);

        List<String> dataCollectionOptions = new ArrayList<String>();
        dataCollectionOptions.add("Each Session");
        dataCollectionOptions.add("Weekly");
        dataCollectionOptions.add("Bi-Monthly");
        dataCollectionOptions.add("Monthly");
        model.addAttribute("dataCollectionOptions", dataCollectionOptions);

        return "learnerPlanNew";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveLearnerPlan(@ModelAttribute(value="learnerPlan") LearnerPlan learnerPlan, BindingResult errors) {

        learnerPlanService.saveLearnerPlan(learnerPlan);

        return "redirect:/learnerPlan/" + learnerPlan.getLearnerPlanId();
    }

    @RequestMapping(value = "/addObjectiveTargets", method = RequestMethod.POST)
    public String saveTargets(@ModelAttribute(value="addObjectiveTargetsRequest") AddObjectiveTargetsRequest addObjectiveTargetsRequest, BindingResult errors) {

        LearnerPlan learnerPlan = learnerPlanService.getLearnerPlan(Long.parseLong(addObjectiveTargetsRequest.getLearnerPlanId()));
        learnerPlanService.addPlanObjectiveTargets(Long.parseLong(addObjectiveTargetsRequest.getPlanObjectiveId()), addObjectiveTargetsRequest.getTargets());
        return "redirect:/learnerPlan/learnerPlanObjectiveTargets/" + learnerPlan.getLearnerPlanId() + "/" + addObjectiveTargetsRequest.getPlanObjectiveId();
    }

    @RequestMapping(value = "/learnerPlanObjectiveTargets/{learnerPlanId}/{planObjectiveId}", method = RequestMethod.GET)
    public String learnerPlanAddTargets(@PathVariable String learnerPlanId, @PathVariable String planObjectiveId, ModelMap model) {

        LearnerPlan plan = learnerPlanService.getLearnerPlan(Long.parseLong(learnerPlanId));
        model.addAttribute("learnerPlan", plan);

        LearnerPlanObjective learnerPlanObjective = learnerPlanService.getLearnerPlanObjective(Long.parseLong(planObjectiveId));
        model.addAttribute("learnerPlanObjective", learnerPlanObjective);

        List<LearnerPlanObjectiveTarget> learnerPlanObjectiveTargets = learnerPlanService.getLearnerPlanObjectiveTargets(Long.parseLong(planObjectiveId));
        model.addAttribute("learnerPlanObjectiveTargets", learnerPlanObjectiveTargets);

        return "learnerPlanObjectiveTargets";
    }


    @RequestMapping(value = "/removeObjectiveTarget/{planId}/{objectiveId}/{objectiveTargetId}", method = RequestMethod.GET)
    public String removeObjectiveTarget(@PathVariable String planId, @PathVariable String objectiveId, @PathVariable String objectiveTargetId) {
        learnerPlanService.deleteLearnerPlanObjectiveTarget(Long.parseLong(objectiveTargetId));
        return "redirect:/learnerPlan/learnerPlanObjectiveTargets/" + planId + "/" + objectiveId;
    }

    @RequestMapping(value = "/removeObjective/{planId}/{objectiveId}", method = RequestMethod.GET)
    public String removeObjective(@PathVariable String planId, @PathVariable String objectiveId) {
        learnerPlanService.deleteLearnerPlanObjective(Long.parseLong(objectiveId));
        return "redirect:/learnerPlan/" + planId;
    }


}

