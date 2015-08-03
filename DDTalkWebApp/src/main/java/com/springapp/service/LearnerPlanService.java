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
    ObjectiveRepository objectiveRepository;

    @Autowired
    ObjectiveTypeRepository objectiveTypeRepository;

    public List<LearnerPlan> getAllLearnerPlans() {
        return learnerPlanRepository.findAll();
    }

    public List<LearnerPlan> getPlansForLearnerId(Long learnerId) {
        return learnerPlanRepository.findByLearnerId(learnerId);
    }

    public LearnerPlan getLearnerPlan(Long planId) {
        return learnerPlanRepository.findOne(planId);
    }

    public void addObjectiveToLearnerPlan(Long planId, Long objectiveId) {
        LearnerPlanObjective learnerPlanObjective = new LearnerPlanObjective();
        learnerPlanObjective.setLearnerPlanId(planId);

        Objective objective = objectiveRepository.findOne(objectiveId);
        learnerPlanObjective.setObjective(objective);

        ObjectiveType objectiveType = objectiveTypeRepository.findByTypeId("P");
        learnerPlanObjective.setObjectiveType(objectiveType);

        learnerPlanObjectiveRepository.save(learnerPlanObjective);
    }


}
