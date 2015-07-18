package com.springapp.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="domains")
public class Domain {

    @Id
    @GeneratedValue
    @Column(name = "domain_id")
    Long domainId;

    @Column(name = "description")
    String description;


    @ManyToMany(targetEntity = Objective.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "domain_objectives",
            joinColumns = { @JoinColumn(name = "domain_id") },
            inverseJoinColumns = { @JoinColumn(name = "objective_id") })
    private List<Objective> objectives;

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<Objective> objectives) {
        this.objectives = objectives;
    }
}
