package com.example.androidapp.ui.signIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidapp.MainActivity;
import com.example.androidapp.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 42;
    private SignInActivityViewModel viewModel;
    private EditText editPassword;
    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SignInActivityViewModel.class);
        setContentView(R.layout.activity_login);
        editPassword = findViewById(R.id.editTextTextPassword);
        editEmail = findViewById(R.id.editTextTextEmailAddress);
        findViewById(R.id.button_sign_in).setOnClickListener(v -> {
            viewModel.getMauth().signInWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    System.out.println("Aboboa");
                }
            });

        });
        checkIfSignedIn();
    }

    public void signIn() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(true)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null)
                goToMainActivity();
        });
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            handleSignInRequest(resultCode);
        }
    }

    private void handleSignInRequest(int resultCode) {
        if (resultCode == RESULT_OK)
            goToMainActivity();
        else
            Toast.makeText(this, "SIGN IN CANCELLED", Toast.LENGTH_SHORT).show();
    }

}