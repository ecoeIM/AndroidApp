package com.example.androidapp.networking;

import com.example.androidapp.data.model.Co2Record;
import com.example.androidapp.data.model.HumidityRecord;
import com.example.androidapp.data.model.LightRecord;
import com.example.androidapp.data.model.Profile;
import com.example.androidapp.data.model.Task;
import com.example.androidapp.data.model.TemperatureRecord;
import com.example.androidapp.data.model.TerrariumData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    //new ones
    @POST("api/terrarium/setUser")
    void setUserId(@Body String UId);

    @GET("api/terrarium/temperature/period/last")
    Call<Double> getLastTemperature();

    @GET("api/terrarium/humidity/period/last")
    Call<Double> getLastHumidity();

    @GET("api/terrarium/co2/period/last")
    Call<Double> getLastCo2();

    @GET("api/terrarium/light/period/last")
    Call<Double> getLastLight();

    @GET("api/terrarium/temp/records")
    Call<List<TemperatureRecord>> getAllTemperature(@Query("terrariumDataId") int terrariumDataId);

    @GET("api/terrarium/hum/records")
    Call<List<HumidityRecord>> getAllHumidity(@Query("terrariumDataId") int terrariumDataId);

    @GET("api/terrarium/carbon/records")
    Call<List<Co2Record>> getAllCo2(@Query("terrariumDataId") int terrariumDataId);

    @GET("api/terrarium/light/records")
    Call<List<LightRecord>> getAllLight(@Query("terrariumDataId") int terrariumDataId);

    @GET("api/terrarium/artLight/state")
    Call<Boolean> getArtificialLightState();

    @GET("api/terrarium/vent/state")
    Call<Boolean> getVentState();

    @GET("api/terrarium/tasks")
    Call<Task> getTasks(@Query("terrariumId") int terrariumId);

    @POST("api/terrarium/tasks")
    void addTask(@Body Task task);

    @DELETE("api/terrarium/tasks")
    void deleteTask(@Query("id") int taskId);

    @GET("api/terrarium/profiles")
    Call<List<Profile>> getProfiles(@Query("terrariumId") int terrariumId);

    @POST("api/terrarium/profiles")
    Call<Void> addProfile(@Body Profile profile);

    @PATCH("api/terrarium/profiles")
    Call<Void> updateProfile(@Body Profile profile);

    @DELETE("api/terrarium/profiles")
    Call<Void> deleteProfile(@Query("id") int id);

    @GET("api/terrarium/profiles/active")
    Call<Profile> getActiveProfile(@Query("terrariumId") int terrariumId);

    @PATCH("api/terrarium/profiles/active")
    Call<Void> setActiveProfile(@Query("terrariumId") int terrariumId, @Query("profileId") int profileId);
}
