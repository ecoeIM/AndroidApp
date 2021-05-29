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

public class LightLevelGraphActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private Spinner light_spinner;
    private LineChartView lightChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_level_graph);

        //Toolbar
        toolbar = findViewById(R.id.light_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Spinner
        light_spinner = findViewById(R.id.light_spinner);
        light_spinner.setOnItemSelectedListener(this);
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
        light_spinner.setAdapter(dataAdapter);
        //Light_Graph.
        this.lightChartView = findViewById(R.id.light_graph);

        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 9));
        values.add(new PointValue(1, 8));
        values.add(new PointValue(2, 10));
        values.add(new PointValue(3, 5));
        values.add(new PointValue(4, 9));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.GREEN).setCubic(true);
        List<Line> lines = new ArrayList<>();
        lines.add(line);

        LineChartData data = new LineChartData();

        Axis axisX = new Axis();
        axisX.setName("Time");
        Axis axisY = new Axis();
        axisY.setName("LightLevel");

        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        data.setLines(lines);

        lightChartView.setLineChartData(data);
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