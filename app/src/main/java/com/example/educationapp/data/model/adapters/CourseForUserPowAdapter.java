package com.example.educationapp.data.model.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.R;
import com.example.educationapp.data.model.entities.Course;
import com.example.educationapp.data.model.entities.Direction;
import com.example.educationapp.data.model.repository.CourseProgressRepository;
import com.example.educationapp.data.model.viewmodels.CourseProgressViewModel;
import com.example.educationapp.data.model.viewmodels.CourseViewModel;

import java.util.ArrayList;
import java.util.List;

public class CourseForUserPowAdapter extends RecyclerView.Adapter<CourseForUserPowAdapter.MyViewHolder> {
    private List<Course> courses = new ArrayList<>();
    private CourseProgressRepository courseProgressRepository;
    private int userID;
    private LiveData<Integer> countCompletedStagesLiveData;
    private LiveData<Integer> countTotalStagesLiveData;
    private int countCompletedStages;
    private int countTotalStages;
    private OnItemClickListener listener;
    private LifecycleOwner lifecycleOwner;

    public CourseForUserPowAdapter(int userID, LifecycleOwner lifecycleOwner, CourseProgressRepository repository) {
        this.userID = userID;
        this.lifecycleOwner = lifecycleOwner;
        this.courseProgressRepository = repository;
    }
    @NonNull
    @Override
    public CourseForUserPowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_course_user, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseForUserPowAdapter.MyViewHolder holder, int position) {
        Course currentCourse = courses.get(position);
        holder.course_title.setText(currentCourse.getTitle());
        holder.course_desc.setText(currentCourse.getDescription());

        courseProgressRepository.getCompletedStagesCountForCourse(currentCourse.getCourseID(), userID).observe(lifecycleOwner, count -> {
            countCompletedStages = count;
            updateProgressText(holder.progress_percentage);
        });

        // Наблюдение за LiveData с общим количеством этапов
        courseProgressRepository.getTotalStagesCountForCourse(currentCourse.getCourseID(),userID).observe(lifecycleOwner, count -> {
            countTotalStages = count;
            updateProgressText(holder.progress_percentage);
        });

    }
    private void updateProgressText(TextView progressText) {
        progressText.setText(String.valueOf(countCompletedStages) + " / " + String.valueOf(countTotalStages));
    }
    @Override
    public int getItemCount() {
        return courses.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCourses(List<Course> courses){
        this.courses = courses;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView course_title, course_desc, progress_percentage;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            progress_percentage = itemView.findViewById(R.id.progress_percentage);
            course_title = itemView.findViewById(R.id.course_title);
            course_desc = itemView.findViewById(R.id.course_desc);
            row_element = itemView.findViewById(R.id.row_element);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(courses.get(position));
                }
            });
        }
    }
    public interface  OnItemClickListener{
        void onItemClick(Course course);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}