package com.springapp.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="learner_session")
public class LearnerSession {

    @Id
    @GeneratedValue
    @Column(name = "learner_session_id")
    Long learnerSessionId;

    @Column(name = "learner_plan_id")
    Long learnerPlanId;

    @Column(name = "session_date")
    Date sessionDate;

    @OneToMany(cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "learner_session_id")
    List<LearnerSessionObjective> learnerSessionObjectiveList;

    public Long getLearnerSessionId() {
        return learnerSessionId;
    }

    public void setLearnerSessionId(Long learnerSessionId) {
        this.learnerSessionId = learnerSessionId;
    }

    public Long getLearnerPlanId() {
        return learnerPlanId;
    }

    public void setLearnerPlanId(Long learnerPlanId) {
        this.learnerPlanId = learnerPlanId;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public List<LearnerSessionObjective> getLearnerSessionObjectiveList() {
        return learnerSessionObjectiveList;
    }

    public void setLearnerSessionObjectiveList(List<LearnerSessionObjective> learnerSessionObjectiveList) {
        this.learnerSessionObjectiveList = learnerSessionObjectiveList;
    }
}
