/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.io.Serializable;

/**
 *
 * @author Mihajlo
 */
public class GradView implements Serializable {
    int idG;
    String naziv;

    public GradView(int idG, String naziv) {
        this.idG = idG;
        this.naziv = naziv;
    }
    
    public int getIdG() {
        return idG;
    }

    public String getNaziv() {
        return naziv;
    }
}
