package com.example.androidapp.ui.graphs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.data.model.TemperatureRecord;
import com.example.androidapp.model.TerrariumData;
import com.example.androidapp.repository.MonitorRepository;
import com.example.androidapp.repository.TemperatureGraphRepository;

import java.util.List;

public class TemperatureGraphActivityViewModel extends ViewModel {
    private TemperatureGraphRepository temperatureGraphRepository;

    public TemperatureGraphActivityViewModel() {
        temperatureGraphRepository = TemperatureGraphRepository.getInstance();
    }

    public LiveData<List<TemperatureRecord>> getTemperatureRecords() {
        temperatureGraphRepository.requestTerrariumRecords();
        return temperatureGraphRepository.getTemperatureRecords();
    }
}
