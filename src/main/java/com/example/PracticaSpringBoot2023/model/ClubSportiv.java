package com.example.PracticaSpringBoot2023.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "t_clubsportiv")
@AllArgsConstructor
@Builder
public class ClubSportiv {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nume")
    private String nume;

    @Column(name = "vechime")
    private String vechime;

    @Column(name = "nrjucatori")
    private int nrJucatori;
    @Column(name = "trofee")
    private int trofee;

    @OneToMany(mappedBy = "clubsportiv")
    public List<Jucatori> jucatori;


    public ClubSportiv() {}

    public ClubSportiv(int id, String nume, String vechime, int nrJucatori, int trofee) {
        this.id = id;
        this.nume = nume;
        this.vechime = vechime;
        this.nrJucatori = nrJucatori;
        this.trofee = trofee;
    }

    public int getNrJucatori() {
        return nrJucatori;
    }

    public void setNrJucatori(int nrJucatori) {
        this.nrJucatori = nrJucatori;
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

    public String getVechime() {
        return vechime;
    }

    public void setVechime(String vechime) {
        this.vechime = vechime;
    }

    public int getTrofee() {
        return trofee;
    }

    public void setTrofee(int trofee) {
        this.trofee = trofee;
    }

    public List<Jucatori> getJucatori() {
        return jucatori;
    }

    public void setJucatori(List<Jucatori> jucatori) {
        this.jucatori = jucatori;
    }

    @Override
    public String toString() {
        return "ClubSportiv{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", vechime='" + vechime + '\'' +
                ", trofee=" + trofee +
                ", jucatori=" + jucatori +
                '}';
    }
}
