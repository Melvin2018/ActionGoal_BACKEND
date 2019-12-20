package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.liga.entidades.Posicion;
@Repository
public interface IPosicion extends JpaRepository<Posicion,Integer>{

}
