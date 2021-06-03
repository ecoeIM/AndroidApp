package com.example.androidapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapp.data.model.Profile;
import com.example.androidapp.model.TerrariumData;
import com.example.androidapp.networking.ServiceGenerator;
import com.example.androidapp.networking.TerrariumAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ProfileRepository {
    private static ProfileRepository instance;
    private MutableLiveData<List<Profile>> profiles;
    private MutableLiveData<Integer> activeProfileId;

    private ProfileRepository() {
        this.profiles = new MutableLiveData<>();
    }

    public static synchronized ProfileRepository getInstance() {
        if (instance == null)
            instance = new ProfileRepository();
        return instance;
    }

    public MutableLiveData<List<Profile>> getProfiles() {
        return profiles;
    }

    public LiveData<Integer> getActiveProfileId() {
        return activeProfileId;
    }

    public void requestProfiles() {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<List<Profile>> call = terrariumAPI.getProfiles();
        call.enqueue(new Callback<List<Profile>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    profiles.setValue(response.body());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void addProfile(Profile newProfile) {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<Void> call = terrariumAPI.addProfile(newProfile);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    requestProfiles();
                    System.out.println("Success");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void updateProfile(Profile updatedProfile) {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<Void> call = terrariumAPI.updateProfile(updatedProfile);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    requestProfiles();
                    System.out.println("Success");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void deleteProfile(int id) {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<Void> call = terrariumAPI.deleteProfile(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    requestProfiles();
                    System.out.println("Success");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void requestActiveProfile() {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<Integer> call = terrariumAPI.getActiveProfile();
        call.enqueue(new Callback<Integer>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    activeProfileId.setValue(response.body());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void setActiveProfile(int id) {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<Void> call = terrariumAPI.setActiveProfile(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    requestProfiles();
                    System.out.println("Success");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }


}
