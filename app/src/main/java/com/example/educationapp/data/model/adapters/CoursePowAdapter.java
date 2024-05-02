package com.example.educationapp.data.model.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.R;
import com.example.educationapp.data.model.entities.Course;
import com.example.educationapp.data.model.entities.Direction;

import java.util.ArrayList;
import java.util.List;

public class CoursePowAdapter extends RecyclerView.Adapter<CoursePowAdapter.MyViewHolder> {
    private List<Course> courses = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public CoursePowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_course, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursePowAdapter.MyViewHolder holder, int position) {
        Course currentCourse = courses.get(position);
        holder.course_title.setText(currentCourse.getTitle());
        holder.course_desc.setText(currentCourse.getDescription());

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
        TextView course_title, course_desc;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
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