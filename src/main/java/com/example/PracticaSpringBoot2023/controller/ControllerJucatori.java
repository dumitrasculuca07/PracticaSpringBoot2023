package com.example.PracticaSpringBoot2023.controller;
import com.example.PracticaSpringBoot2023.Repository.ClubSportivRepository;
import com.example.PracticaSpringBoot2023.Repository.JucatoriRepository;
import com.example.PracticaSpringBoot2023.dto.ClubDto;
import com.example.PracticaSpringBoot2023.dto.JucatoriDto;
import com.example.PracticaSpringBoot2023.model.Jucatori;
import com.example.PracticaSpringBoot2023.service.ClubService;
import com.example.PracticaSpringBoot2023.service.JucatoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
public class ControllerJucatori {

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


        ClubDto club = clubService.findClubById(id);

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

    //Adauga:
    @GetMapping(value = "/clubsportiv/{id_url}/formjucatori")
    public String adaugaJucator(@PathVariable("id_url") int id_url,Model model){

        ClubDto club = clubService.findClubById(id_url);
        List<JucatoriDto> jucatori = jucatoriService.getAllJucatori();

        club.setNrJucatori(club.getNrJucatori()+1);
        clubService.saveClub(club);

        model.addAttribute("club",club);
        JucatoriDto jucatoriDto = new JucatoriDto();
        jucatoriDto.setClubId(club.getId());

        model.addAttribute("jucator", jucatoriDto);

        return "formjucatori";
    }

    //Edit:
    @GetMapping(value = "/jucatori/{id_url}/editjucatorform")
    public String editClub(@PathVariable("id_url") int id_url, Model model){

        JucatoriDto jucatorDto = jucatoriService.findJucatorById(id_url);
        ClubDto clubDto = clubService.findClubById(jucatorDto.getClubId());

        model.addAttribute("jucator", jucatorDto);
        model.addAttribute("club", clubDto);

        return "formjucatori";
    }

    @PostMapping(value = "/clubsportiv/{id_url}")
    public String adaugaJucatorSubmit(@ModelAttribute("jucator") JucatoriDto jucator, @PathVariable("id_url") int id_url){

        ClubDto club = clubService.findClubById(jucator.getClubId());
        clubService.saveClub(club);

        jucatoriService.saveJucatori(jucator);

        return "redirect:/clubsportiv/"+id_url;
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
