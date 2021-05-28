package com.example.androidapp.ui.signUp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidapp.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivityViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private final FirebaseAuth mAuth;

    public SignUpActivityViewModel(Application app) {
        super(app);
        userRepository = UserRepository.getInstance(app);
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth getMAuth()
    {
        return  mAuth;
    }
    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }
}
