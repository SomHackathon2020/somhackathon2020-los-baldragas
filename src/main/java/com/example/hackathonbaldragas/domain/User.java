package com.example.hackathonbaldragas.domain;

import java.time.LocalDate;
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

    public User() {}

    public User(UserBuilder builder) {
        dni = builder.dni;
        password = builder.password;
        name = builder.name;
        surnames = builder.surnames;
        birthday = builder.birthday;
        mail = builder.mail;
        phone = builder.phone;
        address = builder.address;
        availability = builder.availability;
        senior = builder.senior;
        categoriesList = builder.categoriesList;
    }

    @Override
    public String toString() {
        return "User{" +
                "dni='" + dni +
                ", name=" + name +
                ", surnames=" + surnames +
                ", birthday=" + birthday +
                ", mail='" + mail +
                ", phone='" + phone +
                ", address=" + address +
                ", availability=" + availability +
                ", senior=" + senior +
                ", categoriesList=" + categoriesList +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof User)) return false;
        User u = (User) o;
        return u.dni.equals(this.dni);
    }

    public String getDni() {
        return dni;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return phone;
    }

    public String getAvailability() { return availability; }

    public boolean getSenior() { return senior; }

    public List<String> getCategoriesList() { return categoriesList; }

    public void setDni(String dni) { this.dni = dni; }

    public void setPassword(String password) { this.password = password; }

    public void setName(String name) { this.name = name; }

    public void setSurnames(String surnames) { this.surnames = surnames; }

    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public void setMail(String mail) { this.mail = mail; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setAddress(String address) { this.address = address; }

    public void setAvailability(String availability) { this.availability = availability; }

    public void setSenior(boolean senior) { this.senior = senior; }

    public void setCategoriesList(List<String> categoriesList) { this.categoriesList = categoriesList; }

    public static class UserBuilder {
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

        public UserBuilder() {}

        public UserBuilder dni(String dni) {
            this.dni = dni;
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

        public UserBuilder mail(String mail) {
            this.mail = mail;
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

        public UserBuilder availability(String availability) {
            this.availability = availability;
            return this;
        }

        public UserBuilder senior(boolean senior) {
            this.senior = senior;
            return this;
        }

        public UserBuilder categoriesList(List<String> categoriesList) {
            this.categoriesList = categoriesList;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}