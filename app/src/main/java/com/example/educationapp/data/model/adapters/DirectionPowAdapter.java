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
import com.example.educationapp.data.model.entities.Direction;

import java.util.ArrayList;
import java.util.List;

public class DirectionPowAdapter extends RecyclerView.Adapter<DirectionPowAdapter.MyViewHolder> {
    private List<Direction> directions = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public DirectionPowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_direction, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DirectionPowAdapter.MyViewHolder holder, int position) {
        Direction currentDirection = directions.get(position);
        holder.direction_title.setText(currentDirection.getTitle());
        String imageName = currentDirection.getLinkIcon();
        int imageResId = holder.itemView.getContext().getResources().getIdentifier(imageName, "drawable", holder.itemView.getContext().getPackageName());

        if (imageResId != 0) { // Проверяем, что ресурс существует
            holder.direction_icon.setImageResource(imageResId);
        } else {
            // Обработка случая, когда ресурс не найден
            holder.direction_icon.setImageResource(R.drawable.comp); // Здесь установите изображение по умолчанию
        };

    }

    @Override
    public int getItemCount() {
        return directions.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDirections(List<Direction> directions){
        this.directions = directions;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView direction_title;
        ImageView direction_icon;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            direction_title = itemView.findViewById(R.id.direction_title);
            direction_icon = itemView.findViewById(R.id.direction_icon);
            row_element = itemView.findViewById(R.id.row_element);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(directions.get(position));
                }
            });
        }
    }
    public interface  OnItemClickListener{
        void onItemClick(Direction course);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}