package com.example.PracticaSpringBoot2023.Repository;

import com.example.PracticaSpringBoot2023.model.Jucatori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JucatoriRepository extends JpaRepository<Jucatori, Integer> {}
