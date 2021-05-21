package com.example.androidapp.ui.graphs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.androidapp.R;

import java.util.ArrayList;
import java.util.List;


public class TemperatureGraphActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Toolbar toolbar;
    private Spinner temperature_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_graph);

        //Toolbar
        toolbar = findViewById(R.id.temperature_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Spinner
        temperature_spinner = findViewById(R.id.temperature_spinner);
        temperature_spinner.setOnItemSelectedListener(this);
        List<String> spinnerOptions = new ArrayList<>();
        spinnerOptions.add("Last hour");
        spinnerOptions.add("Last day");
        spinnerOptions.add("Last 7 days");
        spinnerOptions.add("Last 28 days");
        spinnerOptions.add("Last 90 days");
        spinnerOptions.add("Last 365 days");
        spinnerOptions.add("Lifetime");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerOptions);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        temperature_spinner.setAdapter(dataAdapter);
        //temperature_spinner.
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //for correct back animation
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
}