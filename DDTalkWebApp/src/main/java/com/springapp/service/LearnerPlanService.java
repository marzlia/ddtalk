package com.springapp.service;

import com.springapp.model.*;
import com.springapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.*;

/**
 * Created by peter on 5/19/15.
 */
@Service
public class LearnerPlanService {

    @Autowired
    LearnerPlanRepository learnerPlanRepository;

    @Autowired
    LearnerPlanObjectiveRepository learnerPlanObjectiveRepository;

    @Autowired
    LearnerPlanObjectiveTargetRepository learnerPlanObjectiveTargetRepository;

    @Autowired
    ObjectiveRepository objectiveRepository;

    @Autowired
    DomainRepository domainRepository;

    @Autowired
    ObjectiveTypeRepository objectiveTypeRepository;

    @Autowired
    ConditionRepository conditionRepository;

    @Autowired
    CriteriaRepository criteriaRepository;

    @Autowired
    DomainService domainService;

    @Autowired
    LearnerSessionService learnerSessionService;

    public List<LearnerPlan> getAllLearnerPlans() {
        return learnerPlanRepository.findAll();
    }

    public List<LearnerPlan> getPlansForLearnerId(Long learnerId) {
        return learnerPlanRepository.findByLearnerId(learnerId);
    }

    public LearnerPlan getLearnerPlan(Long planId) {
        return learnerPlanRepository.findOne(planId);
    }

    public void addObjectiveToLearnerPlan(String planId, String domainId, String objectiveId, String objectiveTypeId,
                                          String criteriaId, String retentionProbeEnabled, String retentionProbeNumDays ) {

        LearnerPlanObjective learnerPlanObjective = new LearnerPlanObjective();
        learnerPlanObjective.setLearnerPlanId(Long.parseLong(planId));
        learnerPlanObjective.setMastered("N");

        //if not numeric, user added a new domain
        if (!isNumeric(domainId)) {
            Domain newDomain = new Domain();
            newDomain.setDescription(domainId);
            newDomain = domainService.createDomain(newDomain);
            domainId = newDomain.getDomainId().toString();
        }

        Objective objective = null;
        if (isNumeric(objectiveId)) {
            objective = objectiveRepository.findOne(Long.parseLong(objectiveId));
        }
        else {
            //user added a new objective
            Domain domain = domainRepository.findOne(Long.parseLong(domainId));

            objective = new Objective();
            objective.setDescription(objectiveId);
            objective.setDomain(domain);
            objective = objectiveRepository.save(objective);
        }
        learnerPlanObjective.setObjective(objective);

        ObjectiveType objectiveType = objectiveTypeRepository.findByObjectiveTypeId(Long.parseLong(objectiveTypeId));
        learnerPlanObjective.setObjectiveType(objectiveType);

        Condition condition = conditionRepository.findByDescription("None");
        learnerPlanObjective.setCondition(condition);

        Criteria criteria = criteriaRepository.findOne(Long.parseLong(criteriaId));
        learnerPlanObjective.setCriteria(criteria);

        if (StringUtils.isEmpty(retentionProbeEnabled) || objectiveType.getTypeId().equals("P")) {
            learnerPlanObjective.setRetentionProbeEnabled("N");
            learnerPlanObjective.setRetentionProbeDaysToRecheck(0L);
        }
        else {
            learnerPlanObjective.setRetentionProbeEnabled("Y");
            learnerPlanObjective.setRetentionProbeDaysToRecheck(Long.parseLong(retentionProbeNumDays));
        }

        learnerPlanObjectiveRepository.save(learnerPlanObjective);
    }

    public void updatePlanObjective(Long planObjectiveId, Long conditionId, Long masteryValue) {
        LearnerPlanObjective learnerPlanObjective = learnerPlanObjectiveRepository.findOne(planObjectiveId);
        Condition condition = conditionRepository.findOne(conditionId);

        learnerPlanObjective.setCondition(condition);
        learnerPlanObjective.setMasteryValue(masteryValue);
        learnerPlanObjectiveRepository.save(learnerPlanObjective);
    }

    public LearnerPlan createNewPlanForLearnerId(Long learnerId) {
        LearnerPlan plan = new LearnerPlan();
        plan.setLearnerId(learnerId);
        return learnerPlanRepository.save(plan);
    }

    public LearnerPlan saveLearnerPlan(LearnerPlan plan) {
        return learnerPlanRepository.save(plan);
    }

    public void addPlanObjectiveTargets(Long planObjectiveId, String targets) {
        //comma separated
        List<String> targetList = new ArrayList<String>(Arrays.asList(targets.split(",")));
        for (String aTarget : targetList) {

            LearnerPlanObjectiveTarget learnerPlanObjectiveTarget = new LearnerPlanObjectiveTarget();
            learnerPlanObjectiveTarget.setLearnerPlanObjectiveId(planObjectiveId);
            learnerPlanObjectiveTarget.setTargetDescription(aTarget);
            learnerPlanObjectiveTarget.setMastered("N");

            learnerPlanObjectiveTargetRepository.save(learnerPlanObjectiveTarget);
        }
    }

