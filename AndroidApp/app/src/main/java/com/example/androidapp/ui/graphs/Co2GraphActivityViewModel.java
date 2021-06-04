package com.example.androidapp.ui.graphs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.androidapp.data.model.Co2Record;
import com.example.androidapp.data.model.HumidityRecord;
import com.example.androidapp.repository.Co2GraphRepository;
import com.example.androidapp.repository.HumidityGraphRepository;

import java.util.ArrayList;
import java.util.List;

public class Co2GraphActivityViewModel extends ViewModel {
    private Co2GraphRepository co2GraphRepository;
    private final Cartesian cartesian;
    private final com.anychart.core.cartesian.series.Line series1;

    public Co2GraphActivityViewModel() {
        co2GraphRepository = Co2GraphRepository.getInstance();
        cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Carbon dioxide graph");

        cartesian.yAxis(0).title("%");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("0", 0));


        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        series1 = cartesian.line(series1Mapping);
        series1.name("Carbon dioxide graph");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);
    }

    public Cartesian getGraph(){
        return this.cartesian;
    }


    public void updateGraph(List<Co2Record> records)
    {
        List<DataEntry> seriesData = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            seriesData.add(new CustomDataEntry(records.get(i).dateTime,records.get(i).value));
        }
        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        this.series1.data(series1Mapping);
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value) {
            super(x, value);
        }
    }

    public LiveData<List<Co2Record>> getCo2Records() {
        co2GraphRepository.requestCo2Records();
        return co2GraphRepository.getCo2Records();
    }
}
