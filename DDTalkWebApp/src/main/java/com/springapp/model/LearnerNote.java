package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="learner_note")
public class LearnerNote {

    @Id
    @GeneratedValue
    @Column(name = "learner_note_id")
    Long learnerNoteId;

    @Column(name = "learner_id")
    Long learnerId;

    @Column(name = "note")
    String note;

    public Long getLearnerNoteId() {
        return learnerNoteId;
    }

    public void setLearnerNoteId(Long learnerNoteId) {
        this.learnerNoteId = learnerNoteId;
    }

    public Long getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(Long learnerId) {
        this.learnerId = learnerId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
