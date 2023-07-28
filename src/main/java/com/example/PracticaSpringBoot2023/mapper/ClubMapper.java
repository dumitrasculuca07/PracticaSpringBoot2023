package com.example.PracticaSpringBoot2023.mapper;
import com.example.PracticaSpringBoot2023.dto.ClubDto;
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

    public ClubSportiv mapToClub(ClubDto clubDto) {
        ClubSportiv clubSportiv = ClubSportiv.builder()
                .id(clubDto.getId())
                .nume(clubDto.getNume())
                .nrJucatori(clubDto.getNrJucatori())
                .vechime(clubDto.getVechime())
                .trofee(clubDto.getTrofee())
                .build();
        return clubSportiv;
    }
}
