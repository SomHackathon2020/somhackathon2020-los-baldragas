package com.example.hackathonbaldragas.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Milestone {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate finalDate;
    private float target;
    private String usersMail;
    private double progress;


    public Milestone() {}

    public Milestone(MilestoneBuilder builder) {
        initialDate = builder.initialDate;
        finalDate = builder.finalDate;
        target = builder.target;
        usersMail = builder.usersMail;
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "initialDate=" + initialDate +
                ", finalDate=" + finalDate +
                ", target=" + target +
                ", usersMail=" + usersMail +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Milestone)) return false;
        Milestone u = (Milestone) o;
        return u.initialDate.equals(this.initialDate) && u.finalDate.equals(this.finalDate) && u.usersMail.equals(this.usersMail);
    }

    public LocalDate getInitialDate() { return initialDate;  }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public float getTarget() {
        return target;
    }

    public String getUsersMail() {
        return usersMail;
    }

    public void setInitialDate(LocalDate initialDate) { this.initialDate = initialDate; }

    public void setFinalDate(LocalDate finalDate) { this.finalDate = finalDate; }

    public void setTarget(float target) { this.target = target; }

    public void setUsersMail(String usersMail) { this.usersMail = usersMail; }

    public void setProgress(double progress) { this.progress = progress; }

    public double getProgress() { return this.progress; }

    public double getProgressPercent() { return 100*this.progress/this.target; }

    public static class MilestoneBuilder {
        private LocalDate initialDate;
        private LocalDate finalDate;
        private float target;
        private String usersMail;

        public MilestoneBuilder() {}

        public MilestoneBuilder initialDate(LocalDate initialDate) {
            this.initialDate = initialDate;
            return this;
        }

        public MilestoneBuilder finalDate(LocalDate finalDate) {
            this.finalDate = finalDate;
            return this;
        }

        public MilestoneBuilder target(float target) {
            this.target = target;
            return this;
        }

        public MilestoneBuilder usersMail(String usersMail) {
            this.usersMail = usersMail;
            return this;
        }

        public Milestone build() {
            return new Milestone(this);
        }
    }
}
