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
    public List<Category> findUserCategories(String dni) { return userDAO.getUserCategories(dni); }
    //inserts
    public int insertUser(User user) { return userDAO.insert(user); }
    public int insertUserCategory(String dni, String category) { return userDAO.insertUserCategory(dni, category); }
    //deletes
    public int deleteUser(User user) { return userDAO.delete(user); } //probably not even necessary. oh well.
    public int deleteUserCategory(String dni, String category) { return userDAO.deleteUserCategory(dni, category); }

    //CATEGORY

    public List<Category> findAllCategories() {
        return categoryDAO.findAll();
    }

}
