/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package requests;

import java.io.IOException;
import java.util.List;
import models.KorisnikModel;
import models.TransakcijaModel;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.TransakcijaService;

/**
 *
 * @author Mihajlo
 */
public class DohvatiSveTransakcije {
    
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

            TransakcijaService service = retrofit.create(TransakcijaService.class);

            try {
                Call<List<TransakcijaModel>> call = service.getAllTransakcija();
                Response<List<TransakcijaModel>> response = call.execute();
                
                System.out.println("idT, placenaSuma, vremePlacanja, idN");
                for (TransakcijaModel t: response.body()) {
                    System.out.println(t);
                }
                
                if (response.body().isEmpty()) {
                    System.out.println("Nema rezultata.");
                    return;
                }
                System.out.println();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }
}
