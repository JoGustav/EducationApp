package com.example.educationapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.data.model.adapters.CoursePowAdapter;
import com.example.educationapp.data.model.adapters.DirectionPowAdapter;
import com.example.educationapp.data.model.adapters.StagePowAdapter;
import com.example.educationapp.data.model.entities.Course;
import com.example.educationapp.data.model.entities.CourseProgress;
import com.example.educationapp.data.model.entities.Direction;
import com.example.educationapp.data.model.entities.Stage;
import com.example.educationapp.data.model.entities.UsersCourses;
import com.example.educationapp.data.model.viewmodels.CourseProgressViewModel;
import com.example.educationapp.data.model.viewmodels.CourseViewModel;
import com.example.educationapp.data.model.viewmodels.DirectionViewModel;
import com.example.educationapp.data.model.viewmodels.StageViewModel;
import com.example.educationapp.data.model.viewmodels.UsersCoursesViewModel;
import com.example.educationapp.databinding.ActivityDirectionsBinding;
import com.example.educationapp.databinding.ActivityStagesBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StagesActivity extends AppCompatActivity {

    public static final String STAGE_TO_PARSE =
            "com.example.educationapp.STAGE_TO_PARSE";
    private ActivityStagesBinding binding;
    RecyclerView recyclerView_stages;
    StagePowAdapter stageRowAdapter;
    private StageViewModel stageViewModel;
    private CourseProgressViewModel courseProgressViewModel;
    private UsersCoursesViewModel usersCoursesViewModel;
    private Button buttonProceed;
    private int userID;
    private FrameLayout overlay;
    private int countStage;
    private LiveData<Integer> countStagesLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        overlay = findViewById(R.id.overlay);

        //заблокировать доступ к экарну
        overlay.setVisibility(View.VISIBLE);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = binding.recyclerView;
        recyclerView_stages = binding.recyclerView;
        recyclerView_stages.setLayoutManager(new LinearLayoutManager(StagesActivity.this));
        recyclerView_stages.setHasFixedSize(true);
        stageRowAdapter = new StagePowAdapter();
        recyclerView_stages.setAdapter(stageRowAdapter);

        stageViewModel = new ViewModelProvider(this).get(StageViewModel.class);
        courseProgressViewModel = new ViewModelProvider(this).get(CourseProgressViewModel.class);
        usersCoursesViewModel = new ViewModelProvider(this).get(UsersCoursesViewModel.class);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            userID = sessionManager.getUserId();
        }
        int courseID = getIntent().getIntExtra(CoursesActivity.COURSE_TO_PARSE, -1);

        buttonProceed = binding.buttonProceed;
        buttonProceed.setEnabled(true);

        countStagesLiveData = stageViewModel.getCountStagesForCourse(courseID);
        countStagesLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                countStage = count;
                // Теперь можно использовать countStage внутри OnClickListener
            }
        });

        buttonProceed.setOnClickListener(v -> {
//            usersCoursesViewModel.insert(new UsersCourses(userID,courseID));
            overlay.setVisibility(View.GONE);

            courseProgressViewModel.insert(new CourseProgress(courseID,userID,0,countStagesLiveData.getValue()));
//            recyclerView_stages.setEnabled(true); // При нажатии делаем RecyclerView доступным
//            recyclerView_stages.setAlpha(1.0f); // Возвращаем нормальную прозрачность
        });

        stageViewModel.getAllStagesForCourse(courseID).observe(this, stages -> stageRowAdapter.setStages(stages));

        stageRowAdapter.setOnItemClickListener(new StagePowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Stage stage) {
                Intent intent = new Intent(StagesActivity.this, StagesActivity.class);
                intent.putExtra(STAGE_TO_PARSE, stage.getStageID());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Вызовите метод onBackPressed или finish(), чтобы закрыть активность
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}