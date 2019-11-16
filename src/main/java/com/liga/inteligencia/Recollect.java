package com.liga.inteligencia;

import java.util.ArrayList;
import java.util.List;
import com.liga.entidades.EquipoTemporada;
import com.liga.entidades.Partido;
import com.liga.entidades.Temporada;
import java.util.Objects;

public class Recollect {
	private final Temporada t;
	public Recollect(Temporada tempo){
	t=tempo;
	}
	public List<Partido> Jugados(EquipoTemporada e) {
		List<Partido> partidos=new ArrayList<>();
                t.getJornadaList().stream().forEach((j) -> {
                    j.getPartidoList().stream().filter((y) -> (equipo(y.getEquipo1(), e) | equipo(y.getEquipo2(),e))).forEach((y) -> {
                        partidos.add(y);
                    });
            });
		return partidos;
	}
	private Boolean equipo(EquipoTemporada e,EquipoTemporada e1 ) {
		return Objects.equals(e.getId(), e1.getId());
	}
}
