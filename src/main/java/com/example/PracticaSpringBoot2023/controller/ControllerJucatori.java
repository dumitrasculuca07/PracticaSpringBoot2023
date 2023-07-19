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


    //----------
    // Overview
    //----------
    @GetMapping("clubsportiv/{id}")
    public String showClubDetails(@PathVariable("id") int id, Model model) {

        ClubSportiv club = clubSportivRepository.findById(id).get();
        List<Jucatori> totiJucatorii = jucatoriRepository.findAll();

        List<Jucatori> jucatoriiClubuluiRespectiv = totiJucatorii.stream()
                .filter(j -> j.getClubsportiv().getId() == club.getId())
                        .toList();


        model.addAttribute("club", club);
        model.addAttribute("titlu", club.getNume());
        model.addAttribute("jucatori",jucatoriiClubuluiRespectiv);
        return "jucatori";
    }


    //---------------
    // EDIT si ADAUGA
    //---------------
    static int idStatic;
    static int nrJucatori;
    //Adauga:
    @GetMapping(value = "/clubsportiv/{id}/formjucatori")
    public String adaugaJucator(@PathVariable("id") int id,Model model){

        ClubSportiv club = clubSportivRepository.findById(id).get();
        model.addAttribute("club",club);

        List<Jucatori> jucatori = jucatoriRepository.findAll();

        Jucatori jucator = new Jucatori();  jucator.setClubsportiv(club);

        idStatic = jucatori.get(jucatori.size()-1).getId()+1;
        nrJucatori = club.getNrJucatori()+1;
        model.addAttribute("jucator", jucator);

        return "formjucatori";
    }

    //Edit:
    @GetMapping(value = "/jucatori/{id}/editjucatorform")
    public String editClub(@PathVariable("id") int id, Model model){

        Jucatori jucator = jucatoriRepository.findById(id).get();
        idStatic = jucator.getId();
        nrJucatori = jucator.getClubsportiv().getNrJucatori();
        model.addAttribute("jucator",jucator);
        model.addAttribute("club",jucator.getClubsportiv());

        return "formjucatori";
    }

    @PostMapping(value = "/clubsportiv/{id}")
    public String adaugaJucatorSubmit(@ModelAttribute("jucator") Jucatori jucator, @PathVariable("id") int id, Model model){


        ClubSportiv club = clubSportivRepository.findById(id).get();

        jucator.setId(idStatic);
        jucator.setClubsportiv(club);
        jucatoriRepository.save(jucator);
        idStatic=0;
        if(nrJucatori!=0){
            club.setNrJucatori(nrJucatori);
            nrJucatori=0;
        }


        clubSportivRepository.save(club);

        return String.format("redirect:/clubsportiv/%d", id);
    }


    //--------
    // DELETE
    //--------
    @GetMapping(value = "/jucatori/{id}/stergeJucator")
    public String stergeJucator(@PathVariable("id") int id){

        Jucatori jucator = jucatoriRepository.findById(id).get();
        int idClub = jucator.getClubsportiv().getId();
        jucator.getClubsportiv().setNrJucatori(jucator.getClubsportiv().getNrJucatori()-1);

        clubSportivRepository.save(jucator.getClubsportiv());
        jucatoriRepository.delete(jucator);

        return String.format("redirect:/clubsportiv/%d", idClub);
    }

}
