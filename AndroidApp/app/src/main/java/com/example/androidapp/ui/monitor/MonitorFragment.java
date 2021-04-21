package com.example.androidapp.ui.monitor;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.R;

public class MonitorFragment extends Fragment {

    private MonitorViewModel monitorViewModel;
    private ImageButton imageButtonTemperature;
    private ImageButton imageButtonHumidity;
    private ImageButton imageButtonCo2;
    private ImageButton imageButtonLight;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        monitorViewModel =
                new ViewModelProvider(this).get(MonitorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_monitor, container, false);
        final TextView textView = root.findViewById(R.id.text_monitor);
        monitorViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        imageButtonTemperature = root.findViewById(R.id.imageButtonTemperature);
        imageButtonHumidity = root.findViewById(R.id.imageButtonHumidity);
        imageButtonCo2 = root.findViewById(R.id.imageButtonCo2);
        imageButtonLight = root.findViewById(R.id.imageButtonLight);

        imageButtonTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return root;
    }

}