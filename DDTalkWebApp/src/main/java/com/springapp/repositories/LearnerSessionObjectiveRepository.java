package com.springapp.repositories;

import com.springapp.model.LearnerSession;
import com.springapp.model.LearnerSessionObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peter on 5/19/15.
 */
@Repository
public interface LearnerSessionObjectiveRepository extends JpaRepository<LearnerSessionObjective, Long> {

    List<LearnerSessionObjective> findByLearnerSessionId(Long learnerSessionId);
}
