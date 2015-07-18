package com.springapp.service;

import com.springapp.model.Condition;
import com.springapp.repositories.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConditionService {

    @Autowired
    ConditionRepository conditionRepository;

    public Condition getCondition(Long longId) {
        return conditionRepository.findOne(longId);
    }

    public List<Condition> getAllConditions() {
        return conditionRepository.findAll();
    }

    public Condition createCondition(Condition condition) {
        Condition persistedCondtion = conditionRepository.save(condition);
        return persistedCondtion;
    }
}
