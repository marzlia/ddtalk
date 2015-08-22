package com.springapp.mvc;

import com.springapp.model.*;
import com.springapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lookup")
public class LookupController {

    @Autowired
    ConditionService conditionService;

    @Autowired
    CriteriaService criteriaService;

    @Autowired
    DomainService domainService;

    @Autowired
    ObjectiveService objectiveService;

    @RequestMapping(value = "/conditions", method = RequestMethod.GET)
    @ResponseBody
    public List<Condition> getConditions() {

        List<Condition> list = conditionService.getAllConditions();
        return list;
    }

    @RequestMapping(value = "/conditions/create", method = RequestMethod.POST)
    @ResponseBody
    public Condition createCondition(@RequestBody Condition condition) {

        Condition persistedCondition = conditionService.createCondition(condition);
        return persistedCondition;
    }

    @RequestMapping(value = "/criteria", method = RequestMethod.GET)
    @ResponseBody
    public List<Criteria> getCriteria() {

        List<Criteria> list = criteriaService.getAllCriteria();
        return list;
    }

    @RequestMapping(value = "/criteriaByObjectiveTypeId/{objectiveTypeId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Criteria> criteriaByObjectiveTypeId(@PathVariable String objectiveTypeId) {

        List<Criteria> list = criteriaService.getCriteriaByObjectiveTypeId(Long.parseLong(objectiveTypeId));
        return list;
    }

    @RequestMapping(value = "/criteria/create", method = RequestMethod.POST)
    @ResponseBody
    public Criteria createCriteria(@RequestBody Criteria criteria) {

        Criteria persistedCriteria = criteriaService.createCriteria(criteria);
        return persistedCriteria;
    }

    @RequestMapping(value = "/domains", method = RequestMethod.GET)
    @ResponseBody
    public List<Domain> getDomains() {

        List<Domain> list = domainService.getAllDomains();
        return list;
    }

    @RequestMapping(value = "/domain/create", method = RequestMethod.POST)
    @ResponseBody
    public Domain createCriteria(@RequestBody Domain domain) {

        Domain persistedDomain = domainService.createDomain(domain);
        return persistedDomain;
    }

    @RequestMapping(value = "/objectives/{domainId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Objective> objectivesForDomain(@PathVariable String domainId) {

        List<Objective> objectives = objectiveService.objectivesForDomainId(domainId);
        return objectives;
    }







}
