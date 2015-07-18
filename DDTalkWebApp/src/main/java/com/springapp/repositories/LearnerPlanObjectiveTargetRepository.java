package com.springapp.repositories;

import com.springapp.model.LearnerPlanObjectiveTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerPlanObjectiveTargetRepository extends JpaRepository<LearnerPlanObjectiveTarget, Long> {

}
