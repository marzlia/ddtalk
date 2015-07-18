package com.springapp.repositories;

import com.springapp.model.Learner;
import com.springapp.model.LearnerPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peter on 5/19/15.
 */
@Repository
public interface LearnerPlanRepository extends JpaRepository<LearnerPlan, Long> {

    List<LearnerPlan> findByLearnerId(Long learnerId);
    List<LearnerPlan> findByLearnerIdAndTreatmentType(Long learnerId, String treatmentType);

}
