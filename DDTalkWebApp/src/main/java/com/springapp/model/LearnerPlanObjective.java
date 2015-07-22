package com.springapp.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="learner_plan_objective")
public class LearnerPlanObjective {

    @Id
    @GeneratedValue
    @Column(name = "learner_plan_objective_id")
    Long learnerPlanObjectiveId;

    @Column(name = "learner_plan_id")
    Long learnerPlanId;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "objective_id")
    Objective objective;

    @Column(name = "objective_type")
    String objectiveType;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "learner_plan_objective_id")
    LearnerPlanObjectiveData learnerPlanObjectiveData;

}
