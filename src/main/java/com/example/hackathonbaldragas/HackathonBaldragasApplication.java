package com.example.hackathonbaldragas;

import com.example.hackathonbaldragas.controller.ControllerDAO;
import com.example.hackathonbaldragas.domain.*;
import com.example.hackathonbaldragas.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;import org.json.*;

import static java.lang.Double.parseDouble;

@SpringBootApplication
public class HackathonBaldragasApplication implements CommandLineRunner {

    @Autowired
    ControllerDAO controllerDAO;

    public static void main(String[] args) {
        SpringApplication.run(HackathonBaldragasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("READY");

        controllerDAO.findAllUsers().forEach(System.out::println);

        List<Activity> activityList;
        activityList = controllerDAO.findAllActivities();

        for (Activity activity : activityList){
            JSONArray jsonArray = new JSONArray(activity.getContent());
            if(activity.getType().equals("rpm")){
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
                //timestampList.forEach(System.out::println);
                int vueltas =(timestampList.size()-1);
                System.out.println("Vueltas = "+vueltas);

                double deltaTime=((timestampList.get(timestampList.size()-1).getTime()-timestampList.get(0).getTime())/1000); //segundos
                System.out.println(deltaTime);
                System.out.println("RPM = "+vueltas/(deltaTime/60));

                System.out.println(((vueltas/(deltaTime)*85)/100)); //kcal
            }
            else{
                List<Timestamp> timestampList = new ArrayList<Timestamp>();
                List<Double> latList = new ArrayList<Double>();
                List<Double> longList = new ArrayList<Double>();
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    try {
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
                        Date date = formatter.parse(jsonObject.getString("Timestamp"));
                        timestampList.add(new Timestamp(date.getTime()));

                    } catch (ParseException e) {
                        System.out.println("Exception :" + e);
                    }

                    latList.add(parseDouble(jsonObject.getString("Lat").replace(",",".")));
                    longList.add(parseDouble(jsonObject.getString("Long").replace(",",".")));
                }
                final int R = 6371; // Radius of the earth
                double lat1 = latList.get(0);
                double lon1 = longList.get(0);
                double lat2 = latList.get(timestampList.size()-1);
                double lon2 = longList.get(timestampList.size()-1);
                double latDistance = Math.toRadians(lat2 - lat1);
                double lonDistance = Math.toRadians(lon2 - lon1);
                double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                double distance = R * c * 1000; // convert to meters



                distance = Math.pow(distance, 2) + Math.pow(2, 2);

                double distancia = Math.sqrt(distance);
                System.out.println(distancia);
                double deltaTime=((timestampList.get(timestampList.size()-1).getTime()-timestampList.get(0).getTime())/1000); //segundos
                System.out.println(distancia/deltaTime);
                System.out.println((distancia/deltaTime)*80*(distancia/1000)); //kcal


            }
        }

        /*controllerDAO.findAllCategories().forEach(System.out::println);

        controllerDAO.findAllRequests().forEach(System.out::println);

        controllerDAO.insertUser(new User.UserBuilder()
                .dni("John123").password("1234").name("John").surnames("john@john.com").birthday(LocalDate.of(1990,10,10))
                .mail("666666666").phone("9999999").address("micasa").availability("matins i tardes").senior(true).build());
        controllerDAO.insertUserCategory("John123", "Deporte");

        controllerDAO.findAllUsers().forEach(System.out::println);
        controllerDAO.findUserCategories("45263112A").forEach(System.out::println);

        controllerDAO.findAllLinks().forEach(System.out::println);

        controllerDAO.insertNewLink(new Link.LinkBuilder().user_dni_senior("AAAAAAA").user_dni_junior("BBBBBB").date(LocalDate.of(1990,10,10)).build());

        controllerDAO.findAllLinks().forEach(System.out::println);*/


    }

}
