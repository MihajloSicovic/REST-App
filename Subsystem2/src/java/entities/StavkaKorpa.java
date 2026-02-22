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
@Table(name = "stavka_korpa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StavkaKorpa.findAll", query = "SELECT s FROM StavkaKorpa s"),
    @NamedQuery(name = "StavkaKorpa.findByIdK", query = "SELECT s FROM StavkaKorpa s WHERE s.stavkaKorpaPK.idK = :idK"),
    @NamedQuery(name = "StavkaKorpa.findByRedBr", query = "SELECT s FROM StavkaKorpa s WHERE s.stavkaKorpaPK.redBr = :redBr"),
    @NamedQuery(name = "StavkaKorpa.findByKolicina", query = "SELECT s FROM StavkaKorpa s WHERE s.kolicina = :kolicina")})
public class StavkaKorpa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StavkaKorpaPK stavkaKorpaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kolicina")
    private int kolicina;
    @JoinColumn(name = "idA", referencedColumnName = "idA")
    @ManyToOne(optional = false)
    private Artikal idA;
    @JoinColumn(name = "idK", referencedColumnName = "idK", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Korpa korpa;

    public StavkaKorpa() {
    }

    public StavkaKorpa(StavkaKorpaPK stavkaKorpaPK) {
        this.stavkaKorpaPK = stavkaKorpaPK;
    }

    public StavkaKorpa(StavkaKorpaPK stavkaKorpaPK, int kolicina) {
        this.stavkaKorpaPK = stavkaKorpaPK;
        this.kolicina = kolicina;
    }

    public StavkaKorpa(int idK, int redBr) {
        this.stavkaKorpaPK = new StavkaKorpaPK(idK, redBr);
    }

    public StavkaKorpaPK getStavkaKorpaPK() {
        return stavkaKorpaPK;
    }

    public void setStavkaKorpaPK(StavkaKorpaPK stavkaKorpaPK) {
        this.stavkaKorpaPK = stavkaKorpaPK;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Artikal getIdA() {
        return idA;
    }

    public void setIdA(Artikal idA) {
        this.idA = idA;
    }

    public Korpa getKorpa() {
        return korpa;
    }

    public void setKorpa(Korpa korpa) {
        this.korpa = korpa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stavkaKorpaPK != null ? stavkaKorpaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StavkaKorpa)) {
            return false;
        }
        StavkaKorpa other = (StavkaKorpa) object;
        if ((this.stavkaKorpaPK == null && other.stavkaKorpaPK != null) || (this.stavkaKorpaPK != null && !this.stavkaKorpaPK.equals(other.stavkaKorpaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StavkaKorpa[ stavkaKorpaPK=" + stavkaKorpaPK + " ]";
    }
    
}
