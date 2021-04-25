package com.example.androidapp.ui.manage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
        final TextView textView = root.findViewById(R.id.text_manage);
        //manageViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        imageButtonHelp = root.findViewById(R.id.imageButtonHelp);
        imageViewVent = root.findViewById(R.id.imageViewVent);
        imageViewLight = root.findViewById(R.id.imageViewLight);
        ventStatus = root.findViewById(R.id.ventStatus);
        lightStatus = root.findViewById(R.id.lightStatus);

        imageViewLight.setOnClickListener(v -> {
            if (isLightOn) {
                imageViewLight.setImageResource(R.drawable.ic_baseline_flashlight_off_24);
                lightStatus.setText("OFF");
                isLightOn = false;
            } else {
                imageViewLight.setImageResource(R.drawable.ic_baseline_flashlight_on_24);
                lightStatus.setText("ON");
                isLightOn = true;
            }
        });

        imageViewVent.setOnClickListener(v -> {
            if (isVentOpen) {
                imageViewVent.setImageResource(R.drawable.ic_baseline_web_asset_off_24);
                ventStatus.setText("CLOSED");
                isVentOpen = false;
            } else {
                imageViewVent.setImageResource(R.drawable.ic_baseline_web_asset_24);
                ventStatus.setText("OPEN");
                isVentOpen = true;
            }
        });

        imageButtonHelp.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Press on the icon to toggle switch", Toast.LENGTH_LONG).show();
            //TODO: dialog window here
            manageViewModel.requestTerrariumData();
        });


        
      /*
      *   manageViewModel.getTerrariumData().observe(getViewLifecycleOwner(), terrariumData -> {
            textView6.setText("test");
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                manageViewModel.requestTerrariumData();
            }
        });*/

        return root;
    }
}