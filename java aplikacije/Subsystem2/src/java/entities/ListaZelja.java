/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mihajlo
 */
@Entity
@Table(name = "lista_zelja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaZelja.findAll", query = "SELECT l FROM ListaZelja l"),
    @NamedQuery(name = "ListaZelja.findByIdK", query = "SELECT l FROM ListaZelja l WHERE l.idK = :idK"),
    @NamedQuery(name = "ListaZelja.findByDatumKreiranja", query = "SELECT l FROM ListaZelja l WHERE l.datumKreiranja = :datumKreiranja")})
public class ListaZelja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idK")
    private Integer idK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumKreiranja")
    @Temporal(TemporalType.DATE)
    private Date datumKreiranja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listaZelja")
    private List<StavkaZelja> stavkaZeljaList;

    public ListaZelja() {
    }

    public ListaZelja(Integer idK) {
        this.idK = idK;
    }

    public ListaZelja(Integer idK, Date datumKreiranja) {
        this.idK = idK;
        this.datumKreiranja = datumKreiranja;
    }

    public Integer getIdK() {
        return idK;
    }

    public void setIdK(Integer idK) {
        this.idK = idK;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    @XmlTransient
    public List<StavkaZelja> getStavkaZeljaList() {
        return stavkaZeljaList;
    }

    public void setStavkaZeljaList(List<StavkaZelja> stavkaZeljaList) {
        this.stavkaZeljaList = stavkaZeljaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idK != null ? idK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaZelja)) {
            return false;
        }
        ListaZelja other = (ListaZelja) object;
        if ((this.idK == null && other.idK != null) || (this.idK != null && !this.idK.equals(other.idK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ListaZelja[ idK=" + idK + " ]";
    }
    
}
