/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Mihajlo
 */
public class ListaZeljaModel implements Serializable {
    int idK;
    int idA;
    Date datumDodavanja;
    
    public ListaZeljaModel() {}

    public ListaZeljaModel(int idK, int idA, Date datumDodavanja) {
        this.idK = idK;
        this.datumDodavanja = datumDodavanja;
        this.idA = idA;
    }

    public int getIdK() {
        return idK;
    }

    public void setIdK(int idK) {
        this.idK = idK;
    }

    public Date getDatumDodavanja() {
        return datumDodavanja;
    }

    public void setDatumDodavanja(Date datumDodavanja) {
        this.datumDodavanja = datumDodavanja;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    @Override
    public String toString() {
        return idK + ", " + idA + ", " + datumDodavanja;
    }
}
