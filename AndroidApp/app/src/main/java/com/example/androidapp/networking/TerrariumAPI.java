package com.example.androidapp.networking;

import com.example.androidapp.model.TerrariumData;

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
