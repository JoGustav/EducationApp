package com.example.educationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.data.model.DiagramItem;
import com.example.educationapp.data.model.adapters.DiagramAdapter;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResultInterestTestActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DiagramAdapter diagramAdapter;
    private List<DiagramItem> diagramItems;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_test_result);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        diagramItems = new ArrayList<>();
        diagramItems.add(new DiagramItem("Интеллектуальная", 2));
        diagramItems.add(new DiagramItem("Творческая", -2));
        diagramItems.add(new DiagramItem("Академическая", 4));
        diagramItems.add(new DiagramItem("Художественно-изобразительная", 1));
        diagramItems.add(new DiagramItem("Музыкальная", 0));
        diagramItems.add(new DiagramItem("Литературная", 3));
        diagramItems.add(new DiagramItem("Артистическая", 0));
        diagramItems.add(new DiagramItem("Техническая", 5));
        diagramItems.add(new DiagramItem("Информационные технологии", 6));
        diagramItems.add(new DiagramItem("Лидерство", -6));
        diagramItems.add(new DiagramItem("Спортивная", -5));

        // Sort the diagramItems by value in descending order
        Collections.sort(diagramItems, new Comparator<DiagramItem>() {
            @Override
            public int compare(DiagramItem o1, DiagramItem o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });

        diagramAdapter = new DiagramAdapter(diagramItems);
        recyclerView.setAdapter(diagramAdapter);

        buttonNext = findViewById(R.id.buttonNext); // замените на ID вашей кнопки
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultInterestTestActivity.this, ResultInterestTestActivityDirection.class);
                startActivity(intent);
            }
        });

    }
}
