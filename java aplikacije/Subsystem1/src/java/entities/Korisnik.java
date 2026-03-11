/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mihajlo
 */
@Entity
@Table(name = "korisnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k"),
    @NamedQuery(name = "Korisnik.findByIdK", query = "SELECT k FROM Korisnik k WHERE k.idK = :idK"),
    @NamedQuery(name = "Korisnik.findByKorisnickoIme", query = "SELECT k FROM Korisnik k WHERE k.korisnickoIme = :korisnickoIme"),
    @NamedQuery(name = "Korisnik.findByLozinka", query = "SELECT k FROM Korisnik k WHERE k.lozinka = :lozinka"),
    @NamedQuery(name = "Korisnik.findByIme", query = "SELECT k FROM Korisnik k WHERE k.ime = :ime"),
    @NamedQuery(name = "Korisnik.findByPrezime", query = "SELECT k FROM Korisnik k WHERE k.prezime = :prezime"),
    @NamedQuery(name = "Korisnik.findByAdresa", query = "SELECT k FROM Korisnik k WHERE k.adresa = :adresa"),
    @NamedQuery(name = "Korisnik.findByStanje", query = "SELECT k FROM Korisnik k WHERE k.stanje = :stanje")})
public class Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idK")
    private Integer idK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "korisnickoIme")
    private String korisnickoIme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lozinka")
    private String lozinka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "prezime")
    private String prezime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "adresa")
    private String adresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stanje")
    private int stanje;
    @JoinTable(name = "ima_ulogu", joinColumns = {
        @JoinColumn(name = "idK", referencedColumnName = "idK")}, inverseJoinColumns = {
        @JoinColumn(name = "idU", referencedColumnName = "idU")})
    @ManyToMany
    private List<Uloga> ulogaList;
    @JoinColumn(name = "grad", referencedColumnName = "idG")
    @ManyToOne(optional = false)
    private Grad grad;

    public Korisnik() {
    }

    public Korisnik(Integer idK) {
        this.idK = idK;
    }

    public Korisnik(Integer idK, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, int stanje) {
        this.idK = idK;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.stanje = stanje;
    }

    public Integer getIdK() {
        return idK;
    }

    public void setIdK(Integer idK) {
        this.idK = idK;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getStanje() {
        return stanje;
    }

    public void setStanje(int stanje) {
        this.stanje = stanje;
    }

    @XmlTransient
    public List<Uloga> getUlogaList() {
        return ulogaList;
    }

    public void setUlogaList(List<Uloga> ulogaList) {
        this.ulogaList = ulogaList;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
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
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.idK == null && other.idK != null) || (this.idK != null && !this.idK.equals(other.idK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idK + ", " + korisnickoIme + ", " + lozinka + ", "+ ime + ", " 
                + prezime + ", " + adresa + ", " + stanje + ", " 
                + grad.getNaziv();
    }
    
}
