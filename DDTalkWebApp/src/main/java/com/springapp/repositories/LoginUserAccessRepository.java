package com.springapp.repositories;

import com.springapp.model.LearnerUserAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by peter on 5/19/15.
 */
@Repository
public interface LoginUserAccessRepository extends JpaRepository<LearnerUserAccess, Long> {
}
