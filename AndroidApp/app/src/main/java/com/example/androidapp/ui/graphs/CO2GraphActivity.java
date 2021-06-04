package com.example.androidapp.ui.graphs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.anychart.AnyChartView;
import com.example.androidapp.R;
import com.example.androidapp.data.model.Co2Record;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class CO2GraphActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private Spinner co2Spinner;
    private AnyChartView co2ChartView;
    private Co2GraphActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_o2_graph);
        viewModel = new ViewModelProvider(this).get(Co2GraphActivityViewModel.class);

        //Toolbar
        toolbar = findViewById(R.id.co2_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Light_Graph.
        this.co2ChartView = findViewById(R.id.co2_Graph);
        this.co2ChartView.setChart(viewModel.getGraph());

        this.viewModel.getCo2Records().observe(this, co2Records -> {
            viewModel.updateGraph(co2Records);
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