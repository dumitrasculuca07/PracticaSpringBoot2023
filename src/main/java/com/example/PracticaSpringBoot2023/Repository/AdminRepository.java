package com.example.PracticaSpringBoot2023.Repository;


import com.example.PracticaSpringBoot2023.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Login, Integer> {}
