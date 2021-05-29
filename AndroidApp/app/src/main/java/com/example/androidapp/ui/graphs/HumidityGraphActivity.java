package com.example.androidapp.ui.graphs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.androidapp.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class HumidityGraphActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private Spinner humiditySpinner;
    private LineChartView humidityChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity_graph);

        //Toolbar
        toolbar = findViewById(R.id.humidity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Spinner
        humiditySpinner = findViewById(R.id.humidity_spinner);
        humiditySpinner.setOnItemSelectedListener(this);
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
        humiditySpinner.setAdapter(dataAdapter);
        //Light_Graph.
        this.humidityChartView = findViewById(R.id.humidity_graph);

        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 4));
        values.add(new PointValue(1, 2));
        values.add(new PointValue(2, 12));
        values.add(new PointValue(3, 3));
        values.add(new PointValue(4, 3));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.parseColor("#008e54")).setCubic(true);
        List<Line> lines = new ArrayList<>();
        lines.add(line);

        LineChartData data = new LineChartData();
        Axis axisX = new Axis();
        axisX.setName("Time");
        Axis axisY = new Axis();
        axisY.setName("Humidity");

        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        data.setLines(lines);

        humidityChartView.setLineChartData(data);
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