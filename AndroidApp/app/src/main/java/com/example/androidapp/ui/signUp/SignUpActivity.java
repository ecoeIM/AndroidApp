package com.example.androidapp.ui.signUp;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.MainActivity;
import com.example.androidapp.R;
import com.example.androidapp.ui.signIn.SignInActivity;
import com.google.android.material.snackbar.Snackbar;


import org.apache.commons.lang3.StringUtils;

import static android.content.ContentValues.TAG;

public class SignUpActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 42;
    private SignUpActivityViewModel viewModel;
    private EditText editPassword;
    private EditText editPasswordRepeat;
    private EditText editEmail;
    private TextView logInLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editPassword = findViewById(R.id.edit_text_password_sign_up);
        editEmail = findViewById(R.id.edit_text_email_sign_up);
        editPasswordRepeat = findViewById(R.id.editTextTextPasswordRepeat);
        logInLabel = findViewById(R.id.log_in_label);
        viewModel = new ViewModelProvider(this).get(SignUpActivityViewModel.class);

        logInLabel.setPaintFlags(logInLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        logInLabel.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), SignInActivity.class));
            finish();
        });

        findViewById(R.id.login).setOnClickListener(v -> {
            boolean isNotEmpty = true;
            if (StringUtils.isEmpty(editEmail.getText().toString())) {
                editEmail.setError("Required field");
                isNotEmpty = false;
            }
            if (StringUtils.isEmpty(editPassword.getText().toString())) {
                editPassword.setError("Required field");
                isNotEmpty = false;
            }
            if (StringUtils.isEmpty(editPasswordRepeat.getText().toString())) {
                editPasswordRepeat.setError("Required field");
                isNotEmpty = false;
            }
            if (isNotEmpty) {
                if (editPassword.getText().toString().length() >= 6) {
                    if (editPassword.getText().toString().equals(editPasswordRepeat.getText().toString())) {
                        viewModel.getMAuth().createUserWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString()).addOnCompleteListener(task -> {
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
                    } else {
                        editPassword.setError("Passwords do not match");
                        editPasswordRepeat.setError("Passwords do not match");
                    }
                } else {
                    editPassword.setError("At least 6 characters long");
                    editPasswordRepeat.setError("At least 6 characters long");
                }
            }
        });

        findViewById(R.id.image_button_help_sign_up).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You must create an account to start using EcoE.");
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
        this.editEmail.setText("");
        this.editPassword.setText("");
        this.editPasswordRepeat.setText("");
    }
}
