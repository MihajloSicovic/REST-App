/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import models.ArtikalModel;
import models.GradModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author Mihajlo
 */
public interface ArtikalService {
    @GET("artikal/{idK}")
    Call<List<ArtikalModel>> getArtikalByIdK(@Path("idK") int idK);
    
    @PUT("artikal/cena/{idA}/{cena}")
    Call<String> updateArtikalCena(
            @Path("idA") int idA, 
            @Path("cena") int cena);
    
    @PUT("artikal/popust/{idA}/{popust}")
    Call<String> updateArtikalPopust(
            @Path("idA") int idA, 
            @Path("popust") int popust);
        
    @POST("artikal")
    Call<String> createArtikal(@Body ArtikalModel a);
}
