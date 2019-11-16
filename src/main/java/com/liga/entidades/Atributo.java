package com.liga.entidades;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "atributo")
@JsonIgnoreProperties(
        {
            "hibernateLazyInitializer", "handler"
        })
public class Atributo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "atributo", fetch = FetchType.LAZY)
    private List<JugadorAtributo> jugadorAtributoList;

    public Atributo() {
    }

    public Atributo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<JugadorAtributo> getJugadorAtributoList() {
        return jugadorAtributoList;
    }

    public void setJugadorAtributoList(List<JugadorAtributo> jugadorAtributoList) {
        this.jugadorAtributoList = jugadorAtributoList;
    }
}
