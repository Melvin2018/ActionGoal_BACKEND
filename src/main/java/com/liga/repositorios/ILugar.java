package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.Lugar;
import org.springframework.stereotype.Repository;
@Repository
public interface ILugar extends JpaRepository<Lugar,Integer>{

}
