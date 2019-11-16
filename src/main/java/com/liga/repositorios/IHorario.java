package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.Horario;
import org.springframework.stereotype.Repository;
@Repository
public interface IHorario extends JpaRepository<Horario,Integer>{
	
}
