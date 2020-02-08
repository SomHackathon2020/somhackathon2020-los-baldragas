package com.example.hackathonbaldragas.controller;

import com.example.hackathonbaldragas.domain.*;
import com.example.hackathonbaldragas.persistence.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("controller")
public class ControllerDAO {

    private final UserDAO userDAO;
    private final CategoryDAO categoryDAO;
    private final RequestDAO requestDAO;
    private final LinkDAO linkDAO;
    private final ActivityDAO activityDAO;
    private final MilestoneDAO milestoneDAO;


    public ControllerDAO(UserDAO u, CategoryDAO c, RequestDAO r, LinkDAO l, ActivityDAO a, MilestoneDAO m) {
        this.userDAO = u;
        this.categoryDAO = c;
        this.requestDAO = r;
        this.linkDAO = l;
        this.activityDAO = a;
        this.milestoneDAO = m;
    }

    //USER
    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    public User findUser(String userMail){
        return userDAO.findUser(userMail);
    }

    public List<User> findUserByName(String name){
        return userDAO.findByName(name);
    }

    /*public User findUserByDni(String dni) { return userDAO.findByDni(dni).get(0); }
    public List<Category> findUserCategories(String dni) { return userDAO.getUserCategories(dni); }
    public List<User> findUserByFilter(UserFilter filter) { return userDAO.findByFilter(filter); }
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
    public int deleteLink(Link link){return linkDAO.delete(link);}*/

    //ACTIVITY
    public List<Activity> findAllActivities() { return activityDAO.findAll(); }

    //MILESTONE
    public int insertMileStone(Milestone milestone){return milestoneDAO.insert(milestone);}
    public List<Milestone> findAllMilestones() { return milestoneDAO.findAll(); }
    public Milestone findMilestoneByStart(LocalDate start, String mail) { return milestoneDAO.findByStart(start, mail); }
    public Milestone findMilestoneByEnd(LocalDate end, String mail) { return milestoneDAO.findByEnd(end, mail); }

}
