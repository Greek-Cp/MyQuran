package com.nicomot.myquran.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {

    private Retrofit mRetroFitClient;
    public RetroFitClient(String baseURL){
        mRetroFitClient = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public Retrofit getInstance(){
        return this.mRetroFitClient;
    }
}
