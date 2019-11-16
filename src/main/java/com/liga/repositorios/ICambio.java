package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.Cambio;
import org.springframework.stereotype.Repository;
@Repository
public interface ICambio extends JpaRepository<Cambio, Integer>{

}
