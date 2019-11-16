package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.Gol;
import org.springframework.stereotype.Repository;
@Repository
public interface IGol extends JpaRepository<Gol, Integer>{

}
