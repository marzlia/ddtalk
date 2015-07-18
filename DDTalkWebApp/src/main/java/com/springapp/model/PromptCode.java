package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="prompt_code")
public class PromptCode {

    @Id
    @GeneratedValue
    @Column(name = "prompt_code_id")
    Long promptCodeId;

    @Column(name = "description")
    String description;

    public Long getPromptCodeId() {
        return promptCodeId;
    }

    public void setPromptCodeId(Long promptCodeId) {
        this.promptCodeId = promptCodeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
