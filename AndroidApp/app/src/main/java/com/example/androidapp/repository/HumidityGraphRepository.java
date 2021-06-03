package com.example.androidapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapp.data.model.HumidityRecord;
import com.example.androidapp.data.model.TemperatureRecord;
import com.example.androidapp.networking.ServiceGenerator;
import com.example.androidapp.networking.TerrariumAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class HumidityGraphRepository {
    private static HumidityGraphRepository instance;
    private MutableLiveData<List<HumidityRecord>> humidityRecords;

    private HumidityGraphRepository() {
        this.humidityRecords = new MutableLiveData<>();
    }

    public static synchronized HumidityGraphRepository getInstance() {
        if (instance == null)
            instance = new HumidityGraphRepository();
        return instance;
    }

    public LiveData<List<HumidityRecord>> getHumidityRecords() {
        return humidityRecords;
    }

    public void requestHumidityRecords() {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<List<HumidityRecord>> call = terrariumAPI.getAllHumidity(1);
        call.enqueue(new Callback<List<HumidityRecord>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<HumidityRecord>> call, Response<List<HumidityRecord>> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    humidityRecords.setValue(response.body());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<HumidityRecord>> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}
