package com.springapp.repositories;

import com.springapp.model.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
    Criteria findByDescription(String description);
}
