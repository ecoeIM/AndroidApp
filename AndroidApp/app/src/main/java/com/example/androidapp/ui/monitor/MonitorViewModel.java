package com.example.androidapp.ui.monitor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.Model.TerrariumData;
import com.example.androidapp.Repository.MonitorRepository;

public class MonitorViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MonitorRepository monitorRepository;

    public MonitorViewModel() {
        monitorRepository = MonitorRepository.getInstance();
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<TerrariumData> getTerrariumData(){
        monitorRepository.requestTerrariumData();
        return monitorRepository.getTerrariumData();
    }



}