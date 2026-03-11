/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import models.NarudzbinaModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 * @author Mihajlo
 */
public interface NarudzbinaService {
    @GET("narudzbina")
    Call<List<NarudzbinaModel>> getAllNarudzbina();
    
    @GET("narudzbina/{idK}")
    Call<List<NarudzbinaModel>> getNarudzbinaByIdK(@Path("idK") int idK);
}
