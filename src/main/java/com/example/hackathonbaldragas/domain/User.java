package com.example.hackathonbaldragas.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String dni;
    private String password;
    private String name;
    private String surnames;
    private LocalDate birthday;
    private String mail;
    private String phone;
    private String address;
    private String availability;
    private boolean senior;
    private List<String> categoriesList;

    public User() {
    }

    public User(UserBuilder builder) {
        username = builder.username;
        password = builder.password;
        name = builder.name;
        mail = builder.mail;
        birthday = builder.birthday;
        phone = builder.phone;
        uservip = builder.uservip;
        rating = builder.rating;
        description = builder.description;
        minimAge = builder.minimAge;
        maximAge = builder.maximAge;
        gender = builder.gender;
        likesGender = builder.likesGender;
        interestsList = builder.interestsList;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name=" + name +
                ", mail='" + mail + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", description=" + description +
                ", gender=" + gender +
                ", likesGender=" + likesGender +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof User)) return false;
        User u = (User) o;
        return u.username.equals(this.username);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getAge(){ return Period.between(birthday, LocalDate.now()).getYears(); }

    public String getPhone() {
        return phone;
    }

    public int getUservip() {
        return uservip ? 1 : 0;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public int getMinimAge() {
        return minimAge;
    }

    public int getMaximAge() {
        return maximAge;
    }

    public int getGender() {
        return gender;
    }

    public List<Integer> getLikesGender() { return likesGender; }

    public List<String> getInterestsList() { return interestsList; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUservip(boolean uservip) {
        this.uservip=uservip;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMinimAge(int minimAge) {
        this.minimAge = minimAge;
    }

    public void setMaximAge(int maximAge) {
        this.maximAge = maximAge;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setLikesGender (List<Integer> likesGender) { this.likesGender = likesGender; }

    public void setInterestsList(List<String> interestsList) { this.interestsList = interestsList; }

    public static class UserBuilder {
        private String username;
        private String password;
        private String name;
        private String mail;
        private LocalDate birthday;
        private String phone;
        private boolean uservip;
        private double rating;
        private String description;
        private int minimAge;
        private int maximAge;
        private int gender;
        private List<Integer> likesGender;
        private List<String> interestsList;

        public UserBuilder() {
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder mail(String mail) {
            this.mail = mail;
            return this;
        }

        public UserBuilder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder uservip(boolean uservip) {
            this.uservip = uservip;
            return this;
        }

        public UserBuilder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public UserBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UserBuilder minimAge(int minimAge) {
            this.minimAge = minimAge;
            return this;
        }

        public UserBuilder maximAge(int maximAge) {
            this.maximAge = maximAge;
            return this;
        }

        public UserBuilder gender(int gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder likesGender(List<Integer> likesGender) {
            this.likesGender = likesGender;
            return this;
        }

        public UserBuilder interestsList(List<String> interestsList){
            this.interestsList = interestsList;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}