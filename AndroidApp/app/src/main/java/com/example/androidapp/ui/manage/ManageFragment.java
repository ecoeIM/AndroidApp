package com.example.androidapp.ui.manage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.R;

public class ManageFragment extends Fragment {

    private ManageViewModel manageViewModel;
    private ImageButton imageButtonHelp;
    private ImageView imageViewVent;
    private ImageView imageViewLight;
    private TextView ventStatus;
    private TextView lightStatus;
    private boolean isVentOpen;
    private boolean isLightOn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        manageViewModel =
                new ViewModelProvider(this).get(ManageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_manage, container, false);

        imageButtonHelp = root.findViewById(R.id.image_button_manage_help);
        imageViewVent = root.findViewById(R.id.imageViewVent);
        imageViewLight = root.findViewById(R.id.imageViewLight);
        ventStatus = root.findViewById(R.id.ventStatus);
        lightStatus = root.findViewById(R.id.lightStatus);


        imageViewLight.setOnClickListener(v -> {
            manageViewModel.requestChangeTerrariumLightState(!isLightOn);
        });

        imageViewVent.setOnClickListener(v -> {
            manageViewModel.requestChangeTerrariumVentState(!isVentOpen);
        });

        imageButtonHelp.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Press on the icon to toggle switch. It might require some time for changes to apply on the IoT side.");
            builder.setPositiveButton("Close", (dialog, id) -> {
                //close
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });


        manageViewModel.getTerrariumData().observe(getViewLifecycleOwner(), terrariumData -> {
            this.isLightOn = terrariumData.isArtificialLightOn();
            this.isVentOpen = terrariumData.isVentOn();
            if (!isLightOn) {
                imageViewLight.setImageResource(R.drawable.ic_baseline_flashlight_off_24);
                lightStatus.setText("OFF");
                isLightOn = false;
            } else {
                imageViewLight.setImageResource(R.drawable.ic_baseline_flashlight_on_24);
                lightStatus.setText("ON");
                isLightOn = true;
            }

            if (!isVentOpen) {
                imageViewVent.setImageResource(R.drawable.ic_baseline_web_asset_off_24);
                ventStatus.setText("CLOSED");
                isVentOpen = false;
            } else {
                imageViewVent.setImageResource(R.drawable.ic_baseline_web_asset_24);
                ventStatus.setText("OPEN");
                isVentOpen = true;
            }
        });

        manageViewModel.getTerrariumLightState().observe(getViewLifecycleOwner(), state -> {
            if (!state) {
                imageViewLight.setImageResource(R.drawable.ic_baseline_flashlight_off_24);
                lightStatus.setText("OFF");
                isLightOn = false;
            } else {
                imageViewLight.setImageResource(R.drawable.ic_baseline_flashlight_on_24);
                lightStatus.setText("ON");
                isLightOn = true;
            }
        });

        manageViewModel.getTerrariumVentState().observe(getViewLifecycleOwner(), state -> {
            if (!state) {
                imageViewVent.setImageResource(R.drawable.ic_baseline_web_asset_off_24);
                ventStatus.setText("CLOSED");
                isVentOpen = false;
            } else {
                imageViewVent.setImageResource(R.drawable.ic_baseline_web_asset_24);
                ventStatus.setText("OPEN");
                isVentOpen = true;
            }
        });

        manageViewModel.requestTerrariumData();

        return root;
    }
}