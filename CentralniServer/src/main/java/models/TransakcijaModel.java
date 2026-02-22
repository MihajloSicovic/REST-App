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
public class TransakcijaModel implements Serializable {
    int idT;
    int placenaSuma;
    Date vremePlacanja;
    int idN;

    public TransakcijaModel() {}
            
    public TransakcijaModel(int idT, int placenaSuma, Date vremePlacanja, int idN) {
        this.idT = idT;
        this.placenaSuma = placenaSuma;
        this.vremePlacanja = vremePlacanja;
        this.idN = idN;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
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

    public int getIdN() {
        return idN;
    }

    public void setIdN(int idN) {
        this.idN = idN;
    }

    @Override
    public String toString() {
        return idT + ", " + placenaSuma + ", " + vremePlacanja + ", " + idN;
    }
    
    
}
