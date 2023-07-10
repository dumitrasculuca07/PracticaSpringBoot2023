package com.example.PracticaSpringBoot2023.controller;

import com.example.PracticaSpringBoot2023.model.ClubSportiv;
import com.example.PracticaSpringBoot2023.model.Jucatori;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class ControllerClubSportiv {

    public static List<ClubSportiv> getCluburi(){

        List<Jucatori> listaJucatori1 = new ArrayList<>();
        Jucatori j11 = new Jucatori(1,"Luca",21,3);
        Jucatori j12 = new Jucatori(2,"Marius",30,5);
        Jucatori j13 = new Jucatori(3,"Octavian",21,2);
        listaJucatori1 = List.of(j11,j12,j13); // Liverpool

        List<Jucatori> listaJucatori2 = new ArrayList<>();
        Jucatori j21 = new Jucatori(1,"Ederson",28,5);
        Jucatori j22 = new Jucatori(2,"Ake",24,2);
        Jucatori j23 = new Jucatori(3,"Foden",23,4);
        listaJucatori2 = List.of(j21,j22,j23); // ManCity

        List<Jucatori> listaJucatori3 = new ArrayList<>();
        Jucatori j31 = new Jucatori(1,"Saliba",22,1);
        Jucatori j32 = new Jucatori(2,"Havertz",24,1);
        Jucatori j33 = new Jucatori(3,"Jesus",26,2);
        listaJucatori3 = List.of(j31,j32,j33); // Arsenal

        ClubSportiv c1 = new ClubSportiv(1,5,"Liverpool","130 ani",50,listaJucatori1);
        ClubSportiv c2 = new ClubSportiv(2,5,"Manchester City","125 ani",30,listaJucatori2);
        ClubSportiv c3 = new ClubSportiv(3,5,"Arsenal","135 ani",33,listaJucatori3);

        return List.of(c1,c2,c3);
    }

    @GetMapping(value = "/clubsportiv")
    public String clubsportiv(Model model){

        String text = "Cluburi Sportive";
        model.addAttribute("titlu",text);
        model.addAttribute("cluburi", getCluburi());
        return "clubsportiv";
    }

}
