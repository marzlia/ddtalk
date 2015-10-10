package com.springapp.repositories;

import com.springapp.model.LearnerNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearnerNoteRepository extends JpaRepository<LearnerNote, Long> {

    LearnerNote findByLearnerId(Long learnerId);
}

