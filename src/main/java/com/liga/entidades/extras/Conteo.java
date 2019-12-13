package com.liga.entidades.extras;

import com.liga.entidades.Carnet;
import com.liga.entidades.Equipo;

public class Conteo {
   private Carnet jugador;
   private Integer goles;
   private Integer amarillas;
   private Integer rojas;
   private Equipo equipo;
   
    public Conteo() {
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Carnet getJugador() {
        return jugador;
    }

    public void setJugador(Carnet jugador) {
        this.jugador = jugador;
    }

    public Integer getGoles() {
        return goles;
    }

    public void setGoles(Integer goles) {
        this.goles = goles;
    }

    public Integer getAmarillas() {
        return amarillas;
    }

    public void setAmarillas(Integer amarillas) {
        this.amarillas = amarillas;
    }

    public Integer getRojas() {
        return rojas;
    }

    public void setRojas(Integer rojas) {
        this.rojas = rojas;
    }
   
}
