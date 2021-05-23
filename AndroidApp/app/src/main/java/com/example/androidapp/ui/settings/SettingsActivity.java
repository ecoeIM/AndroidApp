package com.example.androidapp.ui.settings;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;

import com.example.androidapp.R;
import com.example.androidapp.ui.signIn.SignInActivityViewModel;

public class SettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button buttonLogOut;
    private SettingsActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        viewModel = new ViewModelProvider(this).get(SettingsActivityViewModel.class);
        buttonLogOut = findViewById(R.id.button_log_out);

        //Toolbar
        toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //logOut
        buttonLogOut.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you really want to log out?");
            builder.setNegativeButton("Cancel", (dialog, id) -> {
            });
            builder.setPositiveButton("Log out", (dialog, id) -> {
                viewModel.signOut();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    //for correct back animation
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
}