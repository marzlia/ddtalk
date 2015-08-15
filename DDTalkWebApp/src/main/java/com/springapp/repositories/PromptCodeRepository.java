package com.springapp.repositories;

import com.springapp.model.PromptCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PromptCodeRepository extends JpaRepository<PromptCode, Long> {

}
