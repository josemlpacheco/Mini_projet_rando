package com.project.draggerlogin.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("/MyProjects/mini_projet/login.php")
    @FormUrlEncoded
    Call<String> loginUser(
            @Field("mail") String mail,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/MyProjects/mini_projet/mesrandonnees.php")
    Call<String> getMesRandonnees(
            @Field("mail") String mail,
            @Field("password") String password
    );

}