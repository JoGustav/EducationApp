package com.example.educationapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ResultInterestTestActivity extends AppCompatActivity {
    private HorizontalBarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_test_result);

        chart = findViewById(R.id.chart);

        // Настройка данных для диаграммы
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 6));
        entries.add(new BarEntry(1f, -3));
        // ... добавьте все значения

        BarDataSet dataSet = new BarDataSet(entries, "Test Results");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(16f);

        BarData data = new BarData(dataSet);

        // Настройка осей диаграммы
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(12f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawGridLines(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawAxisLine(true);
        rightAxis.setDrawGridLines(false);

        // Установка данных и обновление диаграммы
        chart.setData(data);
        chart.setFitBars(true);
        chart.getDescription().setEnabled(false);
        chart.animateY(1500);
        chart.invalidate();
    }
}
