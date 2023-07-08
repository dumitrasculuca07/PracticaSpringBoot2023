package com.example.PracticaSpringBoot2023.model;

public class ClubSportiv {
    private int id;
    private int nrJucatori;
    private String nume;
    private String vechime;
    private int trofee;

    public ClubSportiv(int id, int nrJucatori, String nume, String vechime, int trofee) {
        this.id = id;
        this.nrJucatori = nrJucatori;
        this.nume = nume;
        this.vechime = vechime;
        this.trofee = trofee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNrJucatori() {
        return nrJucatori;
    }

    public void setNrJucatori(int nrJucatori) {
        this.nrJucatori = nrJucatori;
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
}
