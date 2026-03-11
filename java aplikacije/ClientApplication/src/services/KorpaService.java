/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import models.KorpaModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 *
 * @author Mihajlo
 */
public interface KorpaService {
    @GET("korpa/{idK}")
    Call<List<KorpaModel>> getKorpaByIdK(@Path("idK") int idK);
    
    @POST("korpa")
    Call<String> addArtikalToKorpa(@Body KorpaModel k);
    
    @DELETE("korpa/{idK}/{redBr}")
    Call<String> deleteArtikalFromKorpa(
            @Path("idK") int idK, 
            @Path("redBr") int redBr);
}
