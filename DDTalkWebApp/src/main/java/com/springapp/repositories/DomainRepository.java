package com.springapp.repositories;


import com.springapp.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {

}
