package com.example.androidapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapp.data.model.Co2Record;
import com.example.androidapp.data.model.TemperatureRecord;
import com.example.androidapp.networking.ServiceGenerator;
import com.example.androidapp.networking.TerrariumAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class Co2GraphRepository {
    private static Co2GraphRepository instance;
    private MutableLiveData<List<Co2Record>> co2Records;

    private Co2GraphRepository() {
        this.co2Records = new MutableLiveData<>();
    }

    public static synchronized Co2GraphRepository getInstance() {
        if (instance == null)
            instance = new Co2GraphRepository();
        return instance;
    }

    public LiveData<List<Co2Record>> getCo2Records() {
        return co2Records;
    }

    public void requestCo2Records() {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<List<Co2Record>> call = terrariumAPI.getAllCo2(1);
        call.enqueue(new Callback<List<Co2Record>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Co2Record>> call, Response<List<Co2Record>> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    co2Records.setValue(response.body());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Co2Record>> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}
