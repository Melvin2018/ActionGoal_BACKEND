package com.liga.entidades;

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

@Entity
@Table(name = "arbitro")
@JsonIgnoreProperties(
        {
            "hibernateLazyInitializer", "handler"
        })
public class Arbitro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "persona", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;
    @OneToMany(mappedBy = "arbitro", fetch = FetchType.LAZY)
    private List<PartidoArbitro> partidoArbitroList;

    public Arbitro() {
    }

    public Arbitro(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @XmlTransient
    public List<PartidoArbitro> getPartidoArbitroList() {
        return partidoArbitroList;
    }

    public void setPartidoArbitroList(List<PartidoArbitro> partidoArbitroList) {
        this.partidoArbitroList = partidoArbitroList;
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
        if (!(object instanceof Arbitro))
        {
            return false;
        }
        Arbitro other = (Arbitro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "votaciones.Arbitro[ id=" + id + " ]";
    }

}
