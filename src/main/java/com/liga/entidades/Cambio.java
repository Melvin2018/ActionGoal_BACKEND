package com.liga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Timestamp;
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
    private Integer id;
    @Column(name = "hora")
    private Timestamp hora;
    @Column(name = "minuto")
    private Integer minuto;
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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
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
}
