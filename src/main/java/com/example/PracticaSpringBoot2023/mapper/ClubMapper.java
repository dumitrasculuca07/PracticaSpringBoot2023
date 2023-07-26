package com.example.PracticaSpringBoot2023.mapper;

import com.example.PracticaSpringBoot2023.dto.ClubDto;
import com.example.PracticaSpringBoot2023.dto.ClubFormDto;
import com.example.PracticaSpringBoot2023.model.ClubSportiv;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ClubMapper {
    public List<ClubDto> getClubDtoList(List<ClubSportiv> clubSportivList) {
        return clubSportivList.stream()
                .map(clubsportiv -> mapToClubDto(clubsportiv))
                .collect(Collectors.toList());
    }

    public ClubDto mapToClubDto(ClubSportiv clubSportiv) {
        return ClubDto.builder()
                .id(clubSportiv.getId())
                .nume(clubSportiv.getNume())
                .nrJucatori(clubSportiv.getNrJucatori())
                .vechime(clubSportiv.getVechime())
                .trofee(clubSportiv.getTrofee())
                .build();
    }

    public ClubSportiv mapToClub(ClubFormDto clubFormDto) {
        ClubSportiv clubSportiv = ClubSportiv.builder()
                .id(clubFormDto.getId())
                .nume(clubFormDto.getNume())
                .nrJucatori(clubFormDto.getNrJucatori())
                .vechime(clubFormDto.getVechime())
                .trofee(clubFormDto.getTrofee())
                .build();
        return clubSportiv;
    }

}
