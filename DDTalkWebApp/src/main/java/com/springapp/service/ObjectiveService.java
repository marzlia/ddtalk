package com.springapp.service;

import com.springapp.model.Domain;
import com.springapp.model.Objective;
import com.springapp.repositories.ObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by peter on 7/31/15.
 */
@Service
public class ObjectiveService {

    @Autowired
    ObjectiveRepository objectiveRepository;

    public List<Objective> objectivesForDomainId(String domainId) {
        Domain domain = new Domain();
        domain.setDomainId(Long.parseLong(domainId));

        return objectiveRepository.findByDomain(domain);
    }
}
