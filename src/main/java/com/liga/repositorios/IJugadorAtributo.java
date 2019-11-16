package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.JugadorAtributo;
import org.springframework.stereotype.Repository;
@Repository
public interface IJugadorAtributo extends JpaRepository<JugadorAtributo,Integer>{

}
