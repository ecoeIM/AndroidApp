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

public class MonitorRepository {
    private static MonitorRepository instance;
    private MutableLiveData<TerrariumData> terrariumData;

    private MonitorRepository() {
        this.terrariumData = new MutableLiveData<>();
    }

    public static synchronized MonitorRepository getInstance() {
        if (instance == null)
            instance = new MonitorRepository();
        return instance;
    }

    public LiveData<TerrariumData> getTerrariumData() {
        return terrariumData;
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

}
