package com.example.PracticaSpringBoot2023.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClubDto {
    private int id;
    private String nume;
    private String vechime;
    private int trofee;
    private int nrJucatori;

    @Override
    public String toString() {
        return "ClubDto{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", vechime='" + vechime + '\'' +
                ", trofee=" + trofee +
                ", nrJucatori=" + nrJucatori +
                '}';
    }
}
