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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mihajlo
 */
@Entity
@Table(name = "narudzbina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Narudzbina.findAll", query = "SELECT n FROM Narudzbina n"),
    @NamedQuery(name = "Narudzbina.findByIdN", query = "SELECT n FROM Narudzbina n WHERE n.idN = :idN"),
    @NamedQuery(name = "Narudzbina.findByUkupnaCena", query = "SELECT n FROM Narudzbina n WHERE n.ukupnaCena = :ukupnaCena"),
    @NamedQuery(name = "Narudzbina.findByVremeKreiranja", query = "SELECT n FROM Narudzbina n WHERE n.vremeKreiranja = :vremeKreiranja"),
    @NamedQuery(name = "Narudzbina.findByAdresa", query = "SELECT n FROM Narudzbina n WHERE n.adresa = :adresa"),
    @NamedQuery(name = "Narudzbina.findByIdG", query = "SELECT n FROM Narudzbina n WHERE n.idG = :idG"),
    @NamedQuery(name = "Narudzbina.findByIdK", query = "SELECT n FROM Narudzbina n WHERE n.idK = :idK")})
public class Narudzbina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idN")
    private Integer idN;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ukupnaCena")
    private int ukupnaCena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremeKreiranja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremeKreiranja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "adresa")
    private String adresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idG")
    private int idG;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idK")
    private int idK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "narudzbina")
    private List<StavkaNarudzbina> stavkaNarudzbinaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idN")
    private List<Transakcija> transakcijaList;

    public Narudzbina() {
    }

    public Narudzbina(Integer idN) {
        this.idN = idN;
    }

    public Narudzbina(Integer idN, int ukupnaCena, Date vremeKreiranja, String adresa, int idG, int idK) {
        this.idN = idN;
        this.ukupnaCena = ukupnaCena;
        this.vremeKreiranja = vremeKreiranja;
        this.adresa = adresa;
        this.idG = idG;
        this.idK = idK;
    }

    public Integer getIdN() {
        return idN;
    }

    public void setIdN(Integer idN) {
        this.idN = idN;
    }

    public int getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(int ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Date getVremeKreiranja() {
        return vremeKreiranja;
    }

    public void setVremeKreiranja(Date vremeKreiranja) {
        this.vremeKreiranja = vremeKreiranja;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getIdG() {
        return idG;
    }

    public void setIdG(int idG) {
        this.idG = idG;
    }

    public int getIdK() {
        return idK;
    }

    public void setIdK(int idK) {
        this.idK = idK;
    }

    @XmlTransient
    public List<StavkaNarudzbina> getStavkaNarudzbinaList() {
        return stavkaNarudzbinaList;
    }

    public void setStavkaNarudzbinaList(List<StavkaNarudzbina> stavkaNarudzbinaList) {
        this.stavkaNarudzbinaList = stavkaNarudzbinaList;
    }

    @XmlTransient
    public List<Transakcija> getTransakcijaList() {
        return transakcijaList;
    }

    public void setTransakcijaList(List<Transakcija> transakcijaList) {
        this.transakcijaList = transakcijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idN != null ? idN.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Narudzbina)) {
            return false;
        }
        Narudzbina other = (Narudzbina) object;
        if ((this.idN == null && other.idN != null) || (this.idN != null && !this.idN.equals(other.idN))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Narudzbina[ idN=" + idN + " ]";
    }
    
}
