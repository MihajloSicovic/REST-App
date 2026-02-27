/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Mihajlo
 */
public class ArtikalModel implements Serializable {
    int idA;
    String naziv;
    String opis;
    int cena;
    int popust;
    int idK;
    int idKat;
    
    public ArtikalModel() {}

    public ArtikalModel(int idA, String naziv, String opis, int cena, int popust, int idK, int idKat) {
        this.idA = idA;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.popust = popust;
        this.idK = idK;
        this.idKat = idKat;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getPopust() {
        return popust;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public int getIdK() {
        return idK;
    }

    public void setIdK(int idK) {
        this.idK = idK;
    }

    public int getIdKat() {
        return idKat;
    }

    public void setIdKat(int idKat) {
        this.idKat = idKat;
    }

    @Override
    public String toString() {
        return idA + ", " + naziv + ", " + opis + ", " + cena + ", " + popust 
                + ", " + idK + ", " + idKat;
    }
}
