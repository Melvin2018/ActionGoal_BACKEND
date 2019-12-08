package com.liga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer id;
    @Column(name = "titular")
    private Boolean titular;
    @JoinColumn(name = "jugador", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Carnet jugador;
    @JsonIgnore
    @JoinColumn(name = "formacion", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Formacion formacion;

    public FormacionCarnet() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

}
