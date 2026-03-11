/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import java.util.Scanner;
import models.KorisnikModel;
import requests.DodajNovacKorisniku;
import requests.DodajUKorpu;
import requests.DodajUListu;
import requests.DohvatiArtikleKorisnika;
import requests.DohvatiKorpuKorisnika;
import requests.DohvatiListuZeljaKorisnika;
import requests.DohvatiNarudzbineKorisnika;
import requests.DohvatiSveGradove;
import requests.DohvatiSveKategorije;
import requests.DohvatiSveKorisnike;
import requests.DohvatiSveNarudzbine;
import requests.DohvatiSveTransakcije;
import requests.KreirajArtikal;
import requests.KreirajGrad;
import requests.KreirajKategoriju;
import requests.KreirajKorisnika;
import requests.MenjanjeCeneArtikla;
import requests.ObrisiIzKorpe;
import requests.ObrisiIzListe;
import requests.Plati;
import requests.PostaviPopustArtikla;
import requests.PromenaAdreseKorisnika;

/**
 *
 * @author Mihajlo
 */
public class RequestForm {
    
    public static int show() {
        Scanner in = new Scanner(System.in);
        
        while (true) {
            System.out.println("Izaberite opciju:\n"
                + "\t 1 - Odjavljivanje\n"
                + "\t 2 - Kreiranje grada\n"
                + "\t 3 - Kreiranje korisnika\n"
                + "\t 4 - Dodavanje novca korisniku\n"
                + "\t 5 - Promena adrese i grada za korisnika\n"
                + "\t 6 - Kreiranje kategorije\n"
                + "\t 7 - Kreiranje artikla\n"
                + "\t 8 - Menjanje cene artikla\n"
                + "\t 9 - Postavljanje popusta za artikal\n"
                + "\t10 - Dodavanje artikala u odredjenoj kolicini u korpu\n"
                + "\t11 - Brisanje artikla u odredjenoj kolicini iz korpe\n"
                + "\t12 - Dodavanje artikla u listu zelja\n"
                + "\t13 - Brisanje artikla iz liste zelja\n"
                + "\t14 - Placanje\n"
                + "\t15 - Dohvatanje svih gradova\n"
                + "\t16 - Dohvatanje svih korisnika\n"
                + "\t17 - Dohvatanje svih kategorija\n"
                + "\t18 - Dohvatanje svih artikala koje prodaje korisnik koji je poslao zahtev\n"
                + "\t19 - Dohvatanje sadrzaja korpe korisnika koji je poslao zahtev\n"
                + "\t20 - Dohvatanje sadrzaja liste zelja korisnika koji je poslao zahtev\n"
                + "\t21 - Dohvatanje svih narudzbina korisnika koji je poslao zahtev\n"
                + "\t22 - Dohvatanje svih narudzbina\n"
                + "\t23 - Dohvatanje svih transakcija");

            try {
                int number = in.nextInt();
                in.nextLine();
                return number;
            } 
            catch (Exception e) {
                System.out.println("Opcija mora biti broj!\n");
                in.nextLine();
            }
        }
    }
    
    public static void perform(int request, KorisnikModel korisnik) {
        switch (request) {
            case 2:
                KreirajGrad.request(korisnik);
                break;
            case 3:
                KreirajKorisnika.request(korisnik);
                break;
            case 4:
                DodajNovacKorisniku.request(korisnik);
                break;
            case 5:
                PromenaAdreseKorisnika.request(korisnik);
                break;
            case 6:
                KreirajKategoriju.request(korisnik);
                break;
            case 7:
                KreirajArtikal.request(korisnik);
                break;
            case 8:
                MenjanjeCeneArtikla.request(korisnik);
                break;
            case 9:
                PostaviPopustArtikla.request(korisnik);
                break;
            case 10:
                DodajUKorpu.request(korisnik);
                break;
            case 11:
                ObrisiIzKorpe.request(korisnik);
                break;
            case 12:
                DodajUListu.request(korisnik);
                break;
            case 13:
                ObrisiIzListe.request(korisnik);
                break;
            case 14:
                Plati.request(korisnik);
                break;
            case 15:
                DohvatiSveGradove.request(korisnik);
                break;
            case 16:
                DohvatiSveKorisnike.request(korisnik);
                break;
            case 17:
                DohvatiSveKategorije.request(korisnik);
                break;
            case 18:
                DohvatiArtikleKorisnika.request(korisnik);
                break;
            case 19:
                DohvatiKorpuKorisnika.request(korisnik);
                break;
            case 20:
                DohvatiListuZeljaKorisnika.request(korisnik);
                break;
            case 21:
                DohvatiNarudzbineKorisnika.request(korisnik);
                break;
            case 22:
                DohvatiSveNarudzbine.request(korisnik);
                break;
            case 23:
                DohvatiSveTransakcije.request(korisnik);
                break;
            default:
                System.out.println("Opcija ne postoji!\n");
        }
    }
}
