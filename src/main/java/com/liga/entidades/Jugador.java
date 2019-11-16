package com.liga.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "jugador")
public class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "foto")
    private String foto;
    @Column(name = "posicion")
    private String posicion;
    @JoinColumn(name = "persona", referencedColumnName = "id")
    @ManyToOne(optional = false,fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Persona persona;
    @JsonIgnore
    @OneToMany(mappedBy = "jugador", fetch = FetchType.LAZY)
    private List<Carnet> carnetList;

    public Jugador() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String po) {
        this.posicion= po;
    }
   
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

	public List<Carnet> getCarnetList() {
		return carnetList;
	}

	public void setCarnetList(List<Carnet> carnetList) {
		this.carnetList = carnetList;
	}

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", foto=" + foto + ", posicion=" + posicion + ", persona="
				+ persona + "]";
	}
    
}
