package com.example.hackathonbaldragas.domain;

import java.util.ArrayList;
import java.util.List;

public class UserFilter {

    private List<String> categoryList;
    private String availability;

    public UserFilter(){
        categoryList = new ArrayList<String>();
        availability = "matins i tardes";
    }

    public List<String> getCategoryList() { return categoryList; }

    public void setCategoryList(List<String> categoryList) { this.categoryList = categoryList; }

    public String getAvailability() { return availability; }

    public void setAvailability(String availability) { this.availability = availability; }

}
