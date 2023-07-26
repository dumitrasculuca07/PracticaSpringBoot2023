package com.example.PracticaSpringBoot2023.service;

import com.example.PracticaSpringBoot2023.Repository.ClubSportivRepository;
import com.example.PracticaSpringBoot2023.dto.ClubDto;
import com.example.PracticaSpringBoot2023.dto.ClubFormDto;
import com.example.PracticaSpringBoot2023.mapper.ClubMapper;
import com.example.PracticaSpringBoot2023.model.ClubSportiv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClubService {

    @Autowired
    ClubSportivRepository clubSportivRepository;

    @Autowired
    ClubMapper clubMapper;

    public List<ClubDto> getAllClubs() {
        List<ClubSportiv> clubSportivList = clubSportivRepository.findAll();
        return clubMapper.getClubDtoList(clubSportivList);
    }

    public ClubDto findClubById(int id){
        return clubMapper.mapToClubDto(clubSportivRepository.findById(id).get());
    }
    public void saveClub(ClubFormDto clubFormDto) {
        ClubSportiv clubSportiv = clubMapper.mapToClub(clubFormDto);
        clubSportivRepository.save(clubSportiv);
    }

    public void deleteClub(int clubId) {
        clubSportivRepository.deleteById(clubId);

    }

}
