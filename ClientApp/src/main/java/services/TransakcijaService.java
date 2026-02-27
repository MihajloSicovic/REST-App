/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import models.KorisnikModel;
import models.TransakcijaModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 *
 * @author Mihajlo
 */
public interface TransakcijaService {
    @GET("transakcija")
    Call<List<TransakcijaModel>> getAllTransakcija();
    
    @POST("transakcija")
    Call<String> createTransakcija(@Body KorisnikModel k);
}
