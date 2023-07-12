package com.example.PracticaSpringBoot2023.controller;


import com.example.PracticaSpringBoot2023.model.ClubSportiv;
import com.example.PracticaSpringBoot2023.model.Jucatori;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
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
}
