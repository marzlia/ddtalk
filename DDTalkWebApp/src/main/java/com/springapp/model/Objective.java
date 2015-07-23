package com.springapp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="objectives")
public class Objective {

    @Id
    @GeneratedValue
    @Column(name = "objective_id")
    Long objectiveId;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "domain_id")
    Domain domain;

    @Column(name = "description")
    String description;

    public Long getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
}
