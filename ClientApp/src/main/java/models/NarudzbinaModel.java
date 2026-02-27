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
public class NarudzbinaModel implements Serializable {
    int idN;
    int ukupnaCena;
    Date vremeKreiranja;
    String adresa;
    int idG;
    int idK;
    
    public NarudzbinaModel() {}

    public NarudzbinaModel(int idN, int ukupnaCena, Date vremeKreiranja, String adresa, int idG, int idK) {
        this.idN = idN;
        this.ukupnaCena = ukupnaCena;
        this.vremeKreiranja = vremeKreiranja;
        this.adresa = adresa;
        this.idG = idG;
        this.idK = idK;
    }

    public int getIdN() {
        return idN;
    }

    public void setIdN(int idN) {
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

    @Override
    public String toString() {
        return idN + ", " + ukupnaCena + ", " + vremeKreiranja 
                + ", " + adresa + ", " + idG + ", " + idK;
    }
    
    
}
