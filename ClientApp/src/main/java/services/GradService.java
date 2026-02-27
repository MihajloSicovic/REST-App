/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import models.GradModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 *
 * @author Mihajlo
 */
public interface GradService {
    @GET("grad")
    Call<List<GradModel>> getAllGrad();
    
    @POST("grad")
    Call<String> createGrad(@Body GradModel g);
}
