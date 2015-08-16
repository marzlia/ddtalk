package com.springapp.model;

import javax.persistence.*;

@Entity
@Table(name="prompt_code")
public class PromptCode {

    @Id
    @GeneratedValue
    @Column(name = "prompt_code_id")
    Long promptCodeId;

    @Column(name = "prompt_code")
    String promptCode;

    @Column(name = "description")
    String description;

    public Long getPromptCodeId() {
        return promptCodeId;
    }

    public void setPromptCodeId(Long promptCodeId) {
        this.promptCodeId = promptCodeId;
    }

    public String getPromptCode() {
        return promptCode;
    }

    public void setPromptCode(String promptCode) {
        this.promptCode = promptCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
