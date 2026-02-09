/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "stavka_zelja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StavkaZelja.findAll", query = "SELECT s FROM StavkaZelja s"),
    @NamedQuery(name = "StavkaZelja.findByIdK", query = "SELECT s FROM StavkaZelja s WHERE s.stavkaZeljaPK.idK = :idK"),
    @NamedQuery(name = "StavkaZelja.findByRedBr", query = "SELECT s FROM StavkaZelja s WHERE s.stavkaZeljaPK.redBr = :redBr"),
    @NamedQuery(name = "StavkaZelja.findByDatumDodavanja", query = "SELECT s FROM StavkaZelja s WHERE s.datumDodavanja = :datumDodavanja")})
public class StavkaZelja implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StavkaZeljaPK stavkaZeljaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumDodavanja")
    @Temporal(TemporalType.DATE)
    private Date datumDodavanja;
    @JoinColumn(name = "idA", referencedColumnName = "idA")
    @ManyToOne(optional = false)
    private Artikal idA;
    @JoinColumn(name = "idK", referencedColumnName = "idK", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ListaZelja listaZelja;

    public StavkaZelja() {
    }

    public StavkaZelja(StavkaZeljaPK stavkaZeljaPK) {
        this.stavkaZeljaPK = stavkaZeljaPK;
    }

    public StavkaZelja(StavkaZeljaPK stavkaZeljaPK, Date datumDodavanja) {
        this.stavkaZeljaPK = stavkaZeljaPK;
        this.datumDodavanja = datumDodavanja;
    }

    public StavkaZelja(int idK, int redBr) {
        this.stavkaZeljaPK = new StavkaZeljaPK(idK, redBr);
    }

    public StavkaZeljaPK getStavkaZeljaPK() {
        return stavkaZeljaPK;
    }

    public void setStavkaZeljaPK(StavkaZeljaPK stavkaZeljaPK) {
        this.stavkaZeljaPK = stavkaZeljaPK;
    }

    public Date getDatumDodavanja() {
        return datumDodavanja;
    }

    public void setDatumDodavanja(Date datumDodavanja) {
        this.datumDodavanja = datumDodavanja;
    }

    public Artikal getIdA() {
        return idA;
    }

    public void setIdA(Artikal idA) {
        this.idA = idA;
    }

    public ListaZelja getListaZelja() {
        return listaZelja;
    }

    public void setListaZelja(ListaZelja listaZelja) {
        this.listaZelja = listaZelja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stavkaZeljaPK != null ? stavkaZeljaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StavkaZelja)) {
            return false;
        }
        StavkaZelja other = (StavkaZelja) object;
        if ((this.stavkaZeljaPK == null && other.stavkaZeljaPK != null) || (this.stavkaZeljaPK != null && !this.stavkaZeljaPK.equals(other.stavkaZeljaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enteties.StavkaZelja[ stavkaZeljaPK=" + stavkaZeljaPK + " ]";
    }
    
}
