package com.example.androidapp.ui.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.MainActivity;
import com.example.androidapp.R;
import com.example.androidapp.ui.signIn.SignInActivity;


import static android.content.ContentValues.TAG;

public class SignUpActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 42;
    private SignUpActivityViewModel viewModel;
    private EditText editPassword;
    private EditText editPasswordRepeat;
    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SignUpActivityViewModel.class);
        setContentView(R.layout.activity_signup);
        findViewById(R.id.log_in_label).setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), SignInActivity.class));
            finish();
        });
        editPassword = findViewById(R.id.edit_text_password_sign_up);
        editEmail = findViewById(R.id.edit_text_email_sign_up);
        editPasswordRepeat = findViewById(R.id.editTextTextPasswordRepeat);

        findViewById(R.id.login).setOnClickListener(v -> {
            if (editPassword.getText().length() >= 6) {
                if (editPassword.getText().toString().equals(editPasswordRepeat.getText().toString()))
                    viewModel.getMAuth().createUserWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString()).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            reset();
                        }
                    });
                else {
                    Toast.makeText(this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                    reset();
                }
            }
            else {
                Toast.makeText(this, "Passwords must be minimum length of 6", Toast.LENGTH_SHORT).show();
                reset();
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

    private void reset()
    {
        this.editEmail.setText("");
        this.editPassword.setText("");
        this.editPasswordRepeat.setText("");
    }
}
