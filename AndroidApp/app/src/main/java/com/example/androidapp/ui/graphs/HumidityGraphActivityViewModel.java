package com.example.androidapp.ui.graphs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.data.model.HumidityRecord;
import com.example.androidapp.data.model.TemperatureRecord;
import com.example.androidapp.repository.HumidityGraphRepository;
import com.example.androidapp.repository.TemperatureGraphRepository;

import java.util.List;

public class HumidityGraphActivityViewModel extends ViewModel {
    private HumidityGraphRepository humidityGraphRepository;

    public HumidityGraphActivityViewModel() {
        humidityGraphRepository = HumidityGraphRepository.getInstance();
    }

    public LiveData<List<HumidityRecord>> getTemperatureRecords() {
        humidityGraphRepository.requestHumidityRecords();
        return humidityGraphRepository.getHumidityRecords();
    }
}
