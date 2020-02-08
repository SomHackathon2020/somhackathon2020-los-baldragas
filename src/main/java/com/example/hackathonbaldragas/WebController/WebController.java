package com.example.hackathonbaldragas.WebController;


import com.example.hackathonbaldragas.controller.ControllerDAO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebController {

    private final ControllerDAO controllerDAO;

    public WebController(ControllerDAO controllerDAO) {
        this.controllerDAO = controllerDAO;
    }

    /*@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }*/

    @GetMapping("/mainPage")
    public String index(Model model){
        return "mainPage";
    }

    @GetMapping("/showRequests")
    public String showRequests(Model model){

        model.addAttribute("requestsList", controllerDAO.findAllRequests());

        return "showRequests";
    }

    @GetMapping("showRequest/{idRequest}")
    public String showRequest(Model model, @PathVariable int idRequest){

        model.addAttribute("creator", controllerDAO.getRequestCreator(idRequest));
        model.addAttribute("request", controllerDAO.findRequest(idRequest));

        return "request";
    }
}
