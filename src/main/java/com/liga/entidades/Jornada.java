package com.liga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "jornada")
@JsonIgnoreProperties(
        {
            "hibernateLazyInitializer", "handler"
        })
public class Jornada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "temporada", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Temporada temporada;
    @JsonIgnore
    @OneToMany(mappedBy = "jornada", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Partido> partidoList;

    public Jornada() {
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }
    @XmlTransient
    public List<Partido> getPartidoList() {
        return partidoList.stream().sorted((x, y) -> x.getHorario().getJerarquia().compareTo(y.getHorario().getJerarquia())).collect(Collectors.toList());
    }

    public void setPartidoList(List<Partido> partidoList) {
        this.partidoList = partidoList;
    }
}
