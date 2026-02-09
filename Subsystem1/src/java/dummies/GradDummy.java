/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dummies;

import java.io.Serializable;

/**
 *
 * @author Mihajlo
 */
public class GradDummy implements Serializable {
    String naziv;
    
    public GradDummy() {}

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
