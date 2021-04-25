package com.example.androidapp.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapp.Model.TerrariumData;
import com.example.androidapp.Networking.ServiceGenerator;
import com.example.androidapp.Networking.TerrariumAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ManageRepository {
    private static ManageRepository instance;
    private final MutableLiveData<TerrariumData> terrariumData;
    private final MutableLiveData<Boolean> terrariumLightState;
    private final MutableLiveData<Boolean> terrariumVentState;

    private ManageRepository() {
        terrariumData = new MutableLiveData<>();
        terrariumLightState = new MutableLiveData<>();
        terrariumVentState = new MutableLiveData<>();
    }

    public static synchronized ManageRepository getInstance() {
        if (instance == null)
            instance = new ManageRepository();
        return instance;
    }

    public LiveData<TerrariumData> getTerrariumData() {
        return terrariumData;
    }

    public MutableLiveData<Boolean> getTerrariumLightState() {
        return terrariumLightState;
    }

    public MutableLiveData<Boolean> getTerrariumVentState() {
        return terrariumVentState;
    }

    public void requestTerrariumData() {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<TerrariumData> call = terrariumAPI.getTerrariumData();
        call.enqueue(new Callback<TerrariumData>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<TerrariumData> call, Response<TerrariumData> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    terrariumData.setValue(response.body());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<TerrariumData> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void requestChangeTerrariumLightState(boolean newState) {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<Boolean> call = terrariumAPI.changeLightStateOfTerrarium(newState, 1);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    terrariumLightState.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void requestChangeTerrariumVentState(boolean newState) {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<Boolean> call = terrariumAPI.changeVentStateOFTerrarium(newState, 1);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    terrariumVentState.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }


}
