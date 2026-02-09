/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mihajlo
 */
@Entity
@Table(name = "korpa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korpa.findAll", query = "SELECT k FROM Korpa k"),
    @NamedQuery(name = "Korpa.findByIdK", query = "SELECT k FROM Korpa k WHERE k.idK = :idK"),
    @NamedQuery(name = "Korpa.findByUkupnaCena", query = "SELECT k FROM Korpa k WHERE k.ukupnaCena = :ukupnaCena")})
public class Korpa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idK")
    private Integer idK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ukupnaCena")
    private int ukupnaCena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "korpa")
    private List<StavkaKorpa> stavkaKorpaList;

    public Korpa() {
    }

    public Korpa(Integer idK) {
        this.idK = idK;
    }

    public Korpa(Integer idK, int ukupnaCena) {
        this.idK = idK;
        this.ukupnaCena = ukupnaCena;
    }

    public Integer getIdK() {
        return idK;
    }

    public void setIdK(Integer idK) {
        this.idK = idK;
    }

    public int getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(int ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    @XmlTransient
    public List<StavkaKorpa> getStavkaKorpaList() {
        return stavkaKorpaList;
    }

    public void setStavkaKorpaList(List<StavkaKorpa> stavkaKorpaList) {
        this.stavkaKorpaList = stavkaKorpaList;
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
        if (!(object instanceof Korpa)) {
            return false;
        }
        Korpa other = (Korpa) object;
        if ((this.idK == null && other.idK != null) || (this.idK != null && !this.idK.equals(other.idK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enteties.Korpa[ idK=" + idK + " ]";
    }
    
}
