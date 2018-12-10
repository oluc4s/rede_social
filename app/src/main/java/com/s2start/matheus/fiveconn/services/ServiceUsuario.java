package com.s2start.matheus.fiveconn.services;

import com.s2start.matheus.fiveconn.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceUsuario {
    @POST("user/login")
    Call<Usuario> login(@Body Usuario u);

    @POST("user/registrar")
    Call<Usuario> cadastro(@Body Usuario u );

    @GET("user/seguindo/{id}")
    Call<List<Usuario>> listSeguindo(@Path("id") int id);

    @GET("user/seguidores/{id}")
    Call<List<Usuario>> listSeguidores(@Path("id") int id);
}
