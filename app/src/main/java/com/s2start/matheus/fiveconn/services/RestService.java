package com.s2start.matheus.fiveconn.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    private Retrofit adapter;

    public RestService(String url){

        Gson gson = new GsonBuilder().setLenient().create();

        adapter = new Retrofit.Builder().
                baseUrl(url).
                addConverterFactory(GsonConverterFactory.create(gson)).
                build();
    }

    public <T> T getService(Class<T> c){
        return adapter.create(c);
    }

}
