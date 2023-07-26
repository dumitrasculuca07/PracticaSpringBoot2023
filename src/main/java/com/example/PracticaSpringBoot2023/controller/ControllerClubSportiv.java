package com.example.PracticaSpringBoot2023.controller;

import com.example.PracticaSpringBoot2023.Repository.ClubSportivRepository;
import com.example.PracticaSpringBoot2023.Repository.JucatoriRepository;
import com.example.PracticaSpringBoot2023.dto.ClubDto;
import com.example.PracticaSpringBoot2023.dto.ClubFormDto;
import com.example.PracticaSpringBoot2023.dto.JucatoriDto;
import com.example.PracticaSpringBoot2023.model.ClubSportiv;
import com.example.PracticaSpringBoot2023.model.Jucatori;
import com.example.PracticaSpringBoot2023.service.ClubService;
import com.example.PracticaSpringBoot2023.service.JucatoriService;
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
    private JucatoriService jucatoriService;
    @Autowired
    private ClubService clubService;



    //----------
    // Overview
    //----------
    @GetMapping(value = "/clubsportiv")
    public String clubsportiv(Model model){

        List<ClubDto> cluburiSportive = clubService.getAllClubs();

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
        ClubFormDto clubFormDto = new ClubFormDto();

        model.addAttribute("club", new ClubDto());
        return "formclub";
    }

    //Edit:
    static int idStatic;
    static int nrJucatori;
    @GetMapping(value = "/clubsportiv/{id}/editclubform")
    public String editClub(@PathVariable("id") int id, Model model){

        ClubDto club = clubService.getAllClubs().get(id-1);
        idStatic = club.getId();
        nrJucatori = club.getNrJucatori();
        model.addAttribute("club",club);

        return "formclub";
    }

    @PostMapping(value = "/clubsportiv")
    public String submitClub(@ModelAttribute("club") ClubFormDto club, Model model) {

        if(idStatic==0){
            clubService.saveClub(club);
        }
        else{
            club.setId(idStatic);
            club.setNrJucatori(nrJucatori);
            clubService.saveClub(club);
            nrJucatori=0;
            idStatic=0;
        }

        return "redirect:/clubsportiv";
    }


    //--------
    // DELETE    !!! NOT WORKING !!! trimite pe link-ul gresit, daca pui manual in url id-ul corect, merge
    //--------

    @GetMapping(value = "/clubsportiv/{id}/stergeClub")
    public String deleteClub(@PathVariable("id") int id) {

        ClubDto club = clubService.getAllClubs().get(id - 1);

        List<JucatoriDto> totiJucatorii = jucatoriService.getAllJucatori();

        List<JucatoriDto> jucatoriiClubuluiRespectiv = totiJucatorii.stream()
                .filter(j -> j.getClubId() == club.getId())
                .toList();

        for(int i=0;i<jucatoriiClubuluiRespectiv.size();i++){
            jucatoriService.deleteJucatori(jucatoriiClubuluiRespectiv.get(i).getId());
        }

        clubService.deleteClub(club.getId());

        return "redirect:/clubsportiv";
    }
}