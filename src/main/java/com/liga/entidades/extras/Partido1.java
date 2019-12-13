package com.liga.entidades.extras;

import com.liga.entidades.Jornada;
import com.liga.entidades.Partido;

public class Partido1 {
    private Partido partido;
    private Jornada jornada;
    private Integer gol1;
     private Integer gol2;
     public Partido1(){};

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Integer getGol1() {
        return gol1;
    }

    public void setGol1(Integer gol1) {
        this.gol1 = gol1;
    }

    public Integer getGol2() {
        return gol2;
    }

    public void setGol2(Integer gol2) {
        this.gol2 = gol2;
    }
     
}
