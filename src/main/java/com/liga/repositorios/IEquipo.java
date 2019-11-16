package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.liga.entidades.Equipo;
import org.springframework.stereotype.Repository;
@Repository
public interface IEquipo extends JpaRepository<Equipo,Integer>{

}
