package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.liga.entidades.Partido;
@Repository
public interface IPartido extends JpaRepository<Partido,Integer>{

}
