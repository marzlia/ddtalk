package com.springapp.repositories;

import com.springapp.model.LearnerSessionObjectiveTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peter on 5/19/15.
 */
@Repository
public interface LearnerSessionObjectiveTargetRepository extends JpaRepository<LearnerSessionObjectiveTarget, Long> {

    List<LearnerSessionObjectiveTarget> findByLearnerSessionObjectiveId(Long learnerSessionObjectiveId);
}
