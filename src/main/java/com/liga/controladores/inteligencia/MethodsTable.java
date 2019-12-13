package com.liga.controladores.inteligencia;

import com.liga.entidades.EquipoTemporada;
import com.liga.entidades.Partido;
import com.liga.entidades.extras.Resultado;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MethodsTable {

    public MethodsTable() {
    }

    public List<Resultado> tabla(Partido p) {
        Resultado r = new Resultado();
        Resultado r1 = new Resultado();
        EquipoTemporada e1 = p.getEquipo1();
        EquipoTemporada e2 = p.getEquipo2();
        AtomicInteger favor = new AtomicInteger(0);
        AtomicInteger contra = new AtomicInteger(0);
        r.setEquipo(e1);
        r1.setEquipo(e2);
        p.getGolList().forEach(x ->
        {
            if (Objects.equals(x.getCarnet().getEquipo().getId(), p.getEquipo1().getId()))
            {
                if (x.getTipo())
                {
                    favor.incrementAndGet();
                } else
                {
                    contra.incrementAndGet();
                }
            } else
            {
                if (!x.getTipo())
                {
                    favor.incrementAndGet();
                } else
                {
                    contra.incrementAndGet();
                }
            }
        });
        r.setFavor(favor.get());
        r.setContra(contra.get());
        r1.setContra(favor.get());
        r1.setFavor(contra.get());
        if (favor.get() > contra.get())
        {
            r.setPuntos(3);
            r1.setPuntos(0);
        } else if (favor.get() == contra.get())
        {
            r.setPuntos(1);
            r1.setPuntos(1);
        } else
        {
            r.setPuntos(0);
            r1.setPuntos(3);
        }
        List<Resultado> resu = new ArrayList<>();
        resu.add(r);
        resu.add(r1);
        return resu;
    }

    public List<Integer> goles(Partido p) {
        List<Integer> go = new ArrayList<>();
        AtomicInteger favor = new AtomicInteger(0);
        AtomicInteger contra = new AtomicInteger(0);
        p.getGolList().forEach(x ->
        {
            if (Objects.equals(x.getCarnet().getEquipo().getId(), p.getEquipo1().getId()))
            {
                if (x.getTipo())
                {
                    favor.incrementAndGet();
                } else
                {
                    contra.incrementAndGet();
                }
            } else
            {
                if (!x.getTipo())
                {
                    favor.incrementAndGet();
                } else
                {
                    contra.incrementAndGet();
                }
            }
        });
        go.add(favor.get());
        go.add(contra.get());
        return go;
    }
}
