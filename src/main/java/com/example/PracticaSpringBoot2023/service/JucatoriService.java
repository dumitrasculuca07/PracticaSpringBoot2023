package com.example.PracticaSpringBoot2023.service;
import com.example.PracticaSpringBoot2023.Repository.JucatoriRepository;
import com.example.PracticaSpringBoot2023.dto.JucatoriDto;
import com.example.PracticaSpringBoot2023.mapper.JucatoriMapper;
import com.example.PracticaSpringBoot2023.model.Jucatori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JucatoriService {

    @Autowired
    JucatoriRepository jucatoriRepository;

    @Autowired
    JucatoriMapper jucatoriMapper;

    public List<JucatoriDto> getAllJucatori() {
        List<Jucatori> jucatoriList = jucatoriRepository.findAll();
        return jucatoriMapper.getJucatoriDtoList(jucatoriList);
    }
    public JucatoriDto findJucatorById(int id){
        return jucatoriMapper.mapToJucatoriDto(jucatoriRepository.findById(id).get());
    }
    public void saveJucatori(JucatoriDto jucatoriDto) {
        Jucatori jucatori = jucatoriMapper.mapToJucatori(jucatoriDto);
        jucatoriRepository.save(jucatori);
    }

    public void deleteJucatori(int jucatoriId) {
        jucatoriRepository.deleteById(jucatoriId);
    }
}
