package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.Formacion;
import org.springframework.stereotype.Repository;
@Repository
public interface IFormacion extends JpaRepository<Formacion, Integer> {

}
