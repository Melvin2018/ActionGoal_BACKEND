package com.liga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "formacion_carnet")
@JsonIgnoreProperties(
        {
            "hibernateLazyInitializer", "handler"
        })
public class FormacionCarnet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "titular")
    private Boolean titular;
    @JoinColumn(name = "jugador", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Carnet jugador;
    @JoinColumn(name = "formacion", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Formacion formacion;

    public FormacionCarnet() {
    }

    public FormacionCarnet(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getTitular() {
        return titular;
    }

    public void setTitular(Boolean titular) {
        this.titular = titular;
    }

    public Carnet getJugador() {
        return jugador;
    }

    public void setJugador(Carnet jugador) {
        this.jugador = jugador;
    }

    public Formacion getFormacion() {
        return formacion;
    }

    public void setFormacion(Formacion formacion) {
        this.formacion = formacion;
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
        if (!(object instanceof FormacionCarnet))
        {
            return false;
        }
        FormacionCarnet other = (FormacionCarnet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "votaciones.FormacionCarnet[ id=" + id + " ]";
    }

}
