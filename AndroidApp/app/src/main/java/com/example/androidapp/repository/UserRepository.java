package com.example.androidapp.repository;

import android.app.Application;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.androidapp.data.liveData.UserLiveData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class UserRepository {
    private final UserLiveData currentUser;
    private final Application app;
    private FirebaseAuth mAuth;
    private static UserRepository instance;

    private UserRepository(Application app) {
        this.app = app;
        currentUser = new UserLiveData();
    }

    public static synchronized UserRepository getInstance(Application app) {
        if (instance == null)
            instance = new UserRepository(app);
        instance.mAuth = FirebaseAuth.getInstance();
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }


    public void signOut() {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }
}