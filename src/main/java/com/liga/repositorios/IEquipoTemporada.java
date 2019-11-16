package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.EquipoTemporada;
import org.springframework.stereotype.Repository;
@Repository
public interface IEquipoTemporada extends JpaRepository<EquipoTemporada,Integer>{

}
