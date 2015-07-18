package com.springapp.service;

import com.springapp.model.Domain;
import com.springapp.repositories.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService {

    @Autowired
    DomainRepository domainRepository;

    public Domain getDomain(Long longId) {
        return domainRepository.findOne(longId);
    }

    public List<Domain> getAllDomains() {
        return domainRepository.findAll();
    }

    public Domain createDomain(Domain domain) {
        Domain persistedDomain = domainRepository.save(domain);
        return persistedDomain;
    }
}
