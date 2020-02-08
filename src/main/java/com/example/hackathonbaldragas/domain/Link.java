package com.example.hackathonbaldragas.domain;

import java.time.LocalDate;

public class Link {
    private String user_dni_senior;
    private String user_dni_junior;
    private String assessment;
    private LocalDate date;
    private String comment;


    public Link() {}

    public Link(LinkBuilder builder) {
        user_dni_senior = builder.user_dni_senior;
        user_dni_junior = builder.user_dni_junior;
        assessment = builder.assessment;
        date = builder.date;
        comment = builder.comment;
    }

    @Override
    public String toString() {
        return "Link{" +
                "user_dni_senior=" + user_dni_senior +
                ", user_dni_junior=" + user_dni_junior +
                ", assessment=" + assessment +
                ", date=" + date +
                ", comment=" + comment +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Link)) return false;
        Link u = (Link) o;
        return u.user_dni_senior.equals(this.user_dni_senior) && u.user_dni_junior.equals(this.user_dni_junior) && u.date.equals(this.date);
    }

    public String getUser_dni_senior() {
        return user_dni_senior;
    }

    public void setUser_dni_senior(String user_dni_senior) {
        this.user_dni_senior = user_dni_senior;
    }

    public String getUser_dni_junior() {
        return user_dni_junior;
    }

    public void setUser_dni_junior(String user_dni_junior) {
        this.user_dni_junior = user_dni_junior;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static class LinkBuilder {
       private String user_dni_senior;
        private String user_dni_junior;
        private String assessment;
        private LocalDate date;
        private String comment;

        public LinkBuilder() {}

        public LinkBuilder user_dni_senior(String user_dni_senior) {
            this.user_dni_senior = user_dni_senior;
            return this;
        }

        public LinkBuilder user_dni_junior(String user_dni_junior) {
            this.user_dni_junior = user_dni_junior;
            return this;
        }

        public LinkBuilder assessment(String assessment) {
            this.assessment = assessment;
            return this;
        }

        public LinkBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public LinkBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Link build() {
            return new Link(this);
        }
    }
}