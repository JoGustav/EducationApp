package com.example.educationapp.data.model.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

        String imageName = currentStage.getLinkIcon();
        int imageResId = holder.itemView.getContext().getResources().getIdentifier(imageName, "drawable", holder.itemView.getContext().getPackageName());

        if (imageResId != 0) { // Проверяем, что ресурс существует
            holder.stage_icon.setImageResource(imageResId);
        } else {
            // Обработка случая, когда ресурс не найден
            holder.stage_icon.setImageResource(R.drawable.comp); // Здесь установите изображение по умолчанию
        }
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
        TextView stage_title;
        ImageView stage_icon;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stage_title = itemView.findViewById(R.id.stage_title);
            row_element = itemView.findViewById(R.id.row_element);
            stage_icon = itemView.findViewById(R.id.stage_icon);
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