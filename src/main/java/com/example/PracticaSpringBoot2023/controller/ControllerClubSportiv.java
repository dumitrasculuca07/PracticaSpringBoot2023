package com.example.PracticaSpringBoot2023.controller;

import com.example.PracticaSpringBoot2023.Repository.ClubSportivRepository;
import com.example.PracticaSpringBoot2023.Repository.JucatoriRepository;
import com.example.PracticaSpringBoot2023.model.ClubSportiv;
import com.example.PracticaSpringBoot2023.model.Jucatori;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Controller
public class ControllerClubSportiv {


    @Autowired
    private ClubSportivRepository clubSportivRepository;
    @Autowired
    private JucatoriRepository jucatoriRepository;



    //----------
    // Overview
    //----------
    @GetMapping(value = "/clubsportiv")
    public String clubsportiv(Model model){

        List<ClubSportiv> cluburiSportive = clubSportivRepository.findAll();

        String text = "Cluburi Sportive";
        model.addAttribute("titlu",text);
        model.addAttribute("cluburi", cluburiSportive);

        return "clubsportiv";
    }





    //---------------
    // EDIT si ADAUGA
    //---------------

    //Adauga:
    @GetMapping(value = "/clubsportiv/formclub")
    public String getEmployeeForm(Model model) {
        model.addAttribute("club", new ClubSportiv());
        return "formclub";
    }

    //Edit:
    static int idStatic;
    @GetMapping(value = "/clubsportiv/{id}/editclubform")
    public String editClub(@PathVariable("id") int id, Model model){

        ClubSportiv club = clubSportivRepository.findById(id).get();
        idStatic = club.getId();

        model.addAttribute("club",club);

        return "formclub";
    }

    @PostMapping(value = "/clubsportiv")
    public String submitClub(@ModelAttribute("club") ClubSportiv club, Model model) {

        if(idStatic==0){
            clubSportivRepository.save(club);
        }
        else{
            club.setId(idStatic);
            clubSportivRepository.save(club);
            idStatic=0;
        }
        String text = "Cluburi Sportive";
        model.addAttribute("titlu",text);
        model.addAttribute("cluburi", clubSportivRepository.findAll());
        return "clubsportiv";
    }


    //--------
    // DELETE
    //--------

    @GetMapping(value = "/clubsportiv/{id}/stergeClub")
    public String deleteClub(@PathVariable("id") int id) {

        ClubSportiv club = clubSportivRepository.findById(id).get();
        List<Jucatori> totiJucatorii = jucatoriRepository.findAll();

        List<Jucatori> jucatoriiClubuluiRespectiv = totiJucatorii.stream()
                .filter(j -> j.getClubsportiv().getId() == club.getId())
                .toList();

        for(int i=0;i<jucatoriiClubuluiRespectiv.size();i++){
            jucatoriRepository.delete(jucatoriiClubuluiRespectiv.get(i));
        }
        clubSportivRepository.deleteById(id);

        return "redirect:/clubsportiv";
    }
}