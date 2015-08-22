package com.springapp.repositories;

import com.springapp.model.Criteria;
import com.springapp.model.ObjectiveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
    Criteria findByDescriptionAndObjectiveType(String description, ObjectiveType objectiveType);

    List<Criteria> findByObjectiveType(ObjectiveType objectiveType);
}
