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
public class KategorijaModel implements Serializable {
    int idKat;
    String naziv;
    int idPotKat;
    
    public KategorijaModel() {}

    public KategorijaModel(int idKat, String naziv, int idPotKat) {
        this.idKat = idKat;
        this.naziv = naziv;
        this.idPotKat = idPotKat;
    }

    public int getIdKat() {
        return idKat;
    }

    public void setIdKat(int idKat) {
        this.idKat = idKat;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getIdPotKat() {
        return idPotKat;
    }

    public void setIdPotKat(int idPotKat) {
        this.idPotKat = idPotKat;
    }

    @Override
    public String toString() {
        return idKat + ", " + naziv + ", " + idPotKat;
    }
}
