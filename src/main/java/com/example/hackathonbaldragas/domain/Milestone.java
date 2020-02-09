package com.example.hackathonbaldragas.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Milestone {

    private LocalDate dateinitial;
    private LocalDate dateend;
    private float target;
    private String users_mail;
    private double progress;


    public Milestone() {}

    public Milestone(MilestoneBuilder builder) {
        dateinitial = builder.dateinitial;
        dateend = builder.dateend;
        target = builder.target;
        users_mail = builder.users_mail;
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "dateinitial=" + dateinitial +
                ", dateend=" + dateend +
                ", target=" + target +
                ", users_mail=" + users_mail +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Milestone)) return false;
        Milestone u = (Milestone) o;
        return u.dateinitial.equals(this.dateinitial) && u.dateend.equals(this.dateend) && u.users_mail.equals(this.users_mail);
    }

    public LocalDate getDateinitial() {
        return dateinitial;
    }

    public void setDateinitial(LocalDate dateinitial) {
        this.dateinitial = dateinitial;
    }

    public LocalDate getDateend() {
        return dateend;
    }

    public void setDateend(LocalDate dateend) {
        this.dateend = dateend;
    }

    public float getTarget() {
        return target;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public String getUsers_mail() {
        return users_mail;
    }

    public void setUsers_mail(String users_mail) {
        this.users_mail = users_mail;
    }

    public void setProgress(double progress) { this.progress = progress; }

    public double getProgress() { return this.progress; }

    public double getProgressPercent() { return 100*this.progress/this.target; }

    public static class MilestoneBuilder {
        private LocalDate dateinitial;
        private LocalDate dateend;
        private float target;
        private String users_mail;

        public MilestoneBuilder() {}

        public MilestoneBuilder dateinitial(LocalDate dateinitial) {
            this.dateinitial = dateinitial;
            return this;
        }

        public MilestoneBuilder dateend(LocalDate dateend) {
            this.dateend = dateend;
            return this;
        }

        public MilestoneBuilder target(float target) {
            this.target = target;
            return this;
        }

        public MilestoneBuilder users_mail(String users_mail) {
            this.users_mail = users_mail;
            return this;
        }

        public Milestone build() {
            return new Milestone(this);
        }
    }
}
