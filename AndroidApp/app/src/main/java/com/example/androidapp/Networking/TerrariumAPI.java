package com.example.androidapp.Networking;

import com.example.androidapp.Model.Terrarium;
import com.example.androidapp.Model.TerrariumData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TerrariumAPI {
    @GET("api/terrarium/data?terrariumDataId=1")
    Call<TerrariumData> getTerrariumData();

    @POST("terrarium/data")
    void setTerrariumData(@Body TerrariumData terrariumData);
}
