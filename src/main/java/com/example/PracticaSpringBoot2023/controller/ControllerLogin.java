package com.example.PracticaSpringBoot2023.controller;

import com.example.PracticaSpringBoot2023.Repository.AdminRepository;
import com.example.PracticaSpringBoot2023.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class ControllerLogin {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping(value ="/login")
    public String loginPage(Model model){

        model.addAttribute("login", new Login());
        model.addAttribute("text","Introdu datele contului: ");
        return "login";
    }

    @PostMapping(value ="/loginStatus")
    public String loginStatus(@ModelAttribute("login") Login login, Model model){

        Login loginUser = login;
        Login loginDB = adminRepository.findById(2).get();

        if(Objects.equals(loginUser.getNume(), loginDB.getNume()) &&
                Objects.equals(loginUser.getParola(), loginDB.getParola())){
            return "redirect:/clubsportiv";
        }
        else{
            System.out.println();
            model.addAttribute("text","Ai introdus contul gresit");
            return "login";
        }
    }
}
