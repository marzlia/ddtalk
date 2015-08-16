package com.springapp.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="learner_session_objective")
public class LearnerSessionObjective {

    @Id
    @GeneratedValue
    @Column(name = "learner_session_objective_id")
    Long learnerSessionObjectiveId;

    @Column(name = "learner_session_id")
    Long learnerSessionId;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "learner_plan_objective_id")
    LearnerPlanObjective learnerPlanObjective;

    @Column(name = "session_value")
    Long sessionValue;

    @Column(name = "mastery_date")
    Date masteryDate;

    @OneToMany(cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "learner_session_objective_id")
    List<LearnerSessionObjectiveTarget> learnerSessionObjectiveTargets;

    public Long getLearnerSessionId() {
        return learnerSessionId;
    }

    public void setLearnerSessionId(Long learnerSessionId) {
        this.learnerSessionId = learnerSessionId;
    }

    public Long getLearnerSessionObjectiveId() {
        return learnerSessionObjectiveId;
    }

    public void setLearnerSessionObjectiveId(Long learnerSessionObjectiveId) {
        this.learnerSessionObjectiveId = learnerSessionObjectiveId;
    }

    public LearnerPlanObjective getLearnerPlanObjective() {
        return learnerPlanObjective;
    }

    public void setLearnerPlanObjective(LearnerPlanObjective learnerPlanObjective) {
        this.learnerPlanObjective = learnerPlanObjective;
    }

    public Long getSessionValue() {
        return sessionValue;
    }

    public void setSessionValue(Long sessionValue) {
        this.sessionValue = sessionValue;
    }

    public List<LearnerSessionObjectiveTarget> getLearnerSessionObjectiveTargets() {
        return learnerSessionObjectiveTargets;
    }

    public void setLearnerSessionObjectiveTargets(List<LearnerSessionObjectiveTarget> learnerSessionObjectiveTargets) {
        this.learnerSessionObjectiveTargets = learnerSessionObjectiveTargets;
    }

    public Date getMasteryDate() {
        return masteryDate;
    }

    public void setMasteryDate(Date masteryDate) {
        this.masteryDate = masteryDate;
    }
}
