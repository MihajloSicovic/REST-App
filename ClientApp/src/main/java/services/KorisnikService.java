/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.List;
import models.GradModel;
import retrofit2.http.GET;
import models.KorisnikModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author Mihajlo
 */
public interface KorisnikService {
    @GET("korisnik/{korisnickoIme}/{lozinka}")
    Call<KorisnikModel> getKorisnik(
            @Path("korisnickoIme") String korisnickoIme, 
            @Path("lozinka") String lozinka);
    
    @GET("korisnik")
    Call<List<KorisnikModel>> getAllKorisnik();
    
    @PUT("korisnik/{idK}/{dodatak}")
    Call<String> updateStanjeKorisnik(
            @Path("idK") int idK, 
            @Path("dodatak") int dodatak);
    
    @PUT("korisnik/{idK}/{idG}/{adresa}")
    Call<String> updateLocationKorisnik(
            @Path("idK") int idK, 
            @Path("idG") int idG,
            @Path("adresa") String adresa);
    
    @POST("korisnik")
    Call<String> createKorisnik(@Body KorisnikModel k);
}
