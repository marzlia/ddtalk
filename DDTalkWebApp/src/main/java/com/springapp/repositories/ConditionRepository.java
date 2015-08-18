package com.springapp.repositories;

import com.springapp.model.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {
    Condition findByDescription(String description);
}
