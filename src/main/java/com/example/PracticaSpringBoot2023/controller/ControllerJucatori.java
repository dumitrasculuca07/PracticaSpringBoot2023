package com.example.PracticaSpringBoot2023.controller;


import com.example.PracticaSpringBoot2023.model.ClubSportiv;
import com.example.PracticaSpringBoot2023.model.Jucatori;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class ControllerJucatori{
    @GetMapping("clubsportiv/{id}")
    public String showClubDetails(@PathVariable("id") int id, Model model) {
        ClubSportiv club = ControllerClubSportiv.Cluburi.get(id - 1);

        model.addAttribute("club", club);
        model.addAttribute("titlu", club.getNume());
        model.addAttribute("jucatori", club.getJucatori());
        return "jucatori";
    }

    @GetMapping(value = "/clubsportiv/{id}/formjucatori")
    public String adaugaJucator(@PathVariable("id") int id,Model model){

        ClubSportiv club = ControllerClubSportiv.Cluburi.get(id - 1);
        model.addAttribute("club",club);
        model.addAttribute("jucator", new Jucatori(club.getNrJucatori()+1,null,0,0));

        return "formjucatori";
    }

    @PostMapping(value = "/clubsportiv/{id}")
    public String adaugaJucatorSubmit(@ModelAttribute("jucator") Jucatori jucator, @PathVariable("id") int id, Model model){

        ClubSportiv club = ControllerClubSportiv.Cluburi.get(id - 1);
        model.addAttribute("club",club);

        if(club.getJucatori()!=null){
            club.setNrJucatori(club.getNrJucatori()+1);
            club.getJucatori().add(jucator);
        }
        else{
            club.setNrJucatori(club.getNrJucatori()+1);
            club.jucatori = new ArrayList<>(Arrays.asList(jucator));
        }

        return String.format("redirect:/clubsportiv/%d", id);
    }
}
