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
}
