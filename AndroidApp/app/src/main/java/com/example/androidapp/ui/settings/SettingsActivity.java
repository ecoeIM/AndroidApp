package com.example.androidapp.ui.settings;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.androidapp.R;
import com.example.androidapp.ui.signIn.SignInActivity;
import com.example.androidapp.ui.signIn.SignInActivityViewModel;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Toolbar toolbar;
    private Button buttonLogOut;
    private SettingsActivityViewModel viewModel;
    private ImageButton imageButtonAddProfileHelp;
    private Spinner spinnerProfileSelector;
    private TextView textViewListIdLabelSettings;
    private ImageButton imageButtonAddProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        viewModel = new ViewModelProvider(this).get(SettingsActivityViewModel.class);
        buttonLogOut = findViewById(R.id.button_log_out);
        imageButtonAddProfileHelp = findViewById(R.id.image_button_add_profile_help);
        spinnerProfileSelector = findViewById(R.id.spinner_profile_selector);
        textViewListIdLabelSettings = findViewById(R.id.text_view_list_id_label_settings);
        imageButtonAddProfile = findViewById(R.id.image_button_add_profile);

        //TODO:set textView to real logged email textViewListIdLabelSettings

        //Toolbar
        toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //add profile
        imageButtonAddProfile.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_add_profile, null);
            builder.setView(dialogView);
            builder.setTitle("Add profile");

            EditText editTextProfileName = dialogView.findViewById(R.id.edit_text_profile_name);
            EditText editTextTempMin = dialogView.findViewById(R.id.edit_text_temp_min);
            EditText editTextTempMax = dialogView.findViewById(R.id.edit_text_temp_max);
            EditText editTextHumidMin = dialogView.findViewById(R.id.edit_text_hum_min);
            EditText editTextHumidMax = dialogView.findViewById(R.id.edit_text_hum_max);
            EditText editTextCo2Min = dialogView.findViewById(R.id.edit_text_co2_min);
            EditText editTextCo2Max = dialogView.findViewById(R.id.edit_text_co2_max);
            EditText editTextLightMin = dialogView.findViewById(R.id.edit_text_light_min);
            EditText editTextLightMax = dialogView.findViewById(R.id.edit_text_light_max);

            builder.setPositiveButton("Add", (dialog, which) -> {
                //Do nothing here because we override this button later to change the close behaviour.
            });
            builder.setNegativeButton("Cancel", (dialog, id) -> {
            });

            editTextProfileName.requestFocus();
            AlertDialog dialog = builder.create();
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v1 -> {
                boolean isOk = true;
                boolean isOk2 = true;
                if (StringUtils.isEmpty(editTextProfileName.getText().toString())) {
                    editTextProfileName.setError("Required field");
                    isOk = false;
                }
                if (StringUtils.isEmpty(editTextTempMin.getText().toString())) {
                    editTextTempMin.setError("Required field");
                    isOk = false;
                }
                if (StringUtils.isEmpty(editTextTempMax.getText().toString())) {
                    editTextTempMax.setError("Required field");
                    isOk = false;
                }
                if (StringUtils.isEmpty(editTextHumidMin.getText().toString())) {
                    editTextHumidMin.setError("Required field");
                    isOk = false;
                }
                if (StringUtils.isEmpty(editTextHumidMax.getText().toString())) {
                    editTextHumidMax.setError("Required field");
                    isOk = false;
                }
                if (StringUtils.isEmpty(editTextCo2Min.getText().toString())) {
                    editTextCo2Min.setError("Required field");
                    isOk = false;
                }
                if (StringUtils.isEmpty(editTextCo2Max.getText().toString())) {
                    editTextCo2Max.setError("Required field");
                    isOk = false;
                }
                if (StringUtils.isEmpty(editTextLightMin.getText().toString())) {
                    editTextLightMin.setError("Required field");
                    isOk = false;
                }
                if (StringUtils.isEmpty(editTextLightMax.getText().toString())) {
                    editTextLightMax.setError("Required field");
                    isOk = false;
                }
                if (isOk) {
                    if (Integer.parseInt(editTextTempMax.getText().toString()) <= Integer.parseInt(editTextTempMin.getText().toString())) {
                        editTextTempMax.setText("");
                        editTextTempMax.setError("M is lower/equal than m");
                        isOk2 = false;
                    }
                    if (Integer.parseInt(editTextHumidMax.getText().toString()) <= Integer.parseInt(editTextHumidMin.getText().toString())) {
                        editTextHumidMax.setText("");
                        editTextHumidMax.setError("M is lower/equal than m");
                        isOk2 = false;
                    }
                    if (Integer.parseInt(editTextCo2Max.getText().toString()) <= Integer.parseInt(editTextCo2Min.getText().toString())) {
                        editTextCo2Max.setText("");
                        editTextCo2Max.setError("M is lower/equal than m");
                        isOk2 = false;
                    }
                    if (Integer.parseInt(editTextLightMax.getText().toString()) <= Integer.parseInt(editTextLightMin.getText().toString())) {
                        editTextLightMax.setText("");
                        editTextLightMax.setError("M is lower/equal than m");
                        isOk2 = false;
                    }
                    if (isOk2)
                        dialog.dismiss();
                }
            });
        });

        //logOut
        buttonLogOut.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you really want to log out?");
            builder.setNegativeButton("Cancel", (dialog, id) -> {
            });
            builder.setPositiveButton("Log out", (dialog, id) -> {
                //TODO:fix
                viewModel.signOut();
                startActivity(new Intent(this, SignInActivity.class));
                finish();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        //Spinner
        spinnerProfileSelector.setOnItemSelectedListener(this);
        List<String> spinnerOptions = new ArrayList<>();
        //TODO:replace with real data
        spinnerOptions.add("Profile 1");
        spinnerOptions.add("Profile 2");
        spinnerOptions.add("Profile 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerOptions);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfileSelector.setAdapter(dataAdapter);

        //help (profiles)
        imageButtonAddProfileHelp.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Profiles are used to set the preferred max and min levels for temperature, carbon dioxide, light, and humidity inside the terrarium. Once one of the defined limits is reached, a notification is triggered.");
            builder.setPositiveButton("Close", (dialog, id) -> {
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}