package com.springapp.service;

import com.springapp.model.Learner;
import com.springapp.repositories.LearnerRepository;
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

    public Learner getLearner(Long longId) {
        return learnerRepository.findOne(longId);
    }

    public List<Learner> getAllLearners() {
        return learnerRepository.findAll();
    }

    public Learner saveLearner(Learner newLearner) {
        return learnerRepository.save(newLearner);
    }
}
