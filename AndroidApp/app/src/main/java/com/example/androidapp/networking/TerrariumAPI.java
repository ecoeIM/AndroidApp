package com.example.androidapp.networking;

import androidx.room.Delete;

import com.example.androidapp.data.model.Co2Record;
import com.example.androidapp.data.model.HumidityRecord;
import com.example.androidapp.data.model.LightRecord;
import com.example.androidapp.data.model.Task;
import com.example.androidapp.data.model.TemperatureRecord;
import com.example.androidapp.model.Profile;
import com.example.androidapp.model.TerrariumData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
    @POST("api/terrarium/setUser/{id}")
    void setUserId(@Path("id") String id);

    @GET("api/terrarium/temperature?period=last")
    Call<Double> getLastTemperature();

    @GET("api/terrarium/humidity?period=last")
    Call<Double> getLastHumidity();

    @GET("api/terrarium/co2?period=last")
    Call<Double> getLastCo2();

    @GET("api/terrarium/light?period=last")
    Call<Double> getLastLight();

    @GET("api/terrarium/temperature?period=all")
    Call<List<TemperatureRecord>> getAllTemperature();

    @GET("api/terrarium/humidity?period=all")
    Call<List<HumidityRecord>> getAllHumidity();

    @GET("api/terrarium/co2?period=all")
    Call<List<Co2Record>> getAllCo2();

    @GET("api/terrarium/light?period=all")
    Call<List<LightRecord>> getAllLight();

    @GET("api/terrarium/artLight/state")
    Call<Boolean> getArtificialLightState();

    @GET("api/terrarium/vent/state")
    Call<Boolean> getVentState();

    @PATCH("api/terrarium/artLight/toggle")
    Call<Boolean> toggleLightState();

    @PATCH("api/terrarium/vent/toggle")
    Call<Boolean> toggleVentState();

    @GET("api/terrarium/tasks")
    Call<Task> getTasks();

    @POST("api/terrarium/tasks")
    void addTask(@Body Task task);

    @DELETE("api/terrarium/tasks/{id}")
    void deleteTask(@Path("id") int id);

    @GET("api/terrarium/profiles")
    Call<Profile> getProfiles(@Query("userId") String id);

    @POST("api/terrarium/profiles")
    Call<Integer> addProfile(@Body Profile profile, @Query("userId") String id);

    @PATCH("api/terrarium/profiles")
    void updateProfile(@Body Profile profile, @Query("userId") String id);

    @DELETE("api/terrarium/profiles/{id}")
    void deleteProfile(@Path("id") int id, @Query("userId") String userId);

    @GET("api/terrarium/profiles/active")
    Call<Profile> getActiveProfile(@Query("userId") String id);

    @PATCH("api/terrarium/profiles/active")
    void setActiveProfile(@Body Profile profile, @Query("userId") String id);
}
