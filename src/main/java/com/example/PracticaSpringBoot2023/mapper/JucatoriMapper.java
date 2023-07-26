package com.example.PracticaSpringBoot2023.mapper;

import com.example.PracticaSpringBoot2023.dto.ClubDto;
import com.example.PracticaSpringBoot2023.dto.ClubFormDto;
import com.example.PracticaSpringBoot2023.dto.JucatoriDto;
import com.example.PracticaSpringBoot2023.dto.JucatoriFormDto;
import com.example.PracticaSpringBoot2023.model.ClubSportiv;
import com.example.PracticaSpringBoot2023.model.Jucatori;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class JucatoriMapper {
    public List<JucatoriDto> getJucatoriDtoList(List<Jucatori> jucatoriList) {
        return jucatoriList.stream()
                .map(jucatori -> mapToJucatoriDto(jucatori))
                .collect(Collectors.toList());
    }


    public JucatoriDto mapToJucatoriDto(Jucatori jucatori) {
        return JucatoriDto.builder()
                .id(jucatori.getId())
                .nume(jucatori.getNume())
                .varsta(jucatori.getVarsta())
                .vechime(jucatori.getVechime())
                .clubId(jucatori.getClubsportiv().getId())
                .build();
    }

    public Jucatori mapToJucatori(JucatoriFormDto jucatoriFormDto) {
        Jucatori jucatori = Jucatori.builder()
                .id(jucatoriFormDto.getId())
                .nume(jucatoriFormDto.getNume())
                .varsta(jucatoriFormDto.getVarsta())
                .vechime(jucatoriFormDto.getVechime())
                .clubsportiv(ClubSportiv.builder().id(jucatoriFormDto.getClubId()).build())
                .build();
        return jucatori;
    }
}
