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
public class StavkaZeljaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idK")
    private int idK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "redBr")
    private int redBr;

    public StavkaZeljaPK() {
    }

    public StavkaZeljaPK(int idK, int redBr) {
        this.idK = idK;
        this.redBr = redBr;
    }

    public int getIdK() {
        return idK;
    }

    public void setIdK(int idK) {
        this.idK = idK;
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
        hash += (int) idK;
        hash += (int) redBr;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StavkaZeljaPK)) {
            return false;
        }
        StavkaZeljaPK other = (StavkaZeljaPK) object;
        if (this.idK != other.idK) {
            return false;
        }
        if (this.redBr != other.redBr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enteties.StavkaZeljaPK[ idK=" + idK + ", redBr=" + redBr + " ]";
    }
    
}
