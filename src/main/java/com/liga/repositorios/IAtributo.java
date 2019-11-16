package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.liga.entidades.Atributo;
import org.springframework.stereotype.Repository;

@Repository
public interface IAtributo extends JpaRepository<Atributo, Integer>{

}
