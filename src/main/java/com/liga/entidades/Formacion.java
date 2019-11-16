package com.liga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "formacion")
@JsonIgnoreProperties(
        {
            "hibernateLazyInitializer", "handler"
        })
public class Formacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @JsonIgnore
    @OneToMany(mappedBy = "formacion", fetch = FetchType.LAZY)
    private List<FormacionCarnet> formacionCarnetList;
    @JoinColumn(name = "equipo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private EquipoTemporada equipo;
    @JoinColumn(name = "jornada", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Jornada jornada;

    public Formacion() {
    }

    public Formacion(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Formacion))
        {
            return false;
        }
        Formacion other = (Formacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "votaciones.Formacion[ id=" + id + " ]";
    }

}
