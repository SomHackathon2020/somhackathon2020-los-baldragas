package com.example.hackathonbaldragas.WebController;


import com.example.hackathonbaldragas.controller.ControllerDAO;
import com.example.hackathonbaldragas.domain.Request;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("mainPage")
    public String index(Model model){
        return "mainPage";
    }

    @GetMapping("showRequests")
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

    @GetMapping("createRequest")
    public String createRequest(Model model){

        Request request = new Request();

        model.addAttribute("creator", "33363112W");
        model.addAttribute("request", request);

        return "requestForm";
    }

    @PostMapping("createRequest")
    public String post_createRequest(Model model, @ModelAttribute String creator, @ModelAttribute Request request){
        try{

            request.setState("open");
            request.setUser_dni("33363112W");

            controllerDAO.insertRequest(request);
            System.out.print(request);
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/requestCreation/" + false;
        }

        return "redirect:/requestCreation/" + true;
    }

    @GetMapping("requestCreation/{successful}")
    public String requestCreation(@PathVariable boolean successful, Model model){
        String message = successful ? "La teva petició ha sigut creada correctament" : "Hi ha hagut un error i la teva " +
                "petició no s'ha pogut processar";

        model.addAttribute("message", message);

        return "requestPostCreation";
    }

    @GetMapping("/sudoku/facil")
    public String sudokuWidget(){
        return "sudokuWidgetEasy";
    }

    @GetMapping("/sudoku/mig")
    public String sudokuWidget2(){
        return "sudokuWidgetMedium";
    }

    @GetMapping("/sudoku/dificil")
    public String sudokuWidget3(){
        return "sudokuWidgetHard";
    }

    @GetMapping("/Receptes")
    public String recetas(){
        return "recetas";
    }
    @GetMapping("/Telefons")
    public String telefons(){
        return "telefons";
    }
}
