package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.PartidoArbitro;
import org.springframework.stereotype.Repository;
@Repository
public interface IPartidoArbitro extends JpaRepository<PartidoArbitro,Integer>{

}
