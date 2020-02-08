package com.example.hackathonbaldragas.domain;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Activity {
    private Timestamp timestampInitial;
    private Timestamp timestampEnd;
    private String content;
    private String type;
    private String usersMail;

    public Activity() {}

    public Timestamp getTimestampInitial() { return timestampInitial; }

    public void setTimestampInitial(Timestamp timestampInitial) { this.timestampInitial = timestampInitial; }

    public Timestamp getTimestampEnd() { return timestampEnd; }

    public void setTimestampEnd(Timestamp timestampEnd) { this.timestampEnd = timestampEnd; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getUsersMail() { return usersMail; }

    public void setUsersMail(String usersMail) { this.usersMail = usersMail; }

    public Activity(ActivityBuilder builder) {
        timestampInitial = builder.timestampInitial;
        timestampEnd = builder.timestampEnd;
        content = builder.content;
        type = builder.type;
        usersMail = builder.usersMail;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "timestamp_initial=" + timestampInitial +
                ", timestamp_end=" + timestampEnd +
                ", content=" + content +
                ", type=" + type +
                ", usersMail=" + usersMail +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Activity)) return false;
        Activity a = (Activity) o;
        return a.timestampInitial.equals(this.timestampInitial) && a.timestampEnd.equals(this.timestampEnd) && a.usersMail.equals(this.usersMail);
    }


    public static class ActivityBuilder {
        private Timestamp timestampInitial;
        private Timestamp timestampEnd;
        private String content;
        private String type;
        private String usersMail;

        public ActivityBuilder() {}

        public ActivityBuilder timestampInitial(Timestamp timestampInitial){
            this.timestampInitial = timestampInitial;
            return this;
        }

        public ActivityBuilder timestampEnd(Timestamp timestampEnd){
            this.timestampEnd = timestampEnd;
            return this;
        }

        public ActivityBuilder content(String content){
            this.content = content;
            return this;
        }

        public ActivityBuilder type(String type){
            this.type = type;
            return this;
        }

        public ActivityBuilder usersMail(String usersMail){
            this.usersMail = usersMail;
            return this;
        }
        public Activity build() {
            return new Activity(this);
        }
    }
}