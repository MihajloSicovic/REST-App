/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mihajlo
 */
public class KorisnikModel implements Serializable {
    int idK;
    String korisnickoIme;
    String lozinka;
    String ime;
    String prezime;
    String adresa;
    int stanje;
    int idG;
    List<Integer> idU;

    public KorisnikModel() {}    
    
    public KorisnikModel(int idK, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, int stanje, int idG, List<Integer> idU) {
        this.idK = idK;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.stanje = stanje;
        this.idG = idG;
        this.idU = idU;
    }

    public int getIdK() {
        return idK;
    }

    public void setIdK(int idK) {
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

    public int getIdG() {
        return idG;
    }

    public void setIdG(int idG) {
        this.idG = idG;
    }

    public List<Integer> getIdU() {
        return idU;
    }

    public void setIdU(List<Integer> idU) {
        this.idU = idU;
    }

    
    
    @Override
    public String toString() {
        return idK + ", " + korisnickoIme + ", " + lozinka + ", " + ime 
                + ", " + prezime + ", " + adresa + ", " + stanje + ", " 
                + idG + ", " + idU;
    }
    
    
}

