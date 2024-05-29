package com.example.educationapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.data.model.adapters.CourseForUserPowAdapter;
import com.example.educationapp.data.model.adapters.CoursePowAdapter;
import com.example.educationapp.data.model.adapters.DirectionPowAdapter;
import com.example.educationapp.data.model.entities.Course;
import com.example.educationapp.data.model.entities.Direction;
import com.example.educationapp.data.model.repository.CourseProgressRepository;
import com.example.educationapp.data.model.viewmodels.CourseProgressViewModel;
import com.example.educationapp.data.model.viewmodels.CourseViewModel;
import com.example.educationapp.data.model.viewmodels.DirectionViewModel;
import com.example.educationapp.data.model.viewmodels.StageViewModel;
import com.example.educationapp.databinding.ActivityDirectionsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CoursesForUserActivity extends AppCompatActivity {

    public static final String COURSE_TO_PARSE =
            "com.example.educationapp.COURSE_TO_PARSE";
    private ActivityDirectionsBinding binding;
    RecyclerView recyclerView_courses;
    CourseForUserPowAdapter courseForUserRowAdapter;
    private CourseViewModel courseViewModel;
    private CourseProgressViewModel courseProgressViewModel;

    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDirectionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            userID = sessionManager.getUserId();
        }

        final RecyclerView recyclerView = binding.recyclerView;

        recyclerView_courses = binding.recyclerView;
        recyclerView_courses.setLayoutManager(new LinearLayoutManager(CoursesForUserActivity.this));
        recyclerView_courses.setHasFixedSize(true);

        CourseProgressRepository repository = new CourseProgressRepository(getApplication());
        courseForUserRowAdapter= new CourseForUserPowAdapter(userID,this,repository);
        recyclerView_courses.setAdapter(courseForUserRowAdapter);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        courseViewModel.getAllCoursesOfUser(userID).observe(this, courses -> courseForUserRowAdapter.setCourses(courses));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_all_directions) {
                Intent intent = new Intent(CoursesForUserActivity.this, DirectionsActivity.class);
                startActivity(intent);
            } else if (id == R.id.navigation_my_courses) {

            } else if (id == R.id.navigation_profile) {
                Intent intent = new Intent(CoursesForUserActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
            return true;
        });

        courseForUserRowAdapter.setOnItemClickListener(new CourseForUserPowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course course) {
                Intent intent = new Intent(CoursesForUserActivity.this, StagesActivity.class);
                intent.putExtra(COURSE_TO_PARSE, course.getCourseID());
                startActivity(intent);
            }
        });
    }
}
