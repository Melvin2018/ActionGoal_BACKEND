package com.liga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
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

@Entity
@Table(name = "carnet")
@JsonIgnoreProperties(
{
    "hibernateLazyInitializer", "handler"
})
public class Carnet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dorsal")
    private Integer dorsal;
     @JsonIgnore
    @OneToMany(mappedBy = "saliente", fetch = FetchType.LAZY)
    private List<Cambio> cambioList;
     @JsonIgnore
    @OneToMany(mappedBy = "entrante", fetch = FetchType.LAZY)
    private List<Cambio> cambioList1;
     @JsonIgnore
    @OneToMany(mappedBy = "carnet", fetch = FetchType.LAZY)
    private List<Gol> golList;
     @JsonIgnore
    @OneToMany(mappedBy = "jugador", fetch = FetchType.LAZY)
    private List<FormacionCarnet> formacionCarnetList;
    @JoinColumn(name = "equipo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private EquipoTemporada equipo;
    @JoinColumn(name = "jugador", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Jugador jugador;
     @JsonIgnore
    @OneToMany(mappedBy = "carnet", fetch = FetchType.LAZY)
    private List<Tarjeta> tarjetaList;

    public Carnet() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDorsal() {
        return dorsal;
    }

    public void setDorsal(Integer dorsal) {
        this.dorsal = dorsal;
    }

    @XmlTransient
    public List<Cambio> getCambioList() {
        return cambioList;
    }

    public void setCambioList(List<Cambio> cambioList) {
        this.cambioList = cambioList;
    }

    @XmlTransient
    public List<Cambio> getCambioList1() {
        return cambioList1;
    }

    public void setCambioList1(List<Cambio> cambioList1) {
        this.cambioList1 = cambioList1;
    }

    @XmlTransient
    public List<Gol> getGolList() {
        return golList;
    }

    public void setGolList(List<Gol> golList) {
        this.golList = golList;
    }

    @XmlTransient
    public List<FormacionCarnet> getFormacionCarnetList() {
        return formacionCarnetList;
    }

    public void setFormacionCarnetList(List<FormacionCarnet> formacionCarnetList) {
        this.formacionCarnetList = formacionCarnetList;
    }

    public EquipoTemporada getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoTemporada equipo) {
        this.equipo = equipo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @XmlTransient
    public List<Tarjeta> getTarjetaList() {
        return tarjetaList;
    }

    public void setTarjetaList(List<Tarjeta> tarjetaList) {
        this.tarjetaList = tarjetaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
