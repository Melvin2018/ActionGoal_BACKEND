package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.Tarjeta;
import org.springframework.stereotype.Repository;
@Repository
public interface ITarjeta extends JpaRepository<Tarjeta,Integer>{

}
