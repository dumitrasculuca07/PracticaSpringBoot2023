package com.example.PracticaSpringBoot2023.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClubFormDto {
    private int id;
    private String nume;
    private String vechime;
    private int trofee;
    private int nrJucatori;
}
