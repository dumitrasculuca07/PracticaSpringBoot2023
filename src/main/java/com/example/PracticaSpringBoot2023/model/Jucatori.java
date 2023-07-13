package com.example.PracticaSpringBoot2023.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class Jucatori {
    private int id;
    private String nume;
    private int varsta;
    private int vechime;

    public Jucatori(int id, String nume, int varsta, int vechime) {

        this.id = id;
        this.nume = nume;
        this.varsta = varsta;
        this.vechime = vechime;
    }

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

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public int getVechime() {
        return vechime;
    }

    public void setVechime(int vechime) {
        this.vechime = vechime;
    }


    @Override
    public String toString() {
        return "Jucatori{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", varsta=" + varsta +
                ", vechime=" + vechime +
                '}';
    }
}
