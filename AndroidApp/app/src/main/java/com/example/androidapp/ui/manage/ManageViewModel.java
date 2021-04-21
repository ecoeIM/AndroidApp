package com.example.androidapp.ui.manage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.Model.TerrariumData;
import com.example.androidapp.Repository.ManageRepository;

public class ManageViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ManageRepository manageRepository;

    public ManageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is manage fragment");
        manageRepository = ManageRepository.getInstance();
    }

    LiveData<TerrariumData> getTerrariumData() {
        return manageRepository.getTerrariumData();
    }

    public void requestTerrariumData() {
        manageRepository.requestTerrariumData();
    }

    public LiveData<String> getText() {
        return mText;
    }
}