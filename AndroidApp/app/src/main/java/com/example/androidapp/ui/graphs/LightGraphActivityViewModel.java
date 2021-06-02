package com.example.androidapp.ui.graphs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.data.model.LightRecord;
import com.example.androidapp.repository.LightGraphRepository;

import java.util.List;

public class LightGraphActivityViewModel extends ViewModel {
    private LightGraphRepository lightGraphRepository;

    public LightGraphActivityViewModel() {
        lightGraphRepository = LightGraphRepository.getInstance();
    }

    public LiveData<List<LightRecord>> getTemperatureRecords() {
        lightGraphRepository.requestLightRecords();
        return lightGraphRepository.getLightRecords();
    }
}
