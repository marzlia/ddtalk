package com.springapp.service;

import com.springapp.model.Learner;
import com.springapp.model.LearnerPlan;
import com.springapp.repositories.LearnerPlanRepository;
import com.springapp.repositories.LearnerRepository;
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

    public List<LearnerPlan> getAllLearnerPlans() {
        return learnerPlanRepository.findAll();
    }

    public List<LearnerPlan> getObjectivePlansForLearnerId(Long learnerId) {
        return learnerPlanRepository.findByLearnerIdAndTreatmentType(learnerId, "Objective");
    }

    public List<LearnerPlan> getTargetPlansForLearnerId(Long learnerId) {
        return learnerPlanRepository.findByLearnerIdAndTreatmentType(learnerId, "Target");
    }

    public LearnerPlan getLearnerPlan(Long planId) {
        return learnerPlanRepository.findOne(planId);
    }

}
