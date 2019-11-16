package com.liga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tabla")
@JsonIgnoreProperties(
{
    "hibernateLazyInitializer", "handler"
})
public class Tabla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private Integer pj;
    @Column
    private Integer pg;
    @Column
    private Integer puntos;
    @Column
    private Integer pe;
    @Column
    private Integer pp;
    @Column
    private Integer gf;
    @Column
    private Integer gc;
    @JoinColumn(name = "equipo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private EquipoTemporada equipo;
    @JoinColumn(name = "temporada", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Temporada temporada;

    public Tabla() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EquipoTemporada getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoTemporada equipo) {
        this.equipo = equipo;
    }

    public Integer getPj() {
        return pj;
    }

    public void setPj(Integer pj) {
        this.pj = pj;
    }

    public Integer getPg() {
        return pg;
    }

    public void setPg(Integer pg) {
        this.pg = pg;
    }

    public Integer getPe() {
        return pe;
    }

    public void setPe(Integer pe) {
        this.pe = pe;
    }

    public Integer getPp() {
        return pp;
    }

    public void setPp(Integer pp) {
        this.pp = pp;
    }

    public Integer getGf() {
        return gf;
    }

    public void setGf(Integer gf) {
        this.gf = gf;
    }

    public Integer getGc() {
        return gc;
    }

    public void setGc(Integer gc) {
        this.gc = gc;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
}
