package com.liga.controladores.inteligencia;

import java.util.List;
import com.liga.entidades.Gol;
import com.liga.entidades.Tabla;
import java.util.Objects;

public class MethodsTable {

    public MethodsTable() {
    }

    public Tabla tabla(Tabla t, List<Gol> goles) {
        int favor = t.getGf();
        int contra = t.getGc();
        int puntos = t.getPuntos();
        int pg = t.getPg();
        int pe = t.getPe();
        int pp = t.getPp();
        int favorr = (int) goles.stream().
                filter(g -> Objects.equals(g.getCarnet().getEquipo().getId(), t.getEquipo().getId()) & g.getTipo()).count();
        int contrar = goles.size() - favor;
        contra += contrar;
        favor += favorr;
        if (favorr > contrar)
        {
            puntos += 3;
            pg += 1;
        } else if (favorr == contrar)
        {
            puntos += 1;
            pe += 1;
        } else
        {
            pp += 1;
        }
        t.setGc(contra);
        t.setGf(favor);
        t.setPuntos(puntos);
        t.setPj(t.getPj() + 1);
        t.setPe(pe);
        t.setPp(pp);
        t.setPg(pg);
        return t;
    }
}
