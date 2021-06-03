package com.example.androidapp.ui.settings;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
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
import com.example.androidapp.data.model.Profile;
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
    private ImageButton imageButtonAddProfile;
    private ImageButton imageButtonProfileInfo;
    private ImageButton imageButtonEditProfile;
    private ImageButton imageButtonDeleteProfile;
    private ImageButton imageButtonShare;
    private ImageButton imageButtonAppInfo;
    private Button buttonSendEmail;
    private TextView textViewListIdLabelSettings;
    private List<Profile> scopedProfiles;
    private boolean userIsInteracting;
    private int activeProfileId;
    ArrayAdapter<String> dataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        viewModel = new ViewModelProvider(this).get(SettingsActivityViewModel.class);
        buttonLogOut = findViewById(R.id.button_log_out);
        imageButtonAddProfileHelp = findViewById(R.id.image_button_add_profile_help);
        spinnerProfileSelector = findViewById(R.id.spinner_profile_selector);
        imageButtonAddProfile = findViewById(R.id.image_button_add_profile);
        imageButtonProfileInfo = findViewById(R.id.image_button_profile_info);
        imageButtonEditProfile = findViewById(R.id.image_button_edit_profile);
        imageButtonDeleteProfile = findViewById(R.id.image_button_delete_profile);
        imageButtonShare = findViewById(R.id.image_button_share);
        imageButtonAppInfo = findViewById(R.id.image_button_app_info);
        buttonSendEmail = findViewById(R.id.button_send_email);
        textViewListIdLabelSettings = findViewById(R.id.text_view_list_id_label_settings);
        textViewListIdLabelSettings.setText(viewModel.getCurrentEmail());
        scopedProfiles = new ArrayList<>();
        activeProfileId = -1;

        imageButtonProfileInfo.setEnabled(false);
        imageButtonDeleteProfile.setEnabled(false);
        imageButtonEditProfile.setEnabled(false);

        viewModel.getActiveProfile();
        viewModel.getProfiles();

        //Toolbar
        toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //observers
        viewModel.getProfiles().observe(this, profiles -> {
            scopedProfiles = profiles;

            //Spinner
            spinnerProfileSelector.setOnItemSelectedListener(this);
            List<String> spinnerOptions = new ArrayList<>();
            for (int i = 0; i < scopedProfiles.size(); i++) {
                spinnerOptions.add(scopedProfiles.get(i).profileName);
            }
            dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerOptions);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerProfileSelector.setAdapter(dataAdapter);
            if (activeProfileId != -1) {
                System.out.println(activeProfileId);
                for (int i = 0; i < scopedProfiles.size(); i++) {
                    if (scopedProfiles.get(i).id == activeProfileId) {
                        spinnerProfileSelector.setSelection(i);
                        break;
                    }
                }
            }

            viewModel.getActiveProfile().observe(this, profileId -> {
                activeProfileId = profileId;
                if (activeProfileId != -1) {
                    System.out.println(activeProfileId);
                    for (int i = 0; i < scopedProfiles.size(); i++) {
                        if (scopedProfiles.get(i).id == activeProfileId) {
                            spinnerProfileSelector.setSelection(i);
                            break;
                        }
                    }
                }
            });


            if (!scopedProfiles.isEmpty()) {
                imageButtonProfileInfo.setEnabled(true);
                imageButtonDeleteProfile.setEnabled(true);
                imageButtonEditProfile.setEnabled(true);
            } else {
                imageButtonProfileInfo.setEnabled(false);
                imageButtonDeleteProfile.setEnabled(false);
                imageButtonEditProfile.setEnabled(false);
            }
        });

        //share
        imageButtonShare.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://github.com/ecoeIM/AndroidApp");
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        //app info
        imageButtonAppInfo.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Version: 0.9");
            builder.setMessage("SEP4, Group 4, Authors: Nicolai Pavliuc, Evgheni Demcenco, Ioana Grigore.");
            builder.setPositiveButton("Close", (dialog, id) -> {
                //close
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        //send email
        buttonSendEmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            String[] arr = {"293101@via.dk"};
            intent.putExtra(Intent.EXTRA_EMAIL, arr);
            intent.putExtra(Intent.EXTRA_SUBJECT, "ecoE Issue Report");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });

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
                    if (isOk2) {
                        Profile newProfile = new Profile();
                        newProfile.profileName = editTextProfileName.getText().toString();
                        newProfile.minTemp = Integer.parseInt(editTextTempMin.getText().toString());
                        newProfile.maxTemp = Integer.parseInt(editTextTempMax.getText().toString());
                        newProfile.minHumid = Integer.parseInt(editTextHumidMin.getText().toString());
                        newProfile.maxHumid = Integer.parseInt(editTextHumidMax.getText().toString());
                        newProfile.minCo2 = Integer.parseInt(editTextCo2Min.getText().toString());
                        newProfile.maxCo2 = Integer.parseInt(editTextCo2Max.getText().toString());
                        newProfile.minLight = Integer.parseInt(editTextLightMin.getText().toString());
                        newProfile.maxLight = Integer.parseInt(editTextLightMax.getText().toString());
                        newProfile.terrariumId = 1;
                        viewModel.addProfile(newProfile);
                        //dataAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }
            });
        });

        //edit profile
        imageButtonEditProfile.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_add_profile, null);
            builder.setView(dialogView);
            builder.setTitle("Edit profile");

            EditText editTextProfileName = dialogView.findViewById(R.id.edit_text_profile_name);
            EditText editTextTempMin = dialogView.findViewById(R.id.edit_text_temp_min);
            EditText editTextTempMax = dialogView.findViewById(R.id.edit_text_temp_max);
            EditText editTextHumidMin = dialogView.findViewById(R.id.edit_text_hum_min);
            EditText editTextHumidMax = dialogView.findViewById(R.id.edit_text_hum_max);
            EditText editTextCo2Min = dialogView.findViewById(R.id.edit_text_co2_min);
            EditText editTextCo2Max = dialogView.findViewById(R.id.edit_text_co2_max);
            EditText editTextLightMin = dialogView.findViewById(R.id.edit_text_light_min);
            EditText editTextLightMax = dialogView.findViewById(R.id.edit_text_light_max);

            Profile selectedProfile = null;
            for (int i = 0; i < scopedProfiles.size(); i++) {
                if (scopedProfiles.get(i).id == activeProfileId) {
                    selectedProfile = scopedProfiles.get(i);
                }
            }

            editTextProfileName.setText(selectedProfile.profileName);
            editTextTempMin.setText(Integer.toString(selectedProfile.minTemp));
            editTextTempMax.setText(Integer.toString(selectedProfile.maxTemp));
            editTextHumidMin.setText(Integer.toString(selectedProfile.minHumid));
            editTextHumidMax.setText(Integer.toString(selectedProfile.maxHumid));
            editTextCo2Min.setText(Integer.toString(selectedProfile.minCo2));
            editTextCo2Max.setText(Integer.toString(selectedProfile.maxCo2));
            editTextLightMin.setText(Integer.toString(selectedProfile.minLight));
            editTextLightMax.setText(Integer.toString(selectedProfile.maxLight));


            builder.setPositiveButton("Save", (dialog, which) -> {
                //Do nothing here because we override this button later to change the close behaviour.
            });
            builder.setNegativeButton("Cancel", (dialog, id) -> {
            });

            AlertDialog dialog = builder.create();
            dialog.show();
            Profile finalSelectedProfile = selectedProfile;
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
                    if (isOk2) {
                        finalSelectedProfile.profileName = editTextProfileName.getText().toString();
                        finalSelectedProfile.minTemp = Integer.parseInt(editTextTempMin.getText().toString());
                        finalSelectedProfile.maxTemp = Integer.parseInt(editTextTempMax.getText().toString());
                        finalSelectedProfile.minHumid = Integer.parseInt(editTextHumidMin.getText().toString());
                        finalSelectedProfile.maxHumid = Integer.parseInt(editTextHumidMax.getText().toString());
                        finalSelectedProfile.minCo2 = Integer.parseInt(editTextCo2Min.getText().toString());
                        finalSelectedProfile.maxCo2 = Integer.parseInt(editTextCo2Max.getText().toString());
                        finalSelectedProfile.minLight = Integer.parseInt(editTextLightMin.getText().toString());
                        finalSelectedProfile.maxLight = Integer.parseInt(editTextLightMax.getText().toString());
                        System.out.println(finalSelectedProfile.minTemp);
                        System.out.println(finalSelectedProfile.maxTemp);
                        viewModel.updateProfile(finalSelectedProfile);
                        dialog.dismiss();
                    }
                }
            });
        });

        //delete profile
        imageButtonDeleteProfile.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you really want to remove the selected profile? Another profile (if any) will not be automatically selected.");

            builder.setPositiveButton("Delete", (dialog, id) -> {
                //delete, and set profile to none
                viewModel.deleteProfile(activeProfileId);
            });
            builder.setNegativeButton("Cancel", (dialog, id) -> {
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        //profile info
        imageButtonProfileInfo.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_profile_info, null);
            builder.setView(dialogView);


            Profile selectedProfile = null;
            for (int i = 0; i < scopedProfiles.size(); i++) {
                if (scopedProfiles.get(i).id == activeProfileId) {
                    selectedProfile = scopedProfiles.get(i);
                }
            }

            TextView textViewTemp = dialogView.findViewById(R.id.text_view_temp);
            TextView textViewHum = dialogView.findViewById(R.id.text_view_hum);
            TextView textViewCo2 = dialogView.findViewById(R.id.text_view_co2);
            TextView textViewLight = dialogView.findViewById(R.id.text_view_light);

            if (selectedProfile != null) {
                builder.setTitle(selectedProfile.profileName);
                textViewTemp.setText("m:" + selectedProfile.minTemp + " M:" + selectedProfile.maxTemp);
                textViewHum.setText("m:" + selectedProfile.minHumid + " M:" + selectedProfile.maxHumid);
                textViewCo2.setText("m:" + selectedProfile.minCo2 + " M:" + selectedProfile.maxCo2);
                textViewLight.setText("m:" + selectedProfile.minLight + " M:" + selectedProfile.maxLight);
            }

            builder.setPositiveButton("Close", (dialog, id) -> {
            });
            AlertDialog dialog = builder.create();
            dialog.show();
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
    public void onUserInteraction() {
        super.onUserInteraction();
        userIsInteracting = true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (userIsInteracting) {
            viewModel.setActiveProfile(scopedProfiles.get(position).id);
            userIsInteracting = false;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}