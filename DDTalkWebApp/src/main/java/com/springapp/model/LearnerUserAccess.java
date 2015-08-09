package com.springapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="learner_user_access")
public class LearnerUserAccess {

    @Id
    @Column(name = "learner_id")
    Long learnerId;

    @Column(name = "user_id")
    Long userId;

    public Long getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(Long learnerId) {
        this.learnerId = learnerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
