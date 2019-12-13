package com.liga.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Comparator;
import java.util.stream.Collectors;

@Entity
@Table(name = "temporada")
@JsonIgnoreProperties(
        {
            "hibernateLazyInitializer", "handler"
        })
public class Temporada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "fecha_ini")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date fechaIni;
    @Column(name = "estado")
    private Integer estado;
    @JsonIgnore
    @OneToMany(mappedBy = "temporada", fetch = FetchType.LAZY)
    private List<Jornada> jornadaList;
    @JsonIgnore
    @OneToMany(mappedBy = "temporada", fetch = FetchType.LAZY)
    private List<EquipoTemporada> equipoTemporadaList;
    @JsonIgnore
    @OneToMany(mappedBy = "temporada", fetch = FetchType.LAZY)
    private List<Tabla> tablaList;

    public Temporada() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Jornada> getJornadaList() {
        return jornadaList;
    }

    public void setJornadaList(List<Jornada> jornadaList) {
        this.jornadaList = jornadaList;
    }

    @XmlTransient
    public List<EquipoTemporada> getEquipoTemporadaList() {
        return equipoTemporadaList;
    }

    public void setEquipoTemporadaList(List<EquipoTemporada> equipoTemporadaList) {
        this.equipoTemporadaList = equipoTemporadaList;
    }

    public List<Tabla> getTablaList() {
        return tablaList;
    }

    public void setTablaList(List<Tabla> tablaList) {
        this.tablaList = tablaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Temporada))
        {
            return false;
        }
        Temporada other = (Temporada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "votaciones.Temporada[ id=" + id + " ]";
    }
}
