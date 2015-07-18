package com.springapp.repositories;

import com.springapp.model.LearnerPlanDomainObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerPlanDomainObjectiveRepository extends JpaRepository<LearnerPlanDomainObjective, Long> {

}
