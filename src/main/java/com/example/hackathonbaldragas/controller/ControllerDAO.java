package com.example.hackathonbaldragas.controller;

import com.example.hackathonbaldragas.domain.*;
import com.example.hackathonbaldragas.persistence.*;
import org.apache.tomcat.jni.Local;
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

    //ACTIVITY
    public List<Activity> findAllActivities() { return activityDAO.findAll(); }
    public List<Activity> findActivitiesByUser(String mail) { return activityDAO.findByUser(mail); }
    public List<Activity> getActivityBetweenDates(String mail, LocalDate datea, LocalDate dateb) { return activityDAO.findBetweenDates(mail, datea, dateb); }

    //MILESTONE
    public int insertMileStone(Milestone milestone) { return milestoneDAO.insert(milestone); }
    public List<Milestone> findAllMilestones() { return milestoneDAO.findAll(); }
    public List<Milestone> findMilestonesByUser(String userMail) {
        List<Milestone> result = milestoneDAO.findMilestonesByUser(userMail);
        for(Milestone mile : result) {
            double count = 0.0;
            for(Activity act: getActivityBetweenDates(userMail, mile.getInitialDate(), mile.getFinalDate())) count += act.getKcal();
            mile.setProgress(count);
        }
        return result;
    }
    public Milestone findMilestoneByStart(LocalDate start, String mail) { return milestoneDAO.findByStart(start, mail); }
    public Milestone findMilestoneByEnd(LocalDate end, String mail) { return milestoneDAO.findByEnd(end, mail); }

}
