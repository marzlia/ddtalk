package com.springapp.repositories;

import com.springapp.model.LearnerPlanObjectiveData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerPlanObjectiveDataRepository extends JpaRepository<LearnerPlanObjectiveData, Long> {

    LearnerPlanObjectiveData findByLearnerPlanObjectiveId(String learnerPlanObjectiveOd);
}
