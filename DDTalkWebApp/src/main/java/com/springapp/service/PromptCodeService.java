package com.springapp.service;

import com.springapp.model.Condition;
import com.springapp.model.PromptCode;
import com.springapp.repositories.ConditionRepository;
import com.springapp.repositories.PromptCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptCodeService {

    @Autowired
    PromptCodeRepository promptCodeRepository;

    public PromptCode getPromptCode(Long longId) {
        return promptCodeRepository.findOne(longId);
    }

    public List<PromptCode> getAllPromptCodes() {
        return promptCodeRepository.findAll();
    }
}
