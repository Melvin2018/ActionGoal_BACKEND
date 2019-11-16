package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.Arbitro;
import org.springframework.stereotype.Repository;
@Repository
public interface IArbitro extends JpaRepository<Arbitro, Integer> {

}
