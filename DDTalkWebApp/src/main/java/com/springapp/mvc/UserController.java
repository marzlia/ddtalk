package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    HttpServletRequest httpServletRequest;


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {

        try {
            httpServletRequest.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "redirect:/learner/user";
    }

}
