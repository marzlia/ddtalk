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

    @Column(name = "description")
    String description;

//    @ManyToMany(targetEntity = Targets.class, cascade = { CascadeType.ALL })
//    @JoinTable(name = "objective_targets",
//            joinColumns = { @JoinColumn(name = "objective_id") },
//            inverseJoinColumns = { @JoinColumn(name = "target_id") })
//    private Set<Targets> targets;

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

}
