package com.springapp.model;

import javax.persistence.*;

/**
 * Created by peter on 7/26/15.
 */
@Entity
@Table(name="objective_type")
public class ObjectiveType {

    @Id
    @GeneratedValue
    @Column(name = "objective_type_id")
    Long objectiveTypeId;

    @Column(name = "type_id")
    String typeId;

    @Column(name = "description")
    String typeDescription;

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
