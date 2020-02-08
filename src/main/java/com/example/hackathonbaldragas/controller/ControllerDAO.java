package com.example.hackathonbaldragas.controller;

import com.example.hackathonbaldragas.domain.Category;
import com.example.hackathonbaldragas.domain.Link;
import com.example.hackathonbaldragas.domain.Request;
import com.example.hackathonbaldragas.domain.User;
import com.example.hackathonbaldragas.persistence.CategoryDAO;
import com.example.hackathonbaldragas.persistence.LinkDAO;
import com.example.hackathonbaldragas.persistence.RequestDAO;
import com.example.hackathonbaldragas.persistence.UserDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("controller")
public class ControllerDAO {

    private final UserDAO userDAO;
    private final CategoryDAO categoryDAO;
    private final RequestDAO requestDAO;
    private final LinkDAO linkDAO;


    public ControllerDAO(UserDAO u, CategoryDAO c, RequestDAO r, LinkDAO l) {
        this.userDAO = u;
        this.categoryDAO = c;
        this.requestDAO = r;
        this.linkDAO = l;
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

    public int updateUser(User user) { return userDAO.update(user); }



    //REQUEST

    public int insertRequest(Request request){
        return requestDAO.insert(request);
    }

    public List<Request> findAllRequests() {
        return requestDAO.findAll();
    }

    public Request findRequest(int id) {
        return requestDAO.find(id);
    }

    public String getRequestCreator(int id){
        return requestDAO.getUser(id);
    }


    //LINK

    public List<Link> findAllLinks() {
        return linkDAO.findAll();
    }
    public int insertLink(Link link){return linkDAO.insert(link);}
    public int updateLink(Link link){return linkDAO.update(link);}
    public int insertNewLink(Link link){return linkDAO.insertNew(link);}
    public int deleteLink(Link link){return linkDAO.delete(link);}

}
