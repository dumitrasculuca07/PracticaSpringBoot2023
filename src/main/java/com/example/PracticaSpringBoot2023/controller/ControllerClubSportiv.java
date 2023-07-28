package com.example.PracticaSpringBoot2023.controller;
import com.example.PracticaSpringBoot2023.dto.ClubDto;
import com.example.PracticaSpringBoot2023.dto.JucatoriDto;
import com.example.PracticaSpringBoot2023.service.ClubService;
import com.example.PracticaSpringBoot2023.service.JucatoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

        model.addAttribute("club", new ClubDto());
        return "formclub";
    }

    //Edit:
    @GetMapping(value = "/clubsportiv/{id}/editclubform")
    public String editClub(@PathVariable("id") int id, Model model){

        ClubDto club = clubService.findClubById(id);
        model.addAttribute("club",club);

        return "formclub";
    }

    @PostMapping(value = "/clubsportiv")
    public String submitClub(@ModelAttribute("club") ClubDto club, Model model) {

        clubService.saveClub(club);
        return "redirect:/clubsportiv";
    }

    //--------
    // DELETE
    //--------

    @GetMapping(value = "/clubsportiv/{id}/stergeClub")
    public String deleteClub(@PathVariable("id") int id) {

        ClubDto club = clubService.findClubById(id);

        List<JucatoriDto> totiJucatorii = jucatoriService.getAllJucatori();

        List<JucatoriDto> jucatoriiClubuluiRespectiv = totiJucatorii.stream()
                .filter(j -> j.getClubId() == club.getId())
                .toList();

        for(int i=0;i<jucatoriiClubuluiRespectiv.size();i++){
            jucatoriService.deleteJucatori(jucatoriiClubuluiRespectiv.get(i).getId());
        }

        clubService.deleteClubById(club.getId());

        return "redirect:/clubsportiv";
    }

}