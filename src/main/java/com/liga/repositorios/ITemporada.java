package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.Temporada;
import org.springframework.stereotype.Repository;
@Repository
public interface ITemporada extends JpaRepository<Temporada,Integer>{

}
