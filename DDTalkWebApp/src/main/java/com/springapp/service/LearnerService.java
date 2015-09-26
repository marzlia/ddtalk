package com.springapp.service;

import com.springapp.model.Learner;
import com.springapp.model.LearnerUserAccess;
import com.springapp.model.LoginUser;
import com.springapp.repositories.LearnerRepository;
import com.springapp.repositories.LoginUserAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by peter on 5/19/15.
 */
@Service
public class LearnerService {

    @Autowired
    LearnerRepository learnerRepository;

    @Autowired
    LoginUserAccessRepository loginUserAccessRepository;

    public Learner getLearner(Long longId) {
        return learnerRepository.findOne(longId);
    }

    public List<Learner> getAllLearners() {
        return learnerRepository.findAll();
    }

    public Learner saveLearner(Learner newLearner) {
        return learnerRepository.save(newLearner);
    }

    public void linkLearnerToLoginUser(Learner learner, LoginUser loginUser) {
        LearnerUserAccess learnerUserAccess = new LearnerUserAccess();
        learnerUserAccess.setUserId(loginUser.getUserId());
        learnerUserAccess.setLearnerId(learner.getLearnerId());
        loginUserAccessRepository.save(learnerUserAccess);
    }

    public void deleteLearner(Long longId) {
        learnerRepository.delete(longId);
    }

}
