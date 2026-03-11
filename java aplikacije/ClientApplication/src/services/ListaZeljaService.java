/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import models.ListaZeljaModel;
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
public interface ListaZeljaService {
    @GET("listaZelja/{idK}")
    Call<List<ListaZeljaModel>> getListaZeljaByIdK(@Path("idK") int idK);
        
    @POST("listaZelja")
    Call<String> addArtikalToLista(@Body ListaZeljaModel l);
    
    @DELETE("listaZelja/{idK}/{idA}")
    Call<String> deleteArtikalFromLista(
            @Path("idK") int idK, 
            @Path("idA") int idA);
}
