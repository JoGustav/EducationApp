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
import com.example.educationapp.data.model.entities.Stage;

import java.util.ArrayList;
import java.util.List;

public class StagePowAdapter extends RecyclerView.Adapter<StagePowAdapter.MyViewHolder> {
    private List<Stage> stages = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public StagePowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_stage, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StagePowAdapter.MyViewHolder holder, int position) {
        Stage currentStage = stages.get(position);
        holder.stage_title.setText(currentStage.getTitle());
        holder.stage_desc.setText(currentStage.getDescription());

    }
    @Override
    public int getItemCount() {
        return stages.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setStages(List<Stage> stages){
        this.stages = stages;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView stage_title, stage_desc;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stage_title = itemView.findViewById(R.id.stage_title);
            stage_desc = itemView.findViewById(R.id.stage_desc);
            row_element = itemView.findViewById(R.id.row_element);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(stages.get(position));
                }
            });
        }
    }
    public interface  OnItemClickListener{
        void onItemClick(Stage stage);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}