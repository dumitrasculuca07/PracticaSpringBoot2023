package com.example.PracticaSpringBoot2023.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_jucatori")
public class Jucatori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nume")
    private String nume;
    @Column(name = "varsta")
    private int varsta;
    @Column(name = "vechime")
    private int vechime;

    @ManyToOne
    @JoinColumn(name = "id_club")
    private ClubSportiv clubsportiv;


    public Jucatori(){}
    public Jucatori(int id, String nume, int varsta, int vechime) {

        this.id = id;
        this.nume = nume;
        this.varsta = varsta;
        this.vechime = vechime;
    }

    public ClubSportiv getClubsportiv() {
        return clubsportiv;
    }

    public void setClubsportiv(ClubSportiv clubsportiv) {
        this.clubsportiv = clubsportiv;
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
