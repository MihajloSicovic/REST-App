/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.KorisnikModel;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import services.KorisnikService;

/**
 *
 * @author Mihajlo
 */
public class KreirajKorisnika {
    
    public static void request(KorisnikModel korisnik) {
        String korisnickoIme = korisnik.getKorisnickoIme();
        String lozinka = korisnik.getLozinka();
        
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    String credential = Credentials.basic(korisnickoIme, lozinka);

                    Request request = original.newBuilder()
                            .header("Authorization", credential)
                            .build();

                    return chain.proceed(request);
                })
                .build();

            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/CentralniServer/api/")
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())  
                .addConverterFactory(GsonConverterFactory.create())     
                .build();

            KorisnikService service = retrofit.create(KorisnikService.class);
            
            Scanner in = new Scanner(System.in);
            String korIme, loz, ime, prezime, adresa;
            int stanje, idG;
            List<Integer> idU;
            
            while (true) {
                try {
                    System.out.print("Korisnicko ime: ");
                    korIme = in.nextLine();
                    System.out.print("Lozinka: ");
                    loz = in.nextLine();
                    System.out.print("Ime: ");
                    ime = in.nextLine();
                    System.out.print("Prezime: ");
                    prezime = in.nextLine();
                    System.out.print("Adresa: ");
                    adresa = in.nextLine();
                    System.out.print("Stanje: ");
                    stanje = in.nextInt();
                    in.nextLine();
                    System.out.print("ID Grada: ");
                    idG = in.nextInt();
                    in.nextLine();
                    System.out.print("Uloge: ");
                    idU = loadList(in);
                    break;
                }
                catch (Exception e) {
                    System.out.println("Nevalidan podatak!\n");
                    in.nextLine();
                }
            }
            
            KorisnikModel k = new KorisnikModel();
            k.setKorisnickoIme(korIme);
            k.setLozinka(loz);
            k.setIme(ime);
            k.setPrezime(prezime);
            k.setAdresa(adresa);
            k.setStanje(stanje);
            k.setIdG(idG);
            k.setIdU(idU);
            
            try {
                Call<String> call = service.createKorisnik(k);
                Response<String> response = call.execute();
                
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody().string() + "\n");
                    return;
                }
                
                System.out.println(response.body());
                System.out.println();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }
    
    private static List<Integer> loadList(Scanner in) {
        List<Integer> numbers = new ArrayList<>();
        
        String line = in.nextLine();
        Scanner lineScanner = new Scanner(line);
        
        while (lineScanner.hasNextInt()) {
            numbers.add(lineScanner.nextInt());
        }
        
        return numbers;
    }
}
