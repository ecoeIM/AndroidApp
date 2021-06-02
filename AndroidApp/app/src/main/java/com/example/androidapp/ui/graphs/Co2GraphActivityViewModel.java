package com.example.androidapp.ui.graphs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.data.model.Co2Record;
import com.example.androidapp.data.model.HumidityRecord;
import com.example.androidapp.repository.Co2GraphRepository;
import com.example.androidapp.repository.HumidityGraphRepository;

import java.util.List;

public class Co2GraphActivityViewModel extends ViewModel {
    private Co2GraphRepository co2GraphRepository;

    public Co2GraphActivityViewModel() {
        co2GraphRepository = Co2GraphRepository.getInstance();
    }

    public LiveData<List<Co2Record>> getCo2Records() {
        co2GraphRepository.requestCo2Records();
        return co2GraphRepository.getCo2Records();
    }
}
