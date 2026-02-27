/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package requests;

import java.io.IOException;
import java.util.Scanner;
import models.ArtikalModel;
import models.KorisnikModel;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import services.ArtikalService;

/**
 *
 * @author Mihajlo
 */
public class KreirajArtikal {
    
    public static void request(KorisnikModel korisnik) {
        String korisnickoIme = korisnik.getKorisnickoIme();
        String lozinka = korisnik.getLozinka();
        int idK = korisnik.getIdK();
        
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

            ArtikalService service = retrofit.create(ArtikalService.class);
            
            Scanner in = new Scanner(System.in);
            String naziv, opis;
            int cena, popust, idKat;
            
            while (true) {
                try {
                    System.out.print("Naziv: ");
                    naziv = in.nextLine();
                    System.out.print("Opis: ");
                    opis = in.nextLine();
                    System.out.print("Cena: ");
                    cena = in.nextInt();
                    in.nextLine();
                    System.out.print("Popust: ");
                    popust = in.nextInt();
                    in.nextLine();
                    System.out.print("Kateogija: ");
                    idKat = in.nextInt();
                    in.nextLine();
                    break;
                }
                catch (Exception e) {
                    System.out.println("Nevalidan podatak!\n");
                    in.nextLine();
                }
            }
            
            ArtikalModel a = new ArtikalModel();
            a.setNaziv(naziv);
            a.setOpis(opis);
            a.setCena(cena);
            a.setPopust(popust);
            a.setIdK(idK);
            a.setIdKat(idKat);
            
            try {
                Call<String> call = service.createArtikal(a);
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
}
