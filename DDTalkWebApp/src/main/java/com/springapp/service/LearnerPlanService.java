package com.springapp.service;

import com.springapp.model.*;
import com.springapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

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

    public List<LearnerPlan> getAllLearnerPlans() {
        return learnerPlanRepository.findAll();
    }

    public List<LearnerPlan> getPlansForLearnerId(Long learnerId) {
        return learnerPlanRepository.findByLearnerId(learnerId);
    }

    public LearnerPlan getLearnerPlan(Long planId) {
        return learnerPlanRepository.findOne(planId);
    }

    public void addObjectiveToLearnerPlan(String planId, String domainId, String objectiveId, String objectiveTypeId) {
        LearnerPlanObjective learnerPlanObjective = new LearnerPlanObjective();
        learnerPlanObjective.setLearnerPlanId(Long.parseLong(planId));


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

        Criteria criteria = criteriaRepository.findByDescription("None");
        learnerPlanObjective.setCriteria(criteria);

        learnerPlanObjectiveRepository.save(learnerPlanObjective);
    }

    public void updatePlanObjective(Long planObjectiveId, Long conditionId, Long criteriaId, Long masteryValue) {
        LearnerPlanObjective learnerPlanObjective = learnerPlanObjectiveRepository.findOne(planObjectiveId);
        Condition condition = conditionRepository.findOne(conditionId);
        Criteria criteria = criteriaRepository.findOne(criteriaId);

        learnerPlanObjective.setCondition(condition);
        learnerPlanObjective.setCriteria(criteria);
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
        LearnerPlanObjectiveTarget learnerPlanObjectiveTarget = new LearnerPlanObjectiveTarget();
        learnerPlanObjectiveTarget.setLearnerPlanObjectiveId(planObjectiveId);
        learnerPlanObjectiveTarget.setTargetDescription(targets);

        learnerPlanObjectiveTargetRepository.save(learnerPlanObjectiveTarget);
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


    private  boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
