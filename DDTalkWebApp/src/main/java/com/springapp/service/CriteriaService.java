package com.springapp.service;

import com.springapp.model.Criteria;
import com.springapp.repositories.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriteriaService {

    @Autowired
    CriteriaRepository criteriaRepository;

    public Criteria getCriteria(Long longId) {
        return criteriaRepository.findOne(longId);
    }

    public List<Criteria> getAllCriteria() {
        return criteriaRepository.findAll();
    }

    public Criteria createCriteria(Criteria criteria) {
        Criteria persistedCriteria = criteriaRepository.save(criteria);
        return persistedCriteria;
    }
}
