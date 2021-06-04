package com.example.androidapp.ui.graphs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.androidapp.R;
import com.example.androidapp.data.model.TemperatureRecord;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;



public class TemperatureGraphActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Toolbar toolbar;
    private Spinner temperature_spinner;
    private AnyChartView lineChartView;
    private TemperatureGraphActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_graph);
        viewModel = new ViewModelProvider(this).get(TemperatureGraphActivityViewModel.class);

        //Toolbar
        toolbar = findViewById(R.id.temperature_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel.getTemperatureRecords().observe(this, temperatureRecords -> {
            this.viewModel.updateGraph(temperatureRecords);
        });

        //graph
        lineChartView = findViewById(R.id.temperature_graph);
        lineChartView.setChart(viewModel.getGraph());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {

        } else if (position == 1) {

        } else if (position == 2) {

        } else if (position == 3) {

        } else if (position == 4) {

        } else if (position == 5) {

        } else if (position == 6) {

        }
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

    public void setLineChartData() {
        List<String> xValues = new ArrayList<>();
        xValues.add("11.00 AM");
        xValues.add("12.00 AM");
        xValues.add("1.00 PM");
        xValues.add("2.00 PM");
        xValues.add("3.00 PM");

        List<Entry> listEntries = new ArrayList<>();
        listEntries.add(new Entry(20f, 0));
        listEntries.add(new Entry(30f, 1));
        listEntries.add(new Entry(40f, 2));
        listEntries.add(new Entry(50f, 3));
        listEntries.add(new Entry(60f, 5));

        LineDataSet lineDataSet = new LineDataSet(listEntries, "Test");

        LineData data = new LineData(lineDataSet);
    }
}