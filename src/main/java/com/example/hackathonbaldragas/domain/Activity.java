package com.example.hackathonbaldragas.domain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Double.parseDouble;

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

    public double getKcal(){
        try{
            JSONArray jsonArray = new JSONArray(this.content);
            if(this.type.equals("rpm")){
                List<Timestamp> timestampList = new ArrayList<Timestamp>();
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    try {
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
                        Date date = formatter.parse(jsonObject.getString("Timestamp"));
                        timestampList.add(new Timestamp(date.getTime()));

                    } catch (ParseException e) {
                        System.out.println("Exception :" + e);
                    }
                }
                int vueltas =(timestampList.size()-1);

                double deltaTime=((timestampList.get(timestampList.size()-1).getTime()-timestampList.get(0).getTime())/1000); //segundos

                return(((vueltas/(deltaTime)*85)/100)); //kcal
            }
            else {
                List<Timestamp> timestampList = new ArrayList<Timestamp>();
                List<Double> latList = new ArrayList<Double>();
                List<Double> longList = new ArrayList<Double>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    try {
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
                        Date date = formatter.parse(jsonObject.getString("Timestamp"));
                        timestampList.add(new Timestamp(date.getTime()));

                    } catch (ParseException e) {
                        System.out.println("Exception :" + e);
                    }

                    latList.add(parseDouble(jsonObject.getString("Lat").replace(",", ".")));
                    longList.add(parseDouble(jsonObject.getString("Long").replace(",", ".")));
                }
                final int R = 6371; // Radius of the earth
                double lat1 = latList.get(0);
                double lon1 = longList.get(0);
                double lat2 = latList.get(timestampList.size() - 1);
                double lon2 = longList.get(timestampList.size() - 1);
                double latDistance = Math.toRadians(lat2 - lat1);
                double lonDistance = Math.toRadians(lon2 - lon1);
                double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                double distance = R * c * 1000; // convert to meters


                distance = Math.pow(distance, 2) + Math.pow(2, 2);

                double distancia = Math.sqrt(distance);

                double deltaTime = ((timestampList.get(timestampList.size() - 1).getTime() - timestampList.get(0).getTime()) / 1000); //segundos

                return((distancia / deltaTime) * 80 * (distancia / 1000)); //kcal
            }
        }
        catch(Exception e){

        }
        return 0;
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