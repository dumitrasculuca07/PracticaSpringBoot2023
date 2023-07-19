package com.example.PracticaSpringBoot2023.model;

import jakarta.persistence.*;

@Entity
@Table(name="t_login")
public class Login {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nume")
    private String nume;

    @Column(name = "parola")
    private String parola;


    public Login(String nume, String parola) {
        this.nume = nume;
        this.parola = parola;
    }
    public Login(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}
