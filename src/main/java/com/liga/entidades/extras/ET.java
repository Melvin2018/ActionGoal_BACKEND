package com.liga.entidades.extras;

import com.liga.entidades.EquipoTemporada;

public class ET {
	private EquipoTemporada equipo;
	private  Integer integrantes;
	public ET(EquipoTemporada e, int i){
	this.equipo=e;
	this.integrantes=i;
	}
	public EquipoTemporada getEquipo() {
	return equipo;
	}
	public void setEquipo(EquipoTemporada equipo) {
	this.equipo = equipo;
	}
	public Integer getIntegrantes() {
	return integrantes;
	}
	public void setIntegrantes(Integer integrantes) {
	this.integrantes = integrantes;
	}
}