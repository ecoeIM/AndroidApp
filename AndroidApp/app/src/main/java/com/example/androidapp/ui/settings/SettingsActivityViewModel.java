package com.example.androidapp.ui.settings;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.repository.UserRepository;

public class SettingsActivityViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    public SettingsActivityViewModel(Application app) {
        super(app);
        userRepository = UserRepository.getInstance(app);
    }

    public void signOut() {
        userRepository.signOut();
    }
}
