package com.example.androidapp.Networking;

import com.example.androidapp.Model.Terrarium;
import com.example.androidapp.Model.TerrariumData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TerrariumAPI {
    @GET("api/terrarium/data?terrariumDataId=1")
    Call<TerrariumData> getTerrariumData();

    @PATCH("api/terrarium/light/state")
    Call<Boolean> changeLightStateOfTerrarium(@Query("lightState") boolean lightState, @Query("terrariumDataId") int terrariumDataId);

    @PATCH("api/terrarium/vent/state")
    Call<Boolean> changeVentStateOFTerrarium(@Query("VentState") boolean lightState, @Query("terrariumDataId") int terrariumDataId);



    @POST("terrarium/data")
    void setTerrariumData(@Body TerrariumData terrariumData);
}
