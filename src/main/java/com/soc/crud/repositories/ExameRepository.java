package com.soc.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.soc.crud.models.Exame;


import com.soc.crud.models.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {
}