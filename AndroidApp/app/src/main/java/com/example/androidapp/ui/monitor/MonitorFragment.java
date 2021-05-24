package com.example.androidapp.ui.monitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.ui.graphs.CO2GraphActivity;
import com.example.androidapp.ui.graphs.HumidityGraphActivity;
import com.example.androidapp.ui.graphs.LightLevelGraphActivity;
import com.example.androidapp.R;
import com.example.androidapp.ui.graphs.TemperatureGraphActivity;
import com.example.androidapp.ui.settings.SettingsActivity;

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
    private ImageButton imageButtonMonitorHelp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        monitorViewModel =
                new ViewModelProvider(this).get(MonitorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_monitor, container, false);

        imageButtonTemperature = root.findViewById(R.id.imageButtonTemperature);
        imageButtonHumidity = root.findViewById(R.id.imageButtonHumidity);
        imageButtonCo2 = root.findViewById(R.id.imageButtonCo2);
        imageButtonLight = root.findViewById(R.id.imageButtonLight);
        imageButtonSettings = root.findViewById(R.id.imageButtonSettings);
        temperatureTextView = root.findViewById(R.id.temperatureTextView);
        humidityTextView = root.findViewById(R.id.humidityTextView);
        co2TextView = root.findViewById(R.id.co2TextView);
        lightTextView = root.findViewById(R.id.lightTextView);
        imageButtonMonitorHelp = root.findViewById(R.id.image_button_monitor_help);

        monitorViewModel.getTerrariumData().observe(getViewLifecycleOwner(), terrariumData -> {
            System.out.println(terrariumData.getTemperature());
            this.temperatureTextView.setText(terrariumData.getTemperature() + "");
            this.humidityTextView.setText(terrariumData.getHumidityLevel() + "");
            this.co2TextView.setText(terrariumData.getCarbonDioxideLevel() + "");
            this.lightTextView.setText(terrariumData.getNaturalLightLevel() + "");
        });


        imageButtonSettings.setOnClickListener(v -> {
            Intent toSettings = new Intent(getActivity(), SettingsActivity.class);
            startActivity(toSettings);
        });

        imageButtonTemperature.setOnClickListener(v -> {
            Intent toTemperatureGraph = new Intent(getContext(), TemperatureGraphActivity.class);
            startActivity(toTemperatureGraph);
        });

        imageButtonHumidity.setOnClickListener(v -> {
            Intent toHumidityGraph = new Intent(getContext(), HumidityGraphActivity.class);
            startActivity(toHumidityGraph);
        });

        imageButtonCo2.setOnClickListener(v -> {
            Intent toCo2Graph = new Intent(getContext(), CO2GraphActivity.class);
            startActivity(toCo2Graph);
        });

        imageButtonLight.setOnClickListener(v -> {
            Intent toLightGraph = new Intent(getContext(), LightLevelGraphActivity.class);
            startActivity(toLightGraph);
        });

        imageButtonMonitorHelp.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("On this screen, you can monitor the latest measurements received from the terrarium. Press the graph button next to each of the sections to view historical data for each measurement.");
            builder.setPositiveButton("Close", (dialog, id) -> {
                //close
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        monitorViewModel.getTerrariumData();
        return root;
    }
}