/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package requests;

import java.io.IOException;
import java.util.List;
import models.KorisnikModel;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.KorisnikService;

/**
 *
 * @author Mihajlo
 */
public class DohvatiSveKorisnike {
    
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
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            KorisnikService service = retrofit.create(KorisnikService.class);

            try {
                Call<List<KorisnikModel>> call = service.getAllKorisnik();
                Response<List<KorisnikModel>> response = call.execute();
                
                if (!response.isSuccessful()) {
                    System.out.println(response.errorBody().string() + "\n");
                    return;
                }
                
                System.out.println("idK, korisnickoIme, lozinka, ime, prezime, adresa, stanje, idG");
                for (KorisnikModel k: response.body()) {
                    System.out.println(k);
                }
                
                if (response.body().isEmpty()) {
                    System.out.println("Nema rezultata.");
                }
                System.out.println();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }
}
