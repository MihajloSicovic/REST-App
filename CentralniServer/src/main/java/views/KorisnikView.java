/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mihajlo
 */
public class KorisnikView implements Serializable {
    int idK;
    String korisnickoIme;
    String lozinka;
    String ime;
    String prezime;
    String adresa;
    int stanje;
    int idG;
    List<Integer> idU;

    public KorisnikView(int idK, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, int stanje, int idG, List<Integer> idU) {
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public int getStanje() {
        return stanje;
    }

    public int getIdG() {
        return idG;
    }

    public List<Integer> getIdU() {
        return idU;
    }
}
