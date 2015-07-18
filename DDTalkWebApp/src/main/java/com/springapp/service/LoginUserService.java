package com.springapp.service;

import com.springapp.model.Learner;
import com.springapp.model.LoginUser;
import com.springapp.repositories.LearnerRepository;
import com.springapp.repositories.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by peter on 5/19/15.
 */
@Service
public class LoginUserService {

    @Autowired
    LoginUserRepository loginUserRepository;

    public LoginUser getLoginUser(String username) {
        return loginUserRepository.findByUsername(username);
    }
}
