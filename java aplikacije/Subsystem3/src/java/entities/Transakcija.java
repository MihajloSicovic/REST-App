/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mihajlo
 */
@Entity
@Table(name = "transakcija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transakcija.findAll", query = "SELECT t FROM Transakcija t"),
    @NamedQuery(name = "Transakcija.findByIdT", query = "SELECT t FROM Transakcija t WHERE t.idT = :idT"),
    @NamedQuery(name = "Transakcija.findByPlacenaSuma", query = "SELECT t FROM Transakcija t WHERE t.placenaSuma = :placenaSuma"),
    @NamedQuery(name = "Transakcija.findByVremePlacanja", query = "SELECT t FROM Transakcija t WHERE t.vremePlacanja = :vremePlacanja")})
public class Transakcija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idT")
    private Integer idT;
    @Basic(optional = false)
    @NotNull
    @Column(name = "placenaSuma")
    private int placenaSuma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremePlacanja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremePlacanja;
    @JoinColumn(name = "idN", referencedColumnName = "idN")
    @ManyToOne(optional = false)
    private Narudzbina idN;

    public Transakcija() {
    }

    public Transakcija(Integer idT) {
        this.idT = idT;
    }

    public Transakcija(Integer idT, int placenaSuma, Date vremePlacanja) {
        this.idT = idT;
        this.placenaSuma = placenaSuma;
        this.vremePlacanja = vremePlacanja;
    }

    public Integer getIdT() {
        return idT;
    }

    public void setIdT(Integer idT) {
        this.idT = idT;
    }

    public int getPlacenaSuma() {
        return placenaSuma;
    }

    public void setPlacenaSuma(int placenaSuma) {
        this.placenaSuma = placenaSuma;
    }

    public Date getVremePlacanja() {
        return vremePlacanja;
    }

    public void setVremePlacanja(Date vremePlacanja) {
        this.vremePlacanja = vremePlacanja;
    }

    public Narudzbina getIdN() {
        return idN;
    }

    public void setIdN(Narudzbina idN) {
        this.idN = idN;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idT != null ? idT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transakcija)) {
            return false;
        }
        Transakcija other = (Transakcija) object;
        if ((this.idT == null && other.idT != null) || (this.idT != null && !this.idT.equals(other.idT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Transakcija[ idT=" + idT + " ]";
    }
    
}
