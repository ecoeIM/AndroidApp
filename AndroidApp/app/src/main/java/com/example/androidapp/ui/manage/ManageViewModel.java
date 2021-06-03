package com.example.androidapp.ui.manage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.data.model.TerrariumData;
import com.example.androidapp.repository.ManageRepository;

public class ManageViewModel extends ViewModel {

    private ManageRepository manageRepository;

    public ManageViewModel() {
        manageRepository = ManageRepository.getInstance();
    }

    LiveData<TerrariumData> getTerrariumData() {
        return manageRepository.getTerrariumData();
    }

    LiveData<Boolean> getTerrariumLightState() {
        return manageRepository.getTerrariumLightState();
    }

    LiveData<Boolean> getTerrariumVentState() {
        return manageRepository.getTerrariumVentState();
    }

    public void requestTerrariumData() {
        manageRepository.requestTerrariumData();
    }

    public void requestChangeTerrariumLightState(boolean newState) {
        manageRepository.requestChangeTerrariumLightState(newState);
    }

    public void requestChangeTerrariumVentState(boolean newState) {
        manageRepository.requestChangeTerrariumVentState(newState);
    }
}