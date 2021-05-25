package com.example.androidapp.ui.signIn;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidapp.repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;


public class SignInActivityViewModel extends AndroidViewModel {

    private final UserRepository userRepository;

    public SignInActivityViewModel(Application app) {
        super(app);
        userRepository = UserRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }
}
