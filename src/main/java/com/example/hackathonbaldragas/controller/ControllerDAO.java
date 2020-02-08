package com.example.hackathonbaldragas.controller;

import com.example.hackathonbaldragas.domain.Category;
import com.example.hackathonbaldragas.domain.User;
import com.example.hackathonbaldragas.persistence.CategoryDAO;
import com.example.hackathonbaldragas.persistence.UserDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("controller")
public class ControllerDAO {

    private final UserDAO userDAO;
    private final CategoryDAO categoryDAO;


    public ControllerDAO(UserDAO u, CategoryDAO c) {
        this.userDAO = u;
        this.categoryDAO = c;
    }

    //USER

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }


    //CATEGORY

    public List<Category> findAllCategories() {
        return categoryDAO.findAll();
    }

}
