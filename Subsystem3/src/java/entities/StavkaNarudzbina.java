/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mihajlo
 */
@Entity
@Table(name = "stavka_narudzbina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StavkaNarudzbina.findAll", query = "SELECT s FROM StavkaNarudzbina s"),
    @NamedQuery(name = "StavkaNarudzbina.findByIdN", query = "SELECT s FROM StavkaNarudzbina s WHERE s.stavkaNarudzbinaPK.idN = :idN"),
    @NamedQuery(name = "StavkaNarudzbina.findByRedBr", query = "SELECT s FROM StavkaNarudzbina s WHERE s.stavkaNarudzbinaPK.redBr = :redBr"),
    @NamedQuery(name = "StavkaNarudzbina.findByIdA", query = "SELECT s FROM StavkaNarudzbina s WHERE s.idA = :idA"),
    @NamedQuery(name = "StavkaNarudzbina.findByKolicina", query = "SELECT s FROM StavkaNarudzbina s WHERE s.kolicina = :kolicina"),
    @NamedQuery(name = "StavkaNarudzbina.findByJedinicnaCena", query = "SELECT s FROM StavkaNarudzbina s WHERE s.jedinicnaCena = :jedinicnaCena")})
public class StavkaNarudzbina implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StavkaNarudzbinaPK stavkaNarudzbinaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idA")
    private int idA;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kolicina")
    private int kolicina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jedinicnaCena")
    private int jedinicnaCena;
    @JoinColumn(name = "idN", referencedColumnName = "idN", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Narudzbina narudzbina;

    public StavkaNarudzbina() {
    }

    public StavkaNarudzbina(StavkaNarudzbinaPK stavkaNarudzbinaPK) {
        this.stavkaNarudzbinaPK = stavkaNarudzbinaPK;
    }

    public StavkaNarudzbina(StavkaNarudzbinaPK stavkaNarudzbinaPK, int idA, int kolicina, int jedinicnaCena) {
        this.stavkaNarudzbinaPK = stavkaNarudzbinaPK;
        this.idA = idA;
        this.kolicina = kolicina;
        this.jedinicnaCena = jedinicnaCena;
    }

    public StavkaNarudzbina(int idN, int redBr) {
        this.stavkaNarudzbinaPK = new StavkaNarudzbinaPK(idN, redBr);
    }

    public StavkaNarudzbinaPK getStavkaNarudzbinaPK() {
        return stavkaNarudzbinaPK;
    }

    public void setStavkaNarudzbinaPK(StavkaNarudzbinaPK stavkaNarudzbinaPK) {
        this.stavkaNarudzbinaPK = stavkaNarudzbinaPK;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getJedinicnaCena() {
        return jedinicnaCena;
    }

    public void setJedinicnaCena(int jedinicnaCena) {
        this.jedinicnaCena = jedinicnaCena;
    }

    public Narudzbina getNarudzbina() {
        return narudzbina;
    }

    public void setNarudzbina(Narudzbina narudzbina) {
        this.narudzbina = narudzbina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stavkaNarudzbinaPK != null ? stavkaNarudzbinaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StavkaNarudzbina)) {
            return false;
        }
        StavkaNarudzbina other = (StavkaNarudzbina) object;
        if ((this.stavkaNarudzbinaPK == null && other.stavkaNarudzbinaPK != null) || (this.stavkaNarudzbinaPK != null && !this.stavkaNarudzbinaPK.equals(other.stavkaNarudzbinaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StavkaNarudzbina[ stavkaNarudzbinaPK=" + stavkaNarudzbinaPK + " ]";
    }
    
}
