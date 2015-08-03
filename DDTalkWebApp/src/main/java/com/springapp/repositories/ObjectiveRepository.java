package com.springapp.repositories;


import com.springapp.model.Domain;
import com.springapp.model.Objective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
    List<Objective> findByDomain( Domain domain );
}
