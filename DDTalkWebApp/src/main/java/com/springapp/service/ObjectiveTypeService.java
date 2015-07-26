package com.springapp.service;

import com.springapp.model.Condition;
import com.springapp.model.ObjectiveType;
import com.springapp.repositories.ConditionRepository;
import com.springapp.repositories.ObjectiveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectiveTypeService {

    @Autowired
    ObjectiveTypeRepository objectiveTypeRepository;

    public ObjectiveType getObjectiveType(Long longId) {
        return objectiveTypeRepository.findOne(longId);
    }

    public List<ObjectiveType> getAllTypes() {
        return objectiveTypeRepository.findAll();
    }
}
