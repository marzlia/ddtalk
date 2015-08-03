package com.springapp.repositories;


import com.springapp.model.Objective;
import com.springapp.model.ObjectiveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ObjectiveTypeRepository extends JpaRepository<ObjectiveType, Long> {

    public ObjectiveType findByTypeId(String typeId);
}
