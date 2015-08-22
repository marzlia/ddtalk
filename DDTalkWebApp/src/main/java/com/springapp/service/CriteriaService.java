package com.springapp.service;

import com.springapp.model.Criteria;
import com.springapp.model.ObjectiveType;
import com.springapp.repositories.CriteriaRepository;
import com.springapp.repositories.ObjectiveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriteriaService {

    @Autowired
    CriteriaRepository criteriaRepository;

    @Autowired
    ObjectiveTypeRepository objectiveTypeRepository;

    public Criteria getCriteria(Long longId) {
        return criteriaRepository.findOne(longId);
    }

    public List<Criteria> getAllCriteria() {
        return criteriaRepository.findAll();
    }

    public List<Criteria> getCriteriaByObjectiveTypeId(Long objectiveTypeId) {
        return criteriaRepository.findByObjectiveType(objectiveTypeRepository.findOne(objectiveTypeId));
    }

    public Criteria createCriteria(Criteria criteria) {
        Criteria persistedCriteria = criteriaRepository.save(criteria);
        return persistedCriteria;
    }
}
