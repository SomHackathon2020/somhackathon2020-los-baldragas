package com.example.hackathonbaldragas.WebController;


import com.example.hackathonbaldragas.controller.ControllerDAO;
import com.opencsv.CSVReader;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

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

    /*@GetMapping("/mainPage")
    public String index(Model model){
        return "mainPage";
    }*/

    @GetMapping("/showRequests")
    public String showRequests(Model model){

        model.addAttribute("requestsList", controllerDAO.findAllRequests());

        return "showRequests";
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
