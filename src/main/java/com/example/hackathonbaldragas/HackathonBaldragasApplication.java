package com.example.hackathonbaldragas;

import com.example.hackathonbaldragas.controller.ControllerDAO;
import com.example.hackathonbaldragas.domain.Category;
import com.example.hackathonbaldragas.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

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
    }
}
