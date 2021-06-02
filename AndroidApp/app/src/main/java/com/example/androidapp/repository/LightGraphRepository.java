package com.example.androidapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapp.data.model.LightRecord;
import com.example.androidapp.data.model.TemperatureRecord;
import com.example.androidapp.networking.ServiceGenerator;
import com.example.androidapp.networking.TerrariumAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class LightGraphRepository {
    private static LightGraphRepository instance;
    private MutableLiveData<List<LightRecord>> lightRecords;

    private LightGraphRepository() {
        this.lightRecords = new MutableLiveData<>();
    }

    public static synchronized LightGraphRepository getInstance() {
        if (instance == null)
            instance = new LightGraphRepository();
        return instance;
    }

    public LiveData<List<LightRecord>> getLightRecords() {
        return lightRecords;
    }

    public void requestLightRecords() {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<List<LightRecord>> call = terrariumAPI.getAllLight();
        call.enqueue(new Callback<List<LightRecord>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<LightRecord>> call, Response<List<LightRecord>> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    lightRecords.setValue(response.body());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<LightRecord>> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}
