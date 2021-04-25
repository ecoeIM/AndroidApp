package com.example.androidapp.ui.monitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.Model.Terrarium;
import com.example.androidapp.R;
import com.example.androidapp.SettingsActivity;

public class MonitorFragment extends Fragment {

    private MonitorViewModel monitorViewModel;
    private ImageButton imageButtonTemperature;
    private ImageButton imageButtonHumidity;
    private ImageButton imageButtonCo2;
    private ImageButton imageButtonLight;
    private ImageButton imageButtonSettings;
    private TextView temperatureTextView;
    private TextView humidityTextView;
    private TextView co2TextView;
    private TextView lightTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        monitorViewModel =
                new ViewModelProvider(this).get(MonitorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_monitor, container, false);
        final TextView textView = root.findViewById(R.id.text_monitor);
        //.getText().observe(getViewLifecycleOwner(), textView::setText);

        imageButtonTemperature = root.findViewById(R.id.imageButtonTemperature);
        imageButtonHumidity = root.findViewById(R.id.imageButtonHumidity);
        imageButtonCo2 = root.findViewById(R.id.imageButtonCo2);
        imageButtonLight = root.findViewById(R.id.imageButtonLight);
        imageButtonSettings = root.findViewById(R.id.imageButtonSettings);
        temperatureTextView = root.findViewById(R.id.temperatureTextView);
        humidityTextView = root.findViewById(R.id.humidityTextView);
        co2TextView = root.findViewById(R.id.co2TextView);
        lightTextView = root.findViewById(R.id.lightTextView);

        monitorViewModel.getTerrariumData().observe(getViewLifecycleOwner(), terrariumData -> {
            System.out.println(terrariumData.getTemperature());
            this.temperatureTextView.setText(terrariumData.getTemperature() + "");
            this.humidityTextView.setText(terrariumData.getHumidityLevel() + "");
            this.co2TextView.setText(terrariumData.getCarbonDioxideLevel() + "");
            this.lightTextView.setText(terrariumData.getNaturalLightLevel() + "");
        });


        imageButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSettings = new Intent(getActivity(), SettingsActivity.class);
                startActivity(toSettings);
            }
        });

        imageButtonTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        monitorViewModel.getTerrariumData();

        return root;
    }
}