package com.example.androidapp.ui.graphs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.androidapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;


public class TemperatureGraphActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Toolbar toolbar;
    private Spinner temperature_spinner;
    private LineChart lineChart;
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
        viewModel.getTemperatureRecords();

        //graph
        lineChart = findViewById(R.id.temperature_graph);

//        //graph
//        this.lineChartView = findViewById(R.id.temperature_graph);
//
//        List<PointValue> values = new ArrayList<PointValue>();
//        values.add(new PointValue(0, 2));
//        values.add(new PointValue(1, 4));
//        values.add(new PointValue(2, 3));
//        values.add(new PointValue(3, 4));
//        values.add(new PointValue(4, 7));
//
//        //In most cased you can call data model methods in builder-pattern-like manner.
//        Line line = new Line(values).setColor(Color.parseColor("#008e54")).setCubic(true);
//        List<Line> lines = new ArrayList<>();
//        lines.add(line);
//
//        LineChartData data = new LineChartData();
//        data.setLines(lines);
//        Axis axisX = new Axis();
//        axisX.setName("Time");
//        Axis axisY = new Axis();
//        axisY.setName("Temperature");
//
//        data.setAxisXBottom(axisX);
//        data.setAxisYLeft(axisY);
//
//        lineChartView.setLineChartData(data);
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