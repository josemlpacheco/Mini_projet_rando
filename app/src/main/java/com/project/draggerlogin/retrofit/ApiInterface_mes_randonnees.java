package com.project.draggerlogin.retrofit;

import com.project.draggerlogin.ui.home.Randonnee;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface_mes_randonnees {
    @FormUrlEncoded
    @POST("/MyProjects/mini_projet/mesrandonnees.php")
    Call<List<Randonnee>> getMesRandonnees(
            @Field("mail") String mail,
            @Field("password") String password
    );
}
