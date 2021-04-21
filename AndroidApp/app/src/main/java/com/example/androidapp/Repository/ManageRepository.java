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

    private ManageRepository() {
        terrariumData = new MutableLiveData<>();
    }

    public static synchronized ManageRepository getInstance() {
        if (instance == null)
            instance = new ManageRepository();
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
                if (response.isSuccessful()) {
                    terrariumData.setValue(response.body());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<TerrariumData> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}
