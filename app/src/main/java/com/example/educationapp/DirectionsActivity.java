package com.example.educationapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.data.model.adapters.DirectionPowAdapter;
import com.example.educationapp.data.model.entities.Direction;
import com.example.educationapp.data.model.viewmodels.DirectionViewModel;
import com.example.educationapp.databinding.ActivityDirectionsBinding;

public class DirectionsActivity extends AppCompatActivity {

    public static final String DIRECTION_TO_PARSE =
            "com.example.educationapp.DIRECTION_TO_PARSE";
    private ActivityDirectionsBinding binding;
    RecyclerView recyclerView_directions;
    DirectionPowAdapter directionRowAdapter;
    private DirectionViewModel directionViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDirectionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final RecyclerView recyclerView = binding.recyclerView;

        recyclerView_directions = binding.recyclerView;
        recyclerView_directions.setLayoutManager(new LinearLayoutManager(DirectionsActivity.this));
        recyclerView_directions.setHasFixedSize(true);
        directionRowAdapter = new DirectionPowAdapter();
        recyclerView_directions.setAdapter(directionRowAdapter);
        directionViewModel = new ViewModelProvider(this).get(DirectionViewModel.class);
        directionViewModel.getAllDirections().observe(this, directions -> directionRowAdapter.setDirections(directions));

        directionRowAdapter.setOnItemClickListener(new DirectionPowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Direction direction) {
                Intent intent = new Intent(DirectionsActivity.this, CoursesActivity.class);
                intent.putExtra(DIRECTION_TO_PARSE, direction.getDirectionID());
                startActivity(intent);
            }
    });
}
}