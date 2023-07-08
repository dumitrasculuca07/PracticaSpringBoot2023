package com.example.PracticaSpringBoot2023.controller;

import com.example.PracticaSpringBoot2023.model.ClubSportiv;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class ControllerClubSportiv {

    @GetMapping(value = "/clubsportiv")
    public String clubsportiv(Model model){

        String text = "Cluburi Sportive";
        model.addAttribute("titlu",text);
        ClubSportiv c1 = new ClubSportiv(1,5,"Liverpool","130 ani",20);
        ClubSportiv c2 = new ClubSportiv(2,5,"Manchester City","125 ani",30);
        ClubSportiv c3 = new ClubSportiv(3,5,"Arsenal","135 ani",35);

        List<ClubSportiv> cluburiSportive = List.of(c1,c2,c3);
        model.addAttribute("cluburi",cluburiSportive);
        return "clubsportiv";
    }
}
