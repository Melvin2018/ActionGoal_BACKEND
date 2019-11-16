package com.liga.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "equipo_temporada")
@JsonIgnoreProperties(
{
    "hibernateLazyInitializer", "handler"
})
public class EquipoTemporada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "equipo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Equipo equipo;
    @JoinColumn(name = "representante", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Jugador representante;
    @JoinColumn(name = "temporada", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Temporada temporada;
    @JsonIgnore
    @OneToMany(mappedBy = "equipo", fetch = FetchType.LAZY)
    private List<Formacion> formacionList;
    @JsonIgnore
    @OneToMany(mappedBy = "equipo", fetch = FetchType.LAZY)
    private List<Carnet> carnetList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipo1", fetch = FetchType.LAZY)
    private List<Partido> partidoList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipo2", fetch = FetchType.LAZY)
    private List<Partido> partidoList1;

    public EquipoTemporada() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<Formacion> getFormacionList() {
        return formacionList;
    }

    public void setFormacionList(List<Formacion> formacionList) {
        this.formacionList = formacionList;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Jugador getRepresentante() {
        return representante;
    }

    public void setRepresentante(Jugador representante) {
        this.representante = representante;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    @XmlTransient
    public List<Carnet> getCarnetList() {
        return carnetList;
    }

    public void setCarnetList(List<Carnet> carnetList) {
        this.carnetList = carnetList;
    }

    @XmlTransient
    public List<Partido> getPartidoList() {
        return partidoList;
    }

    public void setPartidoList(List<Partido> partidoList) {
        this.partidoList = partidoList;
    }

    @XmlTransient
    public List<Partido> getPartidoList1() {
        return partidoList1;
    }

    public void setPartidoList1(List<Partido> partidoList1) {
        this.partidoList1 = partidoList1;
    }
}
