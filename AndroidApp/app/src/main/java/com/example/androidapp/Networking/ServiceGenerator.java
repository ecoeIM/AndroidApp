package com.example.androidapp.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static TerrariumAPI terrariumAPI;

    public static TerrariumAPI getTerrariumAPI() {
        if (terrariumAPI == null) {
            terrariumAPI = new Retrofit.Builder()
                    .baseUrl("https://10.0.2.2:5001/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TerrariumAPI.class);
        }
        return terrariumAPI;
    }
}
