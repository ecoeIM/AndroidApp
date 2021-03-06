package com.example.androidapp.ui.signIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapp.MainActivity;
import com.example.androidapp.R;
import com.example.androidapp.ui.signUp.SignUpActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

//TODO: make sign in and signup textview butttons underlined
public class SignInActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 42;
    private SignInActivityViewModel viewModel;
    private EditText editPassword;
    private EditText editEmail;
    private TextView signUpLabel;
    private ConstraintLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editPassword = findViewById(R.id.edit_text_password_sign_in);
        editEmail = findViewById(R.id.edit_text_email_sign_in);
        signUpLabel = findViewById(R.id.sign_up_label);
        view = findViewById(R.id.cl_sign_in);
        viewModel = new ViewModelProvider(this).get(SignInActivityViewModel.class);

        signUpLabel.setPaintFlags(signUpLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        signUpLabel.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), SignUpActivity.class));
            finish();
        });
        findViewById(R.id.login).setOnClickListener(v -> {
            if (StringUtils.isEmpty(editEmail.getText().toString())) {
                editEmail.setError("Required field");
            }
            if (StringUtils.isEmpty(editPassword.getText().toString())) {
                editPassword.setError("Required field");
            }
            if (!StringUtils.isEmpty(editEmail.getText().toString()) && !StringUtils.isEmpty(editPassword.getText().toString())) {
                viewModel.getMauth().signInWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString()).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        reset();
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Authentication failed.");
                        builder.setPositiveButton("Close", (dialog, id) -> {
                            //close
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
            }
        });
        findViewById(R.id.image_button_help_sign_in).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You must sign in to use the system, if you don't have EcoE account just press sign up button.");
            builder.setPositiveButton("Close", (dialog, id) -> {
                //close
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        checkIfSignedIn();
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

    private void reset() {
        this.editPassword.setText("");
        this.editEmail.setText("");
    }

}