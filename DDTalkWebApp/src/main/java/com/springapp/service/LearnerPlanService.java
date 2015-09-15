package com.springapp.service;

import com.springapp.model.*;
import com.springapp.repositories.*;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.time.LocalDate;
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
    LearnerSessionObjectiveTargetRepository learnerSessionObjectiveTargetRepository;

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

        Objective objective;
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

    public void updatePlanObjective(Long planObjectiveId, String conditionId, Long masteryValue) {
        LearnerPlanObjective learnerPlanObjective = learnerPlanObjectiveRepository.findOne(planObjectiveId);
        if (learnerPlanObjective.getMastered().equals("Y")) {
            //don't need to update if mastered
            return;
        }

        Condition condition = conditionRepository.findOne(Long.parseLong(conditionId));

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
        LearnerPlanObjective planObjective =  getLearnerPlanObjective(planObjectiveId);
        boolean hasRetention = planObjective.getRetentionProbeEnabled().equals("Y");

        //comma separated
        List<String> targetList = new ArrayList<String>(Arrays.asList(targets.split(",")));
        for (String aTarget : targetList) {

            LearnerPlanObjectiveTarget learnerPlanObjectiveTarget = new LearnerPlanObjectiveTarget();
            learnerPlanObjectiveTarget.setLearnerPlanObjectiveId(planObjectiveId);
            learnerPlanObjectiveTarget.setTargetDescription(aTarget);
            learnerPlanObjectiveTarget.setMastered("N");
            learnerPlanObjectiveTarget.setRetentionState(hasRetention ? TargetRetentionState.HAS_RETENTION_POLICY : TargetRetentionState.NO_RETENTION_POLICY);

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

    public LearnerPlan updateRetentionProbeInfoForLearnerPlan(LearnerPlan plan) {

        //get all the sessions for this plan and sort by date
        List<LearnerSession> learnerSessions = getSessionsForLearnerPlanId(plan.getLearnerPlanId());

        //build array of  objectives which have been mastered
        processRetentionForCumulativeObjectiveTargets(plan, learnerSessions);

        //re-fetch for changes
        return learnerPlanRepository.findOne(plan.getLearnerPlanId());
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
        Map<LearnerPlanObjective, Date> lastSessionDatesForObjectiveMastery = new HashMap<LearnerPlanObjective, Date>();

        Map<LearnerPlanObjectiveTarget, Integer> objectiveTargetsMastered = new HashMap<LearnerPlanObjectiveTarget, Integer>();
        Map<LearnerPlanObjectiveTarget, Date> lastSessionDatesForObjectiveTargetMastery = new HashMap<LearnerPlanObjectiveTarget, Date>();

        for (LearnerSession learnerSession : learnerSessions) {
            List<LearnerSessionObjective> learnerSessionObjectives = learnerSession.getLearnerSessionObjectiveList();
            for (LearnerSessionObjective learnerSessionObjective : learnerSessionObjectives) {
                LearnerPlanObjective planObjective = learnerSessionObjective.getLearnerPlanObjective();

                if (planObjective.getObjectiveType().getTypeId().equals("P")) {
                    if (planObjective.getMastered().equals("N")) {
                        processPercentageObjective(learnerSession,
                                                   learnerSessionObjective,
                                                   planObjective,
                                                   objectivesMastered,
                                                   lastSessionDatesForObjectiveMastery);
                    }
                }
                else if (planObjective.getObjectiveType().getTypeId().equals("C")) {
                    if (planObjective.getMastered().equals("N") ) {
                        processCumulativeObjective(learnerSession,
                                                   learnerSessionObjective,
                                                    objectiveTargetsMastered,
                                                    lastSessionDatesForObjectiveTargetMastery);
                    }
                }
            }
        }

        //re-fetch plan to make sure all data is up to date
        plan = getLearnerPlan(plan.getLearnerPlanId());

        //update objectives which have been mastered
        updateMasteredPercentageObjectives(plan, objectivesMastered, lastSessionDatesForObjectiveMastery);

        //update objective targets which have been mastered
        updateMasteredCumulativeObjectiveTargets(objectiveTargetsMastered, lastSessionDatesForObjectiveTargetMastery);

        //re-fetch plan to make sure all data is up to date
        plan = getLearnerPlan(plan.getLearnerPlanId());

        //apply retention policy rules to cumulative objectives targets which have them
        processRetentionForCumulativeObjectiveTargets(plan, learnerSessions);

        //re-fetch plan to make sure all data is up to date
        plan = getLearnerPlan(plan.getLearnerPlanId());

        //update cumulative objectives which have been mastered based on mastered objective targets
        updateMasteredCumulativeObjectives(plan);
    }

    private void processPercentageObjective(LearnerSession learnerSession,
                                            LearnerSessionObjective learnerSessionObjective,
                                            LearnerPlanObjective planObjective,
                                            Map<LearnerPlanObjective, Integer> objectivesMastered,
                                            Map<LearnerPlanObjective, Date> lastSessionDatesForMastery) {

        if (learnerSessionObjective.getSessionValue() >= planObjective.getMasteryValue()) {
            Integer currentConsecutiveMasteryCount = objectivesMastered.get(planObjective);
            currentConsecutiveMasteryCount = (currentConsecutiveMasteryCount != null) ? currentConsecutiveMasteryCount + 1 : 1;
            objectivesMastered.put(planObjective, currentConsecutiveMasteryCount);
            lastSessionDatesForMastery.put(planObjective, learnerSession.getSessionDate());
        } else {
            objectivesMastered.remove(planObjective);
            lastSessionDatesForMastery.remove(planObjective);
        }
    }

    private void updateMasteredPercentageObjectives(LearnerPlan plan,
                                                    Map<LearnerPlanObjective, Integer> objectivesMastered,
                                                    Map<LearnerPlanObjective, Date> lastSessionDatesForMastery) {

        //update objectives which have been mastered
        Set<LearnerPlanObjective> keys = objectivesMastered.keySet();
        for (LearnerPlanObjective learnerPlanObjective : keys) {

            Integer currentConsecutiveMasteryCount = objectivesMastered.get(learnerPlanObjective);
            currentConsecutiveMasteryCount = (currentConsecutiveMasteryCount != null) ? currentConsecutiveMasteryCount : 0;
            if (currentConsecutiveMasteryCount >= learnerPlanObjective.getCriteria().getConsecutiveToMastered()) {
                Date masteryDate = lastSessionDatesForMastery.get(learnerPlanObjective);
                learnerPlanObjective.setMasteryDate(masteryDate);
                learnerPlanObjective.setMastered("Y");
                learnerPlanObjectiveRepository.save(learnerPlanObjective);
            }
        }
    }

    private void processCumulativeObjective(LearnerSession learnerSession,
                                            LearnerSessionObjective learnerSessionObjective,
                                            Map<LearnerPlanObjectiveTarget, Integer> objectiveTargetsMastered,
                                            Map<LearnerPlanObjectiveTarget, Date> lastSessionDatesForObjectiveTargetMastery) {

        List<LearnerSessionObjectiveTarget> sessionObjectiveTargets = learnerSessionObjective.getLearnerSessionObjectiveTargets();
        for (LearnerSessionObjectiveTarget sessionObjectiveTarget : sessionObjectiveTargets) {

            LearnerPlanObjectiveTarget planObjectiveTarget = sessionObjectiveTarget.getLearnerPlanObjectiveTarget();
            if (planObjectiveTarget.getMastered().equals("N")) {

                if (sessionObjectiveTarget.getSessionValue() != null &&
                    sessionObjectiveTarget.getInvalidatedByRetention().equals("N") &&
                    sessionObjectiveTarget.getSessionValue() == 1) {
                    Integer currentConsecutiveMasteryCount = objectiveTargetsMastered.get(planObjectiveTarget);
                    currentConsecutiveMasteryCount = (currentConsecutiveMasteryCount != null) ? currentConsecutiveMasteryCount + 1 : 1;
                    objectiveTargetsMastered.put(planObjectiveTarget, currentConsecutiveMasteryCount);
                    lastSessionDatesForObjectiveTargetMastery.put(planObjectiveTarget, learnerSession.getSessionDate());
                } else {
                    objectiveTargetsMastered.remove(planObjectiveTarget);
                    lastSessionDatesForObjectiveTargetMastery.remove(planObjectiveTarget);
                }
            }
        }
    }

    private void updateMasteredCumulativeObjectiveTargets(Map<LearnerPlanObjectiveTarget, Integer> objectiveTargetsMastered,
                                                    Map<LearnerPlanObjectiveTarget, Date> lastSessionDatesForMastery) {

        //update objective targets which have been mastered
        Set<LearnerPlanObjectiveTarget> keys = objectiveTargetsMastered.keySet();
        for (LearnerPlanObjectiveTarget planObjectiveTarget : keys) {
            LearnerPlanObjective planObjective = learnerPlanObjectiveRepository.findOne(planObjectiveTarget.getLearnerPlanObjectiveId());

            Integer currentConsecutiveMasteryCount = objectiveTargetsMastered.get(planObjectiveTarget);
            currentConsecutiveMasteryCount = (currentConsecutiveMasteryCount != null) ? currentConsecutiveMasteryCount : 0;
            if (currentConsecutiveMasteryCount >= planObjective.getCriteria().getConsecutiveToMastered()) {
                Date masteryDate = lastSessionDatesForMastery.get(planObjectiveTarget);
                planObjectiveTarget.setMasteryDate(masteryDate);
                planObjectiveTarget.setMastered("Y");
                if (planObjective.getRetentionProbeEnabled().equals("Y")) {
                    planObjectiveTarget.setRetentionState(TargetRetentionState.RETENTION_IN_WAIT_PERIOD);
                }
                learnerPlanObjectiveTargetRepository.save(planObjectiveTarget);
            }
        }
    }

    private void updateMasteredCumulativeObjectives(LearnerPlan plan) {
        List<LearnerPlanObjective> planObjectives = plan.getLearnerPlanObjectiveList();
        for (LearnerPlanObjective planObjective : planObjectives) {
            if (planObjective.getObjectiveType().getTypeId().equals("C") && planObjective.getMastered().equals("N")) {

                List<LearnerPlanObjectiveTarget> objectiveTargets = planObjective.getLearnerPlanObjectiveTarget();
                Date masteryDate = null;
                Integer totalTargetsMastered = 0;

                for (LearnerPlanObjectiveTarget objectiveTarget : objectiveTargets) {
                    if (objectiveTarget.getMastered().equals("Y")) {

                        //don't count toward mastery if has retention policy but not mastered in retention yet
                        if (planObjective.getRetentionProbeEnabled().equals("Y")) {
                            if (!TargetRetentionState.isMastered(objectiveTarget.getRetentionState())) {
                                continue;
                            }
                        }

                        totalTargetsMastered++;
                        if (masteryDate == null || objectiveTarget.getMasteryDate().after(masteryDate)) {
                            masteryDate = objectiveTarget.getMasteryDate();
                        }
                    }
                }

                if (planObjective.getMasteryValue() != null) {
                    if (totalTargetsMastered >= planObjective.getMasteryValue()) {
                        planObjective.setMasteryDate(masteryDate);
                        planObjective.setMastered("Y");
                        learnerPlanObjectiveRepository.save(planObjective);
                    }
                }
            }
        }
    }

    private void processRetentionForCumulativeObjectiveTargets(LearnerPlan plan, List<LearnerSession> learnerSessions) {
        Date dateNow = new Date();
        List<Long> objectTargetsToInvalidate = new ArrayList<Long>();

        //figure out which items are retention retest ready
        List<LearnerPlanObjective> planObjectives = plan.getLearnerPlanObjectiveList();
        for (LearnerPlanObjective planObjective : planObjectives) {

            if (planObjective.getObjectiveType().getTypeId().equals("C") &&
                    planObjective.getRetentionProbeEnabled().equals("Y")) {

                List<LearnerPlanObjectiveTarget> objectiveTargets = planObjective.getLearnerPlanObjectiveTarget();
                for (LearnerPlanObjectiveTarget objectiveTarget : objectiveTargets) {
                    if (objectiveTarget.getMastered().equals("Y")) {
                        if (TargetRetentionState.isMastered(objectiveTarget.getRetentionState())) {
                            //already mastered in retention, it is done
                        }
                        else  {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(objectiveTarget.getMasteryDate());
                            cal.add(Calendar.DATE, planObjective.getRetentionProbeDaysToRecheck().intValue());
                            Date retentionStartDate = cal.getTime();

                            if (TargetRetentionState.isRetentionInWaitingPeriod(objectiveTarget.getRetentionState())) {
                                if (dateNow.compareTo(retentionStartDate) >= 0) {
                                    objectiveTarget.setRetentionState(TargetRetentionState.RETENTION_RETEST_READY);
                                    learnerPlanObjectiveTargetRepository.save(objectiveTarget);
                                }
                            }
                            else if (TargetRetentionState.isRetentionRetestReady(objectiveTarget.getRetentionState())) {
                                //go through the sessions and see if retention is met
                                for (LearnerSession learnerSession : learnerSessions) {

                                    if (learnerSession.getSessionDate().compareTo(retentionStartDate) >= 0) {
                                        List<LearnerSessionObjective> learnerSessionObjectives = learnerSession.getLearnerSessionObjectiveList();
                                        for (LearnerSessionObjective learnerSessionObjective : learnerSessionObjectives) {
                                            if (learnerSessionObjective.getLearnerPlanObjective().getLearnerPlanObjectiveId().equals(planObjective.getLearnerPlanObjectiveId())) {

                                                List<LearnerSessionObjectiveTarget> sessionObjectiveTargets = learnerSessionObjective.getLearnerSessionObjectiveTargets();
                                                for (LearnerSessionObjectiveTarget sessionObjectiveTarget : sessionObjectiveTargets) {

                                                    if (objectiveTarget.getLearnerPlanObjectiveTargetId().equals(sessionObjectiveTarget.getLearnerPlanObjectiveTarget().getLearnerPlanObjectiveTargetId())) {
                                                        if (sessionObjectiveTarget.getSessionValue() == 1) {
                                                            //retention is mastered
                                                            objectiveTarget.setRetentionState(TargetRetentionState.RETENTION_MASTERED);
                                                            learnerPlanObjectiveTargetRepository.save(objectiveTarget);
                                                        }
                                                        else if (sessionObjectiveTarget.getSessionValue() == -1) {
                                                            //it failed in retention probe, negate all data until now
                                                            objectTargetsToInvalidate.add(objectiveTarget.getLearnerPlanObjectiveTargetId());

                                                            objectiveTarget.setRetentionState(TargetRetentionState.HAS_RETENTION_POLICY);
                                                            objectiveTarget.setMastered("N");
                                                            objectiveTarget.setMasteryDate(null);
                                                            learnerPlanObjectiveTargetRepository.save(objectiveTarget);
                                                        }
                                                        else if (sessionObjectiveTarget.getSessionValue() == 0) {
                                                            //no value means not tested, do nothing
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        invalidateObjectiveTargetSessionData(objectTargetsToInvalidate);
    }

    private void invalidateObjectiveTargetSessionData(List<Long> objectTargetsToInvalidate) {
        for (Long objectiveTargetId : objectTargetsToInvalidate) {
            LearnerPlanObjectiveTarget learnerPlanObjectiveTarget = learnerPlanObjectiveTargetRepository.findOne(objectiveTargetId);
            List<LearnerSessionObjectiveTarget> sessionTargets = learnerSessionObjectiveTargetRepository.findByLearnerPlanObjectiveTarget(learnerPlanObjectiveTarget);
            for (LearnerSessionObjectiveTarget sessionTarget : sessionTargets) {
                sessionTarget.setInvalidatedByRetention("Y");
                learnerSessionObjectiveTargetRepository.save(sessionTarget);
            }
        }
    }

    private  boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
