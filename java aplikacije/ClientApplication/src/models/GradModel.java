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
public class GradModel implements Serializable {
    int idG;
    String naziv;

    public GradModel() {}
            
    public GradModel(int idG, String naziv) {
        this.idG = idG;
        this.naziv = naziv;
    }

    public int getIdG() {
        return idG;
    }

    public void setIdG(int idG) {
        this.idG = idG;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    @Override
    public String toString() {
        return idG + ", " + naziv;
    }
    
    
}
