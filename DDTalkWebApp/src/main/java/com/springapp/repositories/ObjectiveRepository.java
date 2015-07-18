package com.springapp.repositories;


import com.springapp.model.Objective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, Long> {

}
