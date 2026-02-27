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
public class KorpaModel implements Serializable {
    int idK;
    int redBr;
    int kolicina;
    int idA;
    
    public KorpaModel() {}

    public KorpaModel(int idK, int redBr, int kolicina, int idA) {
        this.idK = idK;
        this.redBr = redBr;
        this.kolicina = kolicina;
        this.idA = idA;
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

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    @Override
    public String toString() {
        return idK + ", " + redBr + ", " + kolicina + ", " + idA;
    }
}
