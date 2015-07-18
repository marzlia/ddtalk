package com.springapp.model;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "has_mastered")
    Long hasMastered;

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

    public Long getHasMastered() {
        return hasMastered;
    }

    public void setHasMastered(Long hasMastered) {
        this.hasMastered = hasMastered;
    }
}
