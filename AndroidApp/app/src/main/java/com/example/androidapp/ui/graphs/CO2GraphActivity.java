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

public class CO2GraphActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private Spinner co2Spinner;
    private LineChartView co2ChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_o2_graph);

        //Toolbar
        toolbar = findViewById(R.id.co2_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Spinner
        co2Spinner = findViewById(R.id.co2_spinner);
        co2Spinner.setOnItemSelectedListener(this);
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
        co2Spinner.setAdapter(dataAdapter);
        //Light_Graph.
        this.co2ChartView = findViewById(R.id.co2_Graph);

        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 3));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 5));
        values.add(new PointValue(3, 6));
        values.add(new PointValue(4, 2));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.GREEN).setCubic(true);
        List<Line> lines = new ArrayList<>();
        lines.add(line);

        LineChartData data = new LineChartData();

        Axis axisX = new Axis();
        axisX.setName("Time");
        Axis axisY = new Axis();
        axisY.setName("Co2Level");

        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        data.setLines(lines);

        co2ChartView.setLineChartData(data);

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