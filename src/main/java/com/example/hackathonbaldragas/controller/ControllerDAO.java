package com.example.hackathonbaldragas.controller;

import com.example.hackathonbaldragas.domain.Category;
import com.example.hackathonbaldragas.domain.Request;
import com.example.hackathonbaldragas.domain.User;
import com.example.hackathonbaldragas.persistence.CategoryDAO;
import com.example.hackathonbaldragas.persistence.RequestDAO;
import com.example.hackathonbaldragas.persistence.UserDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("controller")
public class ControllerDAO {

    private final UserDAO userDAO;
    private final CategoryDAO categoryDAO;
    private final RequestDAO requestDAO;


    public ControllerDAO(UserDAO u, CategoryDAO c, RequestDAO r) {
        this.userDAO = u;
        this.categoryDAO = c;
        this.requestDAO = r;
    }

    //USER

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }


    //CATEGORY

    public List<Category> findAllCategories() {
        return categoryDAO.findAll();
    }



    //REQUEST

    public List<Request> findAllRequests() {
        return requestDAO.findAll();
    }

}
