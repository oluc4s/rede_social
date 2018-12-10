package com.s2start.matheus.fiveconn.services;

import com.s2start.matheus.fiveconn.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServicePost {
    @GET("post/lista/{id}")
    Call<List<Post>> listarPostSeguindo(@Path("id") int id);

    @POST("post/inserir")
    Call<Post> postar(@Body Post p);

    @GET("post/me/{id}")
    Call<List<Post>> meusposts(@Path("id") int id);

}