    public LearnerPlanObjective getLearnerPlanObjective(Long planObjectiveId) {
        LearnerPlanObjective learnerPlanObjective = learnerPlanObjectiveRepository.findOne(planObjectiveId);
        return learnerPlanObjective;
    }

    public List<LearnerPlanObjectiveTarget> getLearnerPlanObjectiveTargets(Long planObjectiveId) {
        List<LearnerPlanObjectiveTarget> learnerPlanObjectiveTargets = learnerPlanObjectiveTargetRepository.findByLearnerPlanObjectiveId(planObjectiveId);

        return learnerPlanObjectiveTargets;
    }

    public void deleteLearnerPlanObjectiveTarget(Long planObjectiveTargetId) {
        learnerPlanObjectiveTargetRepository.delete(planObjectiveTargetId);
    }

    public void deleteLearnerPlanObjective(Long planObjectiveId) {
        learnerPlanObjectiveRepository.delete(planObjectiveId);
    }

    public LearnerPlan updateMasteryForLearnerPlan(LearnerPlan plan) {

        //get all the sessions for this plan and sort by date
        List<LearnerSession> learnerSessions = getSessionsForLearnerPlanId(plan.getLearnerPlanId());

        //build array of  objectives which have been mastered
        updateObjectivesWhichHaveReachedMastery(plan, learnerSessions);

        return learnerPlanRepository.save(plan);
    }

    //get all the sessions for this plan and sort by date
    private List<LearnerSession> getSessionsForLearnerPlanId(Long learnerPlanId) {

        List<LearnerSession> learnerSessions = learnerSessionService.getSessionsForLearnerPlanId(learnerPlanId);
        Collections.sort(learnerSessions, new Comparator<LearnerSession>() {
            @Override
            public int compare(LearnerSession session1, LearnerSession session2) {
                return session1.getSessionDate().compareTo(session2.getSessionDate());
            }
        });

        return learnerSessions;
    }

    private void updateObjectivesWhichHaveReachedMastery(LearnerPlan plan, List<LearnerSession> learnerSessions) {

        Map<LearnerPlanObjective, Integer> objectivesMastered = new HashMap<LearnerPlanObjective, Integer>();
        Map<LearnerPlanObjective, Date> lastSessionDatesForMastery = new HashMap<LearnerPlanObjective, Date>();

        for (LearnerSession learnerSession : learnerSessions) {
            List<LearnerSessionObjective> learnerSessionObjectives = learnerSession.getLearnerSessionObjectiveList();
            for (LearnerSessionObjective learnerSessionObjective : learnerSessionObjectives) {
                LearnerPlanObjective planObjective = learnerSessionObjective.getLearnerPlanObjective();
                if (planObjective.getMastered().equals("N")) {
                    if (planObjective.getObjectiveType().getTypeId().equals("P")) {
                        Integer currentConsecutiveMasteryCount = objectivesMastered.get(planObjective);
                        if (learnerSessionObjective.getSessionValue() >= planObjective.getMasteryValue()) {
                            currentConsecutiveMasteryCount = new Integer((currentConsecutiveMasteryCount != null) ? currentConsecutiveMasteryCount.intValue() + 1 : 1);
                            objectivesMastered.put(planObjective, currentConsecutiveMasteryCount);
                            lastSessionDatesForMastery.put(planObjective, learnerSession.getSessionDate());
                        }
                        else {
                            objectivesMastered.remove(planObjective);
                            lastSessionDatesForMastery.remove(planObjective);
                        }
                    }
                    else if (planObjective.getObjectiveType().getTypeId().equals("C")) {
                    }
                }
            }
        }

        //update objectives which have been mastered
        Set<LearnerPlanObjective> keys = objectivesMastered.keySet();
        for (LearnerPlanObjective learnerPlanObjective : keys) {
            Integer currentConsecutiveMasteryCount = objectivesMastered.get(learnerPlanObjective);
            currentConsecutiveMasteryCount = new Integer((currentConsecutiveMasteryCount != null) ? currentConsecutiveMasteryCount.intValue() : 0);

            if (currentConsecutiveMasteryCount >= learnerPlanObjective.getCriteria().getConsecutiveToMastered()) {
                for (LearnerPlanObjective planObjective : plan.getLearnerPlanObjectiveList()) {
                    if (planObjective.equals(learnerPlanObjective)) {
                        Date masteryDate = lastSessionDatesForMastery.get(learnerPlanObjective);
                        planObjective.setMasteryDate(masteryDate);
                        planObjective.setMastered("Y");
                        learnerPlanObjectiveRepository.save(planObjective);
                        break;
                    }
                }
            }
        }
    }

    private  boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
