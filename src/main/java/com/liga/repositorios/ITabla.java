package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.liga.entidades.Tabla;
import org.springframework.stereotype.Repository;
@Repository
public interface ITabla extends JpaRepository<Tabla, Integer> {

}
