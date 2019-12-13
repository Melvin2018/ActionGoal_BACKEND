package com.liga.entidades.extras;

import com.liga.entidades.EquipoTemporada;

public class Resultado {
    private EquipoTemporada equipo;
    private Integer puntos;
    private Integer favor;
    private Integer contra;
    public Resultado(){}

    public EquipoTemporada getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoTemporada equipo) {
        this.equipo = equipo;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getFavor() {
        return favor;
    }

    public void setFavor(Integer favor) {
        this.favor = favor;
    }

    public Integer getContra() {
        return contra;
    }

    public void setContra(Integer contra) {
        this.contra = contra;
    }
    
}
