package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="learner_plan_domain_objective")
public class LearnerPlanDomainObjective {

    @Id
    @GeneratedValue
    @Column(name = "internal_id")
    Long internalId;

    @Column(name = "learner_plan_id")
    Long learnerPlanId;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "domain_id")
    Domain domain;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "objective_id")
    Objective objective;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "condition_id")
    Condition condition;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "criteria_id")
    Criteria criteria;

    @Column(name = "percent_for_mastery")
    Long percentForMastery;

}
