package com.example.educationapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.data.model.adapters.DirectionPowAdapter;
import com.example.educationapp.data.model.entities.Direction;
import com.example.educationapp.data.model.viewmodels.DirectionViewModel;
import com.example.educationapp.databinding.ActivityDirectionsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        final RecyclerView recyclerView = binding.recyclerView;

        recyclerView_directions = binding.recyclerView;
        recyclerView_directions.setLayoutManager(new LinearLayoutManager(DirectionsActivity.this));
        recyclerView_directions.setHasFixedSize(true);
        directionRowAdapter = new DirectionPowAdapter();
        recyclerView_directions.setAdapter(directionRowAdapter);
        directionViewModel = new ViewModelProvider(this).get(DirectionViewModel.class);
        directionViewModel.getAllDirections().observe(this, directions -> directionRowAdapter.setDirections(directions));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_all_directions) {
                // Переход к списку всех направлений
            } else if (id == R.id.navigation_my_courses) {

            } else if (id == R.id.navigation_profile) {
                Intent intent = new Intent(DirectionsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
            return true;
        });



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