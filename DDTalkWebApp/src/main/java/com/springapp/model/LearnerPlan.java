package com.springapp.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="learner_plan")
public class LearnerPlan {

    @Id
    @GeneratedValue
    @Column(name = "learner_plan_id")
    Long learnerPlanId;

    @Column(name = "learner_id")
    Long learnerId;

    @Column(name = "treatment_description")
    String treatmentDescription;

    @Column(name = "date_start_plan")
    Date dateStartPlan;

    @Column(name = "treatment_frequency")
    String treatmentFrequency;

    @Column(name = "data_collect_frequency")
    String dataCollectFrequency;

    @Column(name = "target_num_fluency_probes")
    Long targetNumFluencyProbes;

    @Column(name = "target_enable_retention_probes")
    String targetEnableRetentionProbes;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "learner_plan_id")
    List<LearnerPlanObjective> learnerPlanObjectiveList;

    public Long getLearnerPlanId() {
        return learnerPlanId;
    }

    public void setLearnerPlanId(Long learnerPlanId) {
        this.learnerPlanId = learnerPlanId;
    }

    public Long getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(Long learnerId) {
        this.learnerId = learnerId;
    }

    public Date getDateStartPlan() {
        return dateStartPlan;
    }

    public void setDateStartPlan(Date dateStartPlan) {
        this.dateStartPlan = dateStartPlan;
    }

    public String getTreatmentFrequency() {
        return treatmentFrequency;
    }

    public void setTreatmentFrequency(String treatmentFrequency) {
        this.treatmentFrequency = treatmentFrequency;
    }

    public String getDataCollectFrequency() {
        return dataCollectFrequency;
    }

    public void setDataCollectFrequency(String dataCollectFrequency) {
        this.dataCollectFrequency = dataCollectFrequency;
    }

    public Long getTargetNumFluencyProbes() {
        return targetNumFluencyProbes;
    }

    public void setTargetNumFluencyProbes(Long targetNumFluencyProbes) {
        this.targetNumFluencyProbes = targetNumFluencyProbes;
    }

    public String getTargetEnableRetentionProbes() {
        return targetEnableRetentionProbes;
    }

    public void setTargetEnableRetentionProbes(String targetEnableRetentionProbes) {
        this.targetEnableRetentionProbes = targetEnableRetentionProbes;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    public List<LearnerPlanObjective> getLearnerPlanObjectiveList() {
        return learnerPlanObjectiveList;
    }

    public void setLearnerPlanObjectiveList(List<LearnerPlanObjective> learnerPlanObjectiveList) {
        this.learnerPlanObjectiveList = learnerPlanObjectiveList;
    }
}
