package com.example.PracticaSpringBoot2023.dto;

import com.example.PracticaSpringBoot2023.model.ClubSportiv;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class JucatoriDto {
    private int id;
    private String nume;
    private int vechime;
    private int varsta;
    private int clubId;


}
