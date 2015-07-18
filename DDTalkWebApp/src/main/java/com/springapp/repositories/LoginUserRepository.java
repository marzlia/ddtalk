package com.springapp.repositories;

import com.springapp.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peter on 5/19/15.
 */
@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {

    LoginUser findByUsername(String username);
}
