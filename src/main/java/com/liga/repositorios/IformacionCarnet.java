package com.liga.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liga.entidades.FormacionCarnet;
import org.springframework.stereotype.Repository;
@Repository
public interface IformacionCarnet extends JpaRepository<FormacionCarnet,Integer>{

}
