package com.example.hackathonbaldragas.domain;

import java.time.LocalDate;
import java.util.List;

public class User {
    private String mail;
    private String password;
    private String name;
    private String surnames;
    private LocalDate birthday;
    private String phone;
    private String address;
    private String type;
    private double height;
    private double weight;


    public User() {}

    public User(UserBuilder builder) {
        mail = builder.mail;
        password = builder.password;
        name = builder.name;
        surnames = builder.surnames;
        birthday = builder.birthday;
        phone = builder.phone;
        address = builder.address;
        type = builder.type;
        height = builder.height;
        weight = builder.weight;
    }

    @Override
    public String toString() {
        return "User{" +
                "mail='" + mail +
                ", name=" + name +
                ", surnames=" + surnames +
                ", birthday=" + birthday +
                ", phone='" + phone +
                ", address=" + address +
                ", type=" + type +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof User)) return false;
        User u = (User) o;
        return u.mail.equals(this.mail);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static class UserBuilder {
        private String mail;
        private String password;
        private String name;
        private String surnames;
        private LocalDate birthday;
        private String phone;
        private String address;
        private String type;
        private double height;
        private double weight;

        public UserBuilder() {}

        public UserBuilder mail(String mail) {
            this.mail = mail;
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

        public UserBuilder surnames(String surnames) {
            this.surnames = surnames;
            return this;
        }

        public UserBuilder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserBuilder type(String type) {
            this.type = type;
            return this;
        }


        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }


        public UserBuilder height(double height) {
            this.height = height;
            return this;
        }

        public UserBuilder weight(double weight) {
            this.weight = weight;
            return this;
        }


        public User build() {
            return new User(this);
        }


    }
}