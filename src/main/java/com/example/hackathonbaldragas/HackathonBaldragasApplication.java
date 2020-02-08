package com.example.hackathonbaldragas;

import com.example.hackathonbaldragas.controller.ControllerDAO;
import com.example.hackathonbaldragas.domain.Link;
import com.example.hackathonbaldragas.domain.User;
import com.example.hackathonbaldragas.domain.Category;
import com.example.hackathonbaldragas.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.LocalDate;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        /*controllerDAO.findAllUsers().forEach(System.out::println);
        controllerDAO.findAllCategories().forEach(System.out::println);

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
