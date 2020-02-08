package com.example.hackathonbaldragas;

import com.example.hackathonbaldragas.controller.ControllerDAO;
import com.example.hackathonbaldragas.domain.User;
import com.example.hackathonbaldragas.domain.Category;
import com.example.hackathonbaldragas.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.LocalDate;

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
        controllerDAO.findAllCategories().forEach(System.out::println);

        /*controllerDAO.insertUser(new User.UserBuilder()
                .dni("John123").password("1234").name("John").surnames("john@john.com").birthday(LocalDate.of(1990,10,10))
                .mail("666666666").phone("9999999").address("micasa").availability("I'm the best!").senior(true).build());*/


        controllerDAO.findUserCategories("45263112A").forEach(System.out::println);



    }

}
