package com.example.educationapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.data.model.adapters.CoursePowAdapter;
import com.example.educationapp.data.model.adapters.DirectionPowAdapter;
import com.example.educationapp.data.model.entities.Course;
import com.example.educationapp.data.model.entities.Direction;
import com.example.educationapp.data.model.viewmodels.CourseViewModel;
import com.example.educationapp.data.model.viewmodels.DirectionViewModel;
import com.example.educationapp.databinding.ActivityDirectionsBinding;

public class CoursesActivity extends AppCompatActivity {

    public static final String COURSE_TO_PARSE =
            "com.example.educationapp.COURSE_TO_PARSE";
    private ActivityDirectionsBinding binding;
    RecyclerView recyclerView_courses;
    CoursePowAdapter courseRowAdapter;
    private CourseViewModel courseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDirectionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final RecyclerView recyclerView = binding.recyclerView;

        recyclerView_courses = binding.recyclerView;
        recyclerView_courses.setLayoutManager(new LinearLayoutManager(CoursesActivity.this));
        recyclerView_courses.setHasFixedSize(true);
        courseRowAdapter = new CoursePowAdapter();
        recyclerView_courses.setAdapter(courseRowAdapter);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        int directionID = getIntent().getIntExtra(DirectionsActivity.DIRECTION_TO_PARSE, -1);
        courseViewModel.getAllCoursesForDirection(directionID).observe(this, courses -> courseRowAdapter.setCourses(courses));

        courseRowAdapter.setOnItemClickListener(new CoursePowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course course) {
                Intent intent = new Intent(CoursesActivity.this, StagesActivity.class);
                intent.putExtra(COURSE_TO_PARSE, course.getCourseID());
                startActivity(intent);
            }
        });
    }
}