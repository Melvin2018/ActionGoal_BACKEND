package com.liga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
@Table(name = "partido")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Partido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "estado")
    private Boolean estado;
    @JsonIgnore
    @OneToMany(mappedBy = "partido", fetch = FetchType.LAZY)
    private List<Cambio> cambioList;
    @JsonIgnore
    @OneToMany(mappedBy = "partido", fetch = FetchType.LAZY)
    private List<Gol> golList;
    @JsonIgnore
    @OneToMany(mappedBy = "partido", fetch = FetchType.LAZY)
    private List<PartidoArbitro> partidoArbitroList;
    @JsonIgnore
    @OneToMany(mappedBy = "partido", fetch = FetchType.LAZY)
    private List<Tarjeta> tarjetaList;
    @JoinColumn(name = "equipo1", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EquipoTemporada equipo1;
    @JoinColumn(name = "equipo2", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EquipoTemporada equipo2;
    @JoinColumn(name = "horario", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Horario horario;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "jornada", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Jornada jornada;

    public Partido() {
    }

    public Partido(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    @XmlTransient
    public List<Cambio> getCambioList() {
        return cambioList;
    }

    public void setCambioList(List<Cambio> cambioList) {
        this.cambioList = cambioList;
    }

    @XmlTransient
    public List<Gol> getGolList() {
        return golList;
    }

    public void setGolList(List<Gol> golList) {
        this.golList = golList;
    }

    @XmlTransient
    public List<PartidoArbitro> getPartidoArbitroList() {
        return partidoArbitroList;
    }

    public void setPartidoArbitroList(List<PartidoArbitro> partidoArbitroList) {
        this.partidoArbitroList = partidoArbitroList;
    }

    @XmlTransient
    public List<Tarjeta> getTarjetaList() {
        return tarjetaList;
    }

    public void setTarjetaList(List<Tarjeta> tarjetaList) {
        this.tarjetaList = tarjetaList;
    }

    public EquipoTemporada getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(EquipoTemporada equipo1) {
        this.equipo1 = equipo1;
    }

    public EquipoTemporada getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(EquipoTemporada equipo2) {
        this.equipo2 = equipo2;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partido)) {
            return false;
        }
        Partido other = (Partido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "votaciones.Partido[ id=" + id + " ]";
    }
    
}
