package com.example.PracticaSpringBoot2023.controller;


import com.example.PracticaSpringBoot2023.Repository.ClubSportivRepository;
import com.example.PracticaSpringBoot2023.Repository.JucatoriRepository;
import com.example.PracticaSpringBoot2023.dto.ClubDto;
import com.example.PracticaSpringBoot2023.dto.ClubFormDto;
import com.example.PracticaSpringBoot2023.dto.JucatoriDto;
import com.example.PracticaSpringBoot2023.dto.JucatoriFormDto;
import com.example.PracticaSpringBoot2023.model.ClubSportiv;
import com.example.PracticaSpringBoot2023.model.Jucatori;
import com.example.PracticaSpringBoot2023.service.ClubService;
import com.example.PracticaSpringBoot2023.service.JucatoriService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
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
    private JucatoriService jucatoriService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private ClubSportivRepository clubSportivRepository;
    @Autowired
    private JucatoriRepository jucatoriRepository;


    //----------
    // Overview
    //----------
    @GetMapping("clubsportiv/{id}")
    public String showClubDetails(@PathVariable("id") int id, Model model) {


        ClubDto club = clubService.getAllClubs().get(id-1);

        List<JucatoriDto> totiJucatorii = jucatoriService.getAllJucatori();

        List<JucatoriDto> jucatoriiClubuluiRespectiv = totiJucatorii.stream()
                .filter(j -> j.getClubId() == club.getId())
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
    static int ClubId;
    static String numeClub;
    static String vechimeClub;
    static int trofeeClub;
    //Adauga:
    @GetMapping(value = "/clubsportiv/{id}/formjucatori")
    public String adaugaJucator(@PathVariable("id") int id,Model model){

        ClubDto club = clubService.getAllClubs().get(id-1);
        model.addAttribute("club",club);

        JucatoriFormDto jucatoriFormDto = new JucatoriFormDto();
        List<JucatoriDto> jucatori = jucatoriService.getAllJucatori();
           jucatoriFormDto.setClubId(club.getId());
        ClubId = club.getId();
        idStatic = jucatori.get(jucatori.size()-1).getId()+1;
        nrJucatori = club.getNrJucatori()+1;
        numeClub = club.getNume();
        vechimeClub = club.getVechime();
        trofeeClub = club.getTrofee();
        model.addAttribute("jucator", jucatoriFormDto);

        return "formjucatori";
    }

    //Edit:
    @GetMapping(value = "/jucatori/{id}/editjucatorform")
    public String editClub(@PathVariable("id") int id, Model model){

        JucatoriDto jucatoriDto = jucatoriService.getAllJucatori().get(id-1);
        ClubDto clubDto = clubService.getAllClubs().get(jucatoriDto.getClubId()-1);
        idStatic = jucatoriDto.getId();
        nrJucatori = clubDto.getNrJucatori();
        ClubId = clubDto.getId();
        numeClub = clubDto.getNume();
        vechimeClub = clubDto.getVechime();
        trofeeClub = clubDto.getTrofee();
        model.addAttribute("jucator",jucatoriDto);
        model.addAttribute("club", clubDto);

        return "formjucatori";
    }

    @PostMapping(value = "/clubsportiv/{id}")
    public String adaugaJucatorSubmit(@ModelAttribute("jucator") JucatoriFormDto jucator,ClubFormDto clubFormDto, @PathVariable("id") int id, Model model){
        jucator.setId(idStatic);
        jucator.setClubId(ClubId);
        jucatoriService.saveJucatori(jucator);

        clubFormDto.setNrJucatori(nrJucatori);
        clubFormDto.setNume(numeClub);
        clubFormDto.setVechime(vechimeClub);
        clubFormDto.setTrofee(trofeeClub);
        clubService.saveClub(clubFormDto);

        ClubId = 0;
        idStatic = 0;
        nrJucatori=0;


        return String.format("redirect:/clubsportiv/%d", id);
    }


    //--------
    // DELETE   !!! NOT WORKING !!!
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
