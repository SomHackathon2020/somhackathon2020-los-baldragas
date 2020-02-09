package com.example.hackathonbaldragas.WebController;


import com.example.hackathonbaldragas.controller.ControllerDAO;
import com.example.hackathonbaldragas.domain.Activity;
import com.example.hackathonbaldragas.domain.User;
import com.example.hackathonbaldragas.domain.Milestone;
import com.example.hackathonbaldragas.domain.User;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("mainPage")
    public String index(Model model){
        return "mainPage";
    }

    @GetMapping("/users")
    public String userList(Model model){
        User filter = new User();
        if(!model.containsAttribute("filter")){
            filter.setName("");
            model.addAttribute("filter",filter);
        }
        System.out.println(filter.getName());
        model.addAttribute("userList", controllerDAO.findUserByName(((User)model.getAttribute("filter")).getName()));
        return "searchUsers";
    }
    @PostMapping("/users")
    public String userListPost(User filter, Model model){
        model.addAttribute("filter",filter);
        model.addAttribute("userList", controllerDAO.findUserByName(((User) model.getAttribute("filter")).getName()));
        return "searchUsers";
    }

    @GetMapping("/users/{userMail}")
    public String userProfile(@PathVariable String userMail, Model model){
        model.addAttribute("user", controllerDAO.findUser(userMail));

        return "userProfile";
    }

    @GetMapping("createMilestone/{userMail}")
    public String createMilestone(Model model, @PathVariable String userMail){

        Milestone milestone = new Milestone();
        model.addAttribute("milestone", milestone);

        return "MilestoneForm";
    }

    @PostMapping("createMilestone/{userMail}")
    public String post_createMilestone(@PathVariable String userMail, Model model, @ModelAttribute Milestone milestone){
        try{
            milestone.setUsers_mail(userMail);
            controllerDAO.insertMileStone(milestone);
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/mileStoneCreation/" + false;
        }

        return "redirect:/mileStoneCreation/" + true;
    }

    @GetMapping("mileStoneCreation/{successful}")
    public String requestCreation(@PathVariable boolean successful, Model model){
        String message = successful ? "La teva petició ha sigut creada correctament" : "Hi ha hagut un error i la teva " +
                "petició no s'ha pogut processar";

        model.addAttribute("message", message);

        return "mileStonePostCreation";
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

    @GetMapping("milestones/{user}")
    public String milestones(@PathVariable String user, Model model) {
        model.addAttribute("milestones",controllerDAO.findMilestonesByUser(user));
        return "milestones";
    }

    @GetMapping("/activity/{mail}")
    public String activity(@PathVariable String mail, Model model){
        List<Activity> listActivity = controllerDAO.findActivitiesByUser(mail);
        List<Milestone> listMilestone = controllerDAO.findMilestonesByUser(mail);
        model.addAttribute("activities", listActivity);
        model.addAttribute("milestones", listMilestone);
        return "activityboot";
    }


    @GetMapping("/activities")
    public String activities(Model model){
        List<Activity> list = controllerDAO.findAllActivities();
        model.addAttribute("activities", list);
        return "activitiesboot";
    }

    @GetMapping("/map/Parcs")
    public String mapParcs(Model model){
        return "mapParcs";
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
