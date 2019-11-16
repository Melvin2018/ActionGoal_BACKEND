package com.liga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "cambio")
@JsonIgnoreProperties(
        {
            "hibernateLazyInitializer", "handler"
        })
public class Cambio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "razon")
    private String razon;
    @JoinColumn(name = "saliente", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Carnet saliente;
    @JoinColumn(name = "entrante", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Carnet entrante;
    @JoinColumn(name = "partido", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Partido partido;

    public Cambio() {
    }

    public Cambio(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public Carnet getSaliente() {
        return saliente;
    }

    public void setSaliente(Carnet saliente) {
        this.saliente = saliente;
    }

    public Carnet getEntrante() {
        return entrante;
    }

    public void setEntrante(Carnet entrante) {
        this.entrante = entrante;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
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
        if (!(object instanceof Cambio))
        {
            return false;
        }
        Cambio other = (Cambio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "votaciones.Cambio[ id=" + id + " ]";
    }

}
