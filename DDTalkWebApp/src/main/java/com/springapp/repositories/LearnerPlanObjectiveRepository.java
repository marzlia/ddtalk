package com.springapp.repositories;

import com.springapp.model.LearnerPlanObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearnerPlanObjectiveRepository extends JpaRepository<LearnerPlanObjective, Long> {

    List<LearnerPlanObjective> findByLearnerPlanId(Long learnerPlanId);
}
