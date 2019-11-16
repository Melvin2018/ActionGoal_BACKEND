package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liga.entidades.Jugador;
@Repository
public interface IJugador extends JpaRepository<Jugador, Integer> {

}