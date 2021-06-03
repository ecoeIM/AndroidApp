package com.example.androidapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapp.data.model.TemperatureRecord;
import com.example.androidapp.networking.ServiceGenerator;
import com.example.androidapp.networking.TerrariumAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class TemperatureGraphRepository {
    private static TemperatureGraphRepository instance;
    private MutableLiveData<List<TemperatureRecord>> temperatureRecords;

    private TemperatureGraphRepository() {
        this.temperatureRecords = new MutableLiveData<>();
    }

    public static synchronized TemperatureGraphRepository getInstance() {
        if (instance == null)
            instance = new TemperatureGraphRepository();
        return instance;
    }

    public LiveData<List<TemperatureRecord>> getTemperatureRecords() {
        return temperatureRecords;
    }

    public void requestTerrariumRecords() {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<List<TemperatureRecord>> call = terrariumAPI.getAllTemperature(1);
        call.enqueue(new Callback<List<TemperatureRecord>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<TemperatureRecord>> call, Response<List<TemperatureRecord>> response) {
                System.out.println(response.code());
                System.out.println(response.body());
                System.out.println("ABOBAOBABOBA");
                if (response.isSuccessful()) {
                    temperatureRecords.setValue(response.body());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<TemperatureRecord>> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}
