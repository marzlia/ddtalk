package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="domains")
public class Domain implements Comparable<Domain>{

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

    public int compareTo(Domain domain) {
        if(this.getDescription() != null && domain.getDescription() != null){
            return this.getDescription().compareToIgnoreCase(domain.getDescription());
        }
        return 0;
    }
}
