package com.springapp.service;

import com.springapp.model.*;
import com.springapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by peter on 5/19/15.
 */
@Service
public class LearnerSessionService {

    @Autowired
    LearnerSessionRepository learnerSessionRepository;

    @Autowired
    LearnerSessionObjectiveRepository learnerSessionObjectiveRepository;

    @Autowired
    LearnerSessionObjectiveTargetRepository learnerSessionObjectiveTargetRepository;

    @Autowired
    PromptCodeRepository promptCodeRepository;

    public List<LearnerSession> getSessionsForLearnerPlanId(Long learnerPlanId) {
        return learnerSessionRepository.findByLearnerPlanId(learnerPlanId);
    }

    public LearnerSession getLearnerSession(Long learnerSessionId) {
        return learnerSessionRepository.findOne(learnerSessionId);
    }

    public LearnerSession createNewSessionForLearnerPlan(LearnerPlan learnerPlan) {
        //create main session object
        LearnerSession learnerSession = new LearnerSession();
        learnerSession.setLearnerPlanId(learnerPlan.getLearnerPlanId());
        learnerSession.setSessionDate(new Date());
        learnerSession = learnerSessionRepository.save(learnerSession);

        //add session objectives
        List<LearnerSessionObjective> learnerSessionObjectives = new ArrayList<LearnerSessionObjective>();
        for (LearnerPlanObjective objective : learnerPlan.getLearnerPlanObjectiveList()) {
            LearnerSessionObjective learnerSessionObjective = new LearnerSessionObjective();

            learnerSessionObjective.setLearnerPlanObjective(objective);
            learnerSessionObjective.setLearnerSessionId(learnerSession.getLearnerSessionId());
            learnerSessionObjective = learnerSessionObjectiveRepository.save(learnerSessionObjective);

            //add objective targets if cumulative
            if (objective.getObjectiveType().getTypeId().equals("C")) {
                List<LearnerSessionObjectiveTarget> learnerSessionObjectiveTargets = new ArrayList<LearnerSessionObjectiveTarget>();
                for (LearnerPlanObjectiveTarget objectiveTarget : objective.getLearnerPlanObjectiveTarget()) {
                    LearnerSessionObjectiveTarget learnerSessionObjectiveTarget = new LearnerSessionObjectiveTarget();

                    learnerSessionObjectiveTarget.setLearnerPlanObjectiveTarget(objectiveTarget);
                    learnerSessionObjectiveTarget.setLearnerSessionObjectiveId(learnerSessionObjective.getLearnerSessionObjectiveId());

                    learnerSessionObjectiveTargets.add(learnerSessionObjectiveTarget);
                }
                learnerSessionObjective.setLearnerSessionObjectiveTargets(learnerSessionObjectiveTargets);
            }

            learnerSessionObjectives.add(learnerSessionObjective);
        }
        learnerSession.setLearnerSessionObjectiveList(learnerSessionObjectives);

        return learnerSessionRepository.save(learnerSession);
    }

    public LearnerSession saveLearnerSession(LearnerSession learnerSession) {
        return learnerSessionRepository.save(learnerSession);
    }

    public void updateSessionObjective(Long sessionObjectiveId, Long sessionValue) {
        LearnerSessionObjective learnerSessionObjective = learnerSessionObjectiveRepository.findOne(sessionObjectiveId);

        learnerSessionObjective.setSessionValue(sessionValue);
        learnerSessionObjectiveRepository.save(learnerSessionObjective);
    }

    public LearnerSessionObjective getSessionObjective(LearnerPlanObjective learnerPlanObjective, Long sessionId) {
        LearnerSessionObjective learnerSessionObjective = learnerSessionObjectiveRepository.findByLearnerPlanObjectiveAndLearnerSessionId(learnerPlanObjective, sessionId);
        return learnerSessionObjective;
    }

    public void updateSessionObjectiveTarget(Long sessionObjectiveTargetId, Long promptCodeId, Long sessionValue) {
        LearnerSessionObjectiveTarget learnerSessionObjectiveTarget = learnerSessionObjectiveTargetRepository.findOne(sessionObjectiveTargetId);
        PromptCode promptCode = promptCodeRepository.findOne(promptCodeId);

        learnerSessionObjectiveTarget.setSessionValue(sessionValue);
        learnerSessionObjectiveTarget.setPromptCode(promptCode);
        learnerSessionObjectiveTargetRepository.save(learnerSessionObjectiveTarget);
    }



}
