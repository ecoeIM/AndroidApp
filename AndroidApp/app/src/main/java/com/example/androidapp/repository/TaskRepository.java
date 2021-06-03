package com.example.androidapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapp.data.model.Task;
import com.example.androidapp.networking.ServiceGenerator;
import com.example.androidapp.networking.TerrariumAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class TaskRepository {
    private static TaskRepository instance;
    private MutableLiveData<List<Task>> tasks;

    private TaskRepository() {
        this.tasks = new MutableLiveData<>();
    }

    public static synchronized TaskRepository getInstance() {
        if (instance == null)
            instance = new TaskRepository();
        return instance;
    }

    public LiveData<List<Task>> getTasks() {
        return tasks;
    }

    public void requestTasks() {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<List<Task>> call = terrariumAPI.getTasks(1);
        call.enqueue(new Callback<List<Task>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    tasks.setValue(response.body());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void addTasks(Task newTask) {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<Void> call = terrariumAPI.addTask(newTask);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    System.out.println("Task added");
                    requestTasks();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.toString());
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void deleteTasks(int id) {
        TerrariumAPI terrariumAPI = ServiceGenerator.getTerrariumAPI();
        Call<Void> call = terrariumAPI.deleteTask(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    System.out.println("Task deleted");
                    requestTasks();
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
