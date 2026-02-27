/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package requests;

import java.io.IOException;
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
public class AzurirajKorisnika {

    public static KorisnikModel request(KorisnikModel korisnik) {
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
            Call<KorisnikModel> call = service.getKorisnik(korisnickoIme, lozinka);
            Response<KorisnikModel> response = call.execute();

            if (response.isSuccessful()) return response.body();
            else System.out.println(response.errorBody().string() + "\n");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    
}
