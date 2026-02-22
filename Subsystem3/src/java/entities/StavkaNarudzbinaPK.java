/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mihajlo
 */
@Embeddable
public class StavkaNarudzbinaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idN")
    private int idN;
    @Basic(optional = false)
    @NotNull
    @Column(name = "redBr")
    private int redBr;

    public StavkaNarudzbinaPK() {
    }

    public StavkaNarudzbinaPK(int idN, int redBr) {
        this.idN = idN;
        this.redBr = redBr;
    }

    public int getIdN() {
        return idN;
    }

    public void setIdN(int idN) {
        this.idN = idN;
    }

    public int getRedBr() {
        return redBr;
    }

    public void setRedBr(int redBr) {
        this.redBr = redBr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idN;
        hash += (int) redBr;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StavkaNarudzbinaPK)) {
            return false;
        }
        StavkaNarudzbinaPK other = (StavkaNarudzbinaPK) object;
        if (this.idN != other.idN) {
            return false;
        }
        if (this.redBr != other.redBr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StavkaNarudzbinaPK[ idN=" + idN + ", redBr=" + redBr + " ]";
    }
    
}
