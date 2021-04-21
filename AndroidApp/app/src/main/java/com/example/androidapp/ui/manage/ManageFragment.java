package com.example.androidapp.ui.manage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.R;

public class ManageFragment extends Fragment {

    private ManageViewModel manageViewModel;
    private TextView textView6;
    private Switch switch1;
    //private boolean artificialLightStatus;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        manageViewModel =
                new ViewModelProvider(this).get(ManageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_manage, container, false);
        final TextView textView = root.findViewById(R.id.text_manage);
        manageViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        textView6 = root.findViewById(R.id.textView6);
        switch1 = root.findViewById(R.id.switch1);
        
        manageViewModel.getTerrariumData().observe(getViewLifecycleOwner(), terrariumData -> {
            textView6.setText("test");
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                manageViewModel.requestTerrariumData();
            }
        });

        return root;
    }
}