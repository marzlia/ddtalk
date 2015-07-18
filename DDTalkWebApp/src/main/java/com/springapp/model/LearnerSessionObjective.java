package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="learner_session_objective")
public class LearnerSessionObjective {

    @Id
    @GeneratedValue
    @Column(name = "internalId")
    Long internalId;

    @Column(name = "learner_session_id")
    Long learnerSessionId;

    @Column(name = "objective_id")
    Long objectiveId;

    @Column(name = "percent")
    Long percent;

    public Long getLearnerSessionId() {
        return learnerSessionId;
    }

    public void setLearnerSessionId(Long learnerSessionId) {
        this.learnerSessionId = learnerSessionId;
    }

    public Long getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }
}
