/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package requests;

import java.io.IOException;
import java.util.Scanner;
import models.KategorijaModel;
import models.KorisnikModel;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import services.KategorijaService;

/**
 *
 * @author Mihajlo
 */
public class KreirajKategoriju {
    
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

            KategorijaService service = retrofit.create(KategorijaService.class);
            
            Scanner in = new Scanner(System.in);
            String naziv;
            int idPotKat;
            
            while (true) {
                try {
                    System.out.print("Naziv: ");
                    naziv = in.nextLine();
                    System.out.print("ID Potkategorije: ");
                    idPotKat = in.nextInt();
                    in.nextLine();
                    break;
                }
                catch (Exception e) {
                    System.out.println("Nevalidan podatak!\n");
                    in.nextLine();
                }
            }
            
            KategorijaModel k = new KategorijaModel();
            k.setNaziv(naziv);
            k.setIdPotKat(idPotKat);
            
            try {
                Call<String> call = service.createKategorija(k);
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
