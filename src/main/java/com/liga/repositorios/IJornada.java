package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.Jornada;
import org.springframework.stereotype.Repository;
@Repository
public interface IJornada extends JpaRepository<Jornada,Integer> {

}
