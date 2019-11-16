package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.Carnet;
import org.springframework.stereotype.Repository;
@Repository
public interface ICarnet extends JpaRepository<Carnet, Integer> {

}
