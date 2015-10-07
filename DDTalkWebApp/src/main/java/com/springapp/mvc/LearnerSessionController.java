package com.springapp.mvc;

import com.springapp.model.*;
import com.springapp.model.request.*;
import com.springapp.repositories.PromptCodeRepository;
import com.springapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

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
    PromptCodeService promptCodeService;

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
        Collections.sort(learnerSessions, new Comparator<LearnerSession>() {
            @Override
            public int compare(LearnerSession session1, LearnerSession session2) {
                return session1.getSessionDate().compareTo(session2.getSessionDate());
            }
        });

        model.addAttribute("learnerSessions", learnerSessions);

        return "learnerSessionMain";
    }

    @RequestMapping(value = "/newLearnerSession/{learnerPlanId}", method = RequestMethod.GET)
    public String createLearnerSession(@PathVariable String learnerPlanId, ModelMap model) {

        LearnerPlan plan = learnerPlanService.getLearnerPlan(Long.parseLong(learnerPlanId));
        //make sure retention probe data is up to date
        plan = learnerPlanService.updateRetentionProbeInfoForLearnerPlan(plan);

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
            LearnerSessionObjective learnerSessionObjective = learnerSessionService.getSessionObjective(objective, learnerSession.getLearnerSessionId());
            if (learnerSessionObjective != null) {

                Domain domain = returnDomainObjectFromArrayWhichMatches(objective.getObjective().getDomain(), domains);
                List<MapSessionObjectiveItem> domainObjectiveList = domainObjectivesMap.get(domain);
                if (domainObjectiveList == null) {
                    domainObjectiveList = new ArrayList<MapSessionObjectiveItem>();
                    domainObjectivesMap.put(domain, domainObjectiveList);
                }

                MapSessionObjectiveItem mapSessionObjectiveItem = new MapSessionObjectiveItem();
                mapSessionObjectiveItem.setPlanObjective(objective);
                mapSessionObjectiveItem.setSessionObjective(learnerSessionObjective);
                //table row class
                mapSessionObjectiveItem.setTableRowClass(learnerPlanService.getTableRowClassForObjective(objective));

                domainObjectiveList.add(mapSessionObjectiveItem);
            }
        }

        Map<Domain, List<MapSessionObjectiveItem>> treeMap = new TreeMap<Domain, List<MapSessionObjectiveItem>>(domainObjectivesMap);
        Set<Domain> keys = treeMap.keySet();
        for( Domain domain : keys) {
            List<MapSessionObjectiveItem> objectiveList = treeMap.get(domain);
            Collections.sort(objectiveList, new Comparator<MapSessionObjectiveItem>() {
                @Override
                public int compare(MapSessionObjectiveItem item1, MapSessionObjectiveItem item2) {
                    return item1.getPlanObjective().getMastered().compareTo(item2.getPlanObjective().getMastered());
                }
            });
        }
        model.addAttribute("domainObjectivesMap", treeMap);

        List<Integer> masteryPercents = new ArrayList<Integer>();
        for (int i = 0; i <= 100; i += 5) {
            masteryPercents.add(i);
        }
        model.addAttribute("masteryPercents", masteryPercents);



        return "learnerSession";

    }

    private Domain returnDomainObjectFromArrayWhichMatches(Domain domain, List<Domain> domainArray) {
        for (Domain searchDomain : domainArray) {
            if (searchDomain.getDomainId().equals(domain.getDomainId())) {
                return searchDomain;
            }
        }
        return null;
    }

    @RequestMapping(value = "/updateSessionDate", method = RequestMethod.POST)
    @ResponseBody
    public String updateSessionDate(@ModelAttribute(value="updateSessionDate") UpdateSessionDate updateSessionDate, BindingResult errors) {
        learnerSessionService.updateSessionDate(Long.parseLong(updateSessionDate.getSessionId()),
                updateSessionDate.getSessionDate());


        return "{}";
    }

    @RequestMapping(value = "/updateSessionObjective", method = RequestMethod.POST)
    @ResponseBody
    public String updateSessionObjective(@ModelAttribute(value="updateSessionObjectiveItem") UpdateSessionObjectiveItem updateSessionObjectiveItem, BindingResult errors) {
        learnerSessionService.updateSessionObjective(Long.parseLong(updateSessionObjectiveItem.getSessionObjectiveId()),
                                                     updateSessionObjectiveItem.getSessionValue(),
                                                     updateSessionObjectiveItem.getForcedMastered());


        return "{}";
    }

    @RequestMapping(value = "/updateSessionObjectiveTarget", method = RequestMethod.POST)
    @ResponseBody
    public String updateSessionObjectiveTarget(@ModelAttribute(value="updateSessionObjectiveTargetRequestItem") UpdateSessionObjectiveTargetItem updateSessionObjectiveTargetItem, BindingResult errors) {
        String promptCodeId = (updateSessionObjectiveTargetItem.getPromptCodeId() != null) ? updateSessionObjectiveTargetItem.getPromptCodeId() : "-1";

        learnerSessionService.updateSessionObjectiveTarget(Long.parseLong(updateSessionObjectiveTargetItem.getLearnerSessionObjectiveTargetId()),
                Long.parseLong(promptCodeId),
                updateSessionObjectiveTargetItem.getSessionValue());


        return "{}";
    }

    @RequestMapping(value = "/masteryCheck/{sessionId}", method = RequestMethod.GET)
    public String masteryCheck(@PathVariable String sessionId) {

        LearnerSession learnerSession = learnerSessionService.getLearnerSession(Long.parseLong(sessionId));

        LearnerPlan plan = learnerPlanService.getLearnerPlan(learnerSession.getLearnerPlanId());

        //ensure all mastery processing is executed
        plan = learnerPlanService.updateMasteryForLearnerPlan(plan);

        return "redirect:/learnerSession/" + plan.getLearnerPlanId();
    }

    @RequestMapping(value = "/learnerSessionObjectiveTargets/{learnerPlanId}/{sessionId}/{planObjectiveId}", method = RequestMethod.GET)
    public String learnerSessionObjectiveTargets(@PathVariable String learnerPlanId, @PathVariable String sessionId, @PathVariable String planObjectiveId, ModelMap model) {

        LearnerPlan plan = learnerPlanService.getLearnerPlan(Long.parseLong(learnerPlanId));
        Learner learner = learnerService.getLearner(plan.getLearnerId());
        LearnerSession learnerSession = learnerSessionService.getLearnerSession(Long.parseLong(sessionId));

        model.addAttribute("learnerPlan", plan);
        model.addAttribute("learner", learner);
        model.addAttribute("learnerSession", learnerSession);

        // populate existing objective targets
        LearnerPlanObjective learnerPlanObjective = learnerPlanService.getLearnerPlanObjective(Long.parseLong(planObjectiveId));
        LearnerSessionObjective learnerSessionObjective = learnerSessionService.getSessionObjective(learnerPlanObjective, Long.parseLong(sessionId));

        List<MapSessionObjectiveTargetItem> objectiveTargetMapList = new ArrayList<MapSessionObjectiveTargetItem>();
        for (LearnerPlanObjectiveTarget planObjectiveTarget : learnerPlanObjective.getLearnerPlanObjectiveTarget()) {

            MapSessionObjectiveTargetItem mapSessionObjectiveTargetItem = new MapSessionObjectiveTargetItem();
            mapSessionObjectiveTargetItem.setPlanObjectiveTarget(planObjectiveTarget);

            //edit state
            if (planObjectiveTarget.getMastered().equals("N") || TargetRetentionState.isRetentionRetestReady(planObjectiveTarget.getRetentionState())) {
                mapSessionObjectiveTargetItem.setEditState("Y");
            }
            else {
                mapSessionObjectiveTargetItem.setEditState("N");
            }
            //table row class
            mapSessionObjectiveTargetItem.setTableRowClass("");
            if (planObjectiveTarget.getMastered().equals("Y")) {
                if (learnerPlanObjective.getRetentionProbeEnabled().equals("Y") &&
                        !TargetRetentionState.isMastered(planObjectiveTarget.getRetentionState())) {
                    mapSessionObjectiveTargetItem.setTableRowClass("retention");
                }
                else {
                    mapSessionObjectiveTargetItem.setTableRowClass("mastered");
                }
            }


            for (LearnerSessionObjectiveTarget sessionObjectiveTarget : learnerSessionObjective.getLearnerSessionObjectiveTargets()) {
                if (sessionObjectiveTarget.getLearnerPlanObjectiveTarget().getLearnerPlanObjectiveTargetId().equals(planObjectiveTarget.getLearnerPlanObjectiveTargetId())) {
                    mapSessionObjectiveTargetItem.setSessionObjectiveTarget(sessionObjectiveTarget);
                    break;
                }
            }

            objectiveTargetMapList.add(mapSessionObjectiveTargetItem);
        }

        model.addAttribute("planObjective", learnerPlanObjective);
        model.addAttribute("sessionObjectiveTargets", objectiveTargetMapList);

        List<PromptCode> promptCodes = promptCodeService.getAllPromptCodes();
        model.addAttribute("promptCodes", promptCodes);

        return "learnerSessionObjectiveTargets";
    }

    @RequestMapping(value = "/deleteConfirm/{learnerPlanId}/{sessionId}", method = RequestMethod.GET)
    public String deleteConfirmSession(@PathVariable String learnerPlanId, @PathVariable String sessionId, ModelMap model) {
        LearnerPlan learnerPlan = learnerPlanService.getLearnerPlan(Long.parseLong(learnerPlanId));
        Learner learner = learnerService.getLearner(learnerPlan.getLearnerId());

        LearnerSession learnerSession = learnerSessionService.getLearnerSession(Long.parseLong(sessionId));
        model.addAttribute("learnerSession", learnerSession);
        model.addAttribute("learnerPlanId", learnerPlanId);
        model.addAttribute("learner", learner);

        return "learnerSessionDelete";
    }

    @RequestMapping(value = "/delete/{learnerPlanId}/{sessionId}", method = RequestMethod.GET)
    public String sessionDelete(@PathVariable String learnerPlanId, @PathVariable String sessionId) {

        LearnerSession learnerSession = learnerSessionService.getLearnerSession(Long.parseLong(sessionId));
        learnerSessionService.deleteSession(learnerSession);

        return "redirect:/learnerSession/" + learnerPlanId;
    }

}

