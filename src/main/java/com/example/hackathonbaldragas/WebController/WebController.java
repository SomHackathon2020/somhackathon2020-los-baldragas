package com.example.hackathonbaldragas.WebController;


import com.example.hackathonbaldragas.controller.ControllerDAO;
import com.example.hackathonbaldragas.domain.User;
import com.example.hackathonbaldragas.domain.UserFilter;
import com.example.hackathonbaldragas.domain.Request;
import com.opencsv.CSVReader;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    private final ControllerDAO controllerDAO;
    private final String CAPS_CSV_FILE_PATH = "./CAPS.csv";
    private final String FARMACIES_CSV_FILE_PATH = "./Farmacies.csv";

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

    /*@GetMapping("showRequests")
    public String showRequests(Model model){

        model.addAttribute("requestsList", controllerDAO.findAllRequests());

        return "showRequests";
    }
    //User Profile [ADMIN AND PRINCIPAL]
    @GetMapping("/user/{tempUser}")
    public String userProfile(@PathVariable String tempUser, Model model){
        model.addAttribute("user", controllerDAO.findUserByDni(tempUser));
        return "userProfile";
    }

    //User List (for requests) [ADMIN]
    @GetMapping("/users")
    public String userList(Model model){
        if(!model.containsAttribute("filter")) model.addAttribute("filter", new UserFilter());
        model.addAttribute("users", controllerDAO.findUserByFilter((UserFilter) model.getAttribute("filter")));
        model.addAttribute("categories", controllerDAO.findAllCategories());
        return "userList";
    }
    @PostMapping("/users")
    public String userListPost(UserFilter filter, Model model){
        model.addAttribute("filter",filter);
        model.addAttribute("users", controllerDAO.findUserByFilter((UserFilter) model.getAttribute("filter")));
        model.addAttribute( "categories", controllerDAO.findAllCategories());
        return "userList";
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
    }*/

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

    @GetMapping("milestones/{user}")
    public String milestones(@PathVariable String user, Model model) {
        model.addAttribute("milestones",controllerDAO.findMilestonesByUser(user));
        return "milestones";
    }

    @GetMapping("/map/CAPS")
    public String mapCaps(Model model){
        List<String> listName = new ArrayList<String>();
        List<String> listAddress = new ArrayList<String>();
        List<String> listLat = new ArrayList<String>();
        List<String> listLong = new ArrayList<String>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(CAPS_CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            // Reading Records One by One in a String array
            String[] nextRecord;
            int cont = 0;
            while ((nextRecord = csvReader.readNext()) != null) {
                /*System.out.println("Name : " + nextRecord[0]);
                System.out.println("Lat : " + nextRecord[6]);
                System.out.println("Long : " + nextRecord[7]);
                System.out.println("==========================");*/
                if(cont>0){
                    listName.add(nextRecord[0]);
                    listAddress.add(nextRecord[1]);
                    listLat.add(nextRecord[6]);
                    listLong.add(nextRecord[7]);
                }
                cont++;
            }
        }catch (Exception e){
            //Mostrar una página de que no se ha podido cargar la web
        }
        model.addAttribute("listName", listName);
        model.addAttribute("listAddress", listAddress);
        model.addAttribute("listLat", listLat);
        model.addAttribute("listLong", listLong);

        return "mapCAPS";
    }

    @GetMapping("/map/Farmacies")
    public String mapFarmacies(Model model){
        List<String> listName = new ArrayList<String>();
        List<String> listAddress = new ArrayList<String>();
        List<String> listLat = new ArrayList<String>();
        List<String> listLong = new ArrayList<String>();
        List<String> listDesc = new ArrayList<String>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(FARMACIES_CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            // Reading Records One by One in a String array
            String[] nextRecord;
            int cont = 0;
            while ((nextRecord = csvReader.readNext()) != null) {
                /*System.out.println("Name : " + nextRecord[0]);
                System.out.println("Lat : " + nextRecord[6]);
                System.out.println("Long : " + nextRecord[7]);
                System.out.println("==========================");*/
                if(cont>0){
                    listName.add(nextRecord[0]);
                    listAddress.add(nextRecord[1]);
                    listDesc.add(nextRecord[4]);
                    listLat.add(nextRecord[6]);
                    listLong.add(nextRecord[7]);
                }
                cont++;
            }
        }catch (Exception e){
            //Mostrar una página de que no se ha podido cargar la web
        }
        model.addAttribute("listName", listName);
        model.addAttribute("listAddress", listAddress);
        model.addAttribute("listDesc", listDesc);
        model.addAttribute("listLat", listLat);
        model.addAttribute("listLong", listLong);

        return "mapFarmacies";
    }
}
