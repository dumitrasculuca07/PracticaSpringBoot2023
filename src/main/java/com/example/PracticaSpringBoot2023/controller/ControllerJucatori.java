package com.example.PracticaSpringBoot2023.controller;


import com.example.PracticaSpringBoot2023.Repository.ClubSportivRepository;
import com.example.PracticaSpringBoot2023.Repository.JucatoriRepository;
import com.example.PracticaSpringBoot2023.model.ClubSportiv;
import com.example.PracticaSpringBoot2023.model.Jucatori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ControllerJucatori{

    @Autowired
    private JucatoriRepository jucatoriRepository;
    @Autowired
    private ClubSportivRepository clubSportivRepository;

    @GetMapping("clubsportiv/{id}")
    public String showClubDetails(@PathVariable("id") int id, Model model) {

        ClubSportiv club = clubSportivRepository.findById(id).get();
        List<Jucatori> totiJucatorii = jucatoriRepository.findAll();

        List<Jucatori> jucatoriiClubuluiRespectiv = totiJucatorii.stream()
                .filter(j -> j.getClubsportiv().getId() == club.getId())
                        .toList();

        for(int i=0;i<jucatoriiClubuluiRespectiv.size();i++){
            jucatoriiClubuluiRespectiv.get(i).setId(i+1);
        }

        model.addAttribute("club", club);
        model.addAttribute("titlu", club.getNume());
        model.addAttribute("jucatori",jucatoriiClubuluiRespectiv);
        return "jucatori";
    }

    @GetMapping(value = "/clubsportiv/{id}/formjucatori")
    public String adaugaJucator(@PathVariable("id") int id,Model model){

        ClubSportiv club = clubSportivRepository.findById(id).get();
        model.addAttribute("club",club);
        model.addAttribute("jucator", new Jucatori());

        return "formjucatori";
    }

    @PostMapping(value = "/clubsportiv/{id}")
    public String adaugaJucatorSubmit(@ModelAttribute("jucator") Jucatori jucator, @PathVariable("id") int id, Model model){

        ClubSportiv club = clubSportivRepository.findById(id).get();
        model.addAttribute("club",club);

        jucator.setClubsportiv(club);
        club.setNrJucatori(club.getNrJucatori()+1);

        clubSportivRepository.save(club);
        jucatoriRepository.save(jucator);
        return String.format("redirect:/clubsportiv/%d", id);
    }

}
