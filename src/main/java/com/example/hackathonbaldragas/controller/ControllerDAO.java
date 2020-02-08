package com.example.hackathonbaldragas.controller;

import com.example.hackathonbaldragas.domain.User;
import com.example.hackathonbaldragas.persistence.UserDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("controller")
public class ControllerDAO {

    private final UserDAO userDAO;


    public ControllerDAO(UserDAO u) {
        this.userDAO = u;
    }

    //USER

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

}
