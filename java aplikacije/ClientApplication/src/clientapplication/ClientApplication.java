/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clientapplication;

import forms.LoginForm;
import forms.RequestForm;
import java.util.Scanner;
import models.KorisnikModel;

/**
 *
 * @author Mihajlo
 */
public class ClientApplication {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        while (true) {
            KorisnikModel korisnik = LoginForm.login();
            while (true) {
                int request = RequestForm.show();
                if (request == 1) break;
                RequestForm.perform(request, korisnik);
                System.out.println("Pritisnite <enter> da nastavite...");
                in.nextLine();
            }
        }
    }
}
