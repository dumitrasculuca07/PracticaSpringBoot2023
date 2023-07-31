package com.example.PracticaSpringBoot2023.repository;

import com.example.PracticaSpringBoot2023.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}