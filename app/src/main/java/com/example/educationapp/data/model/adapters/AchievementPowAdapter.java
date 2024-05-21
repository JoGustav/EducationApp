package com.example.educationapp.data.model.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.R;
import com.example.educationapp.data.model.entities.Achievements;
import com.example.educationapp.data.model.entities.Course;
import com.example.educationapp.data.model.repository.CourseProgressRepository;
import com.example.educationapp.data.model.repository.UserAchievementsRepository;

import java.util.ArrayList;
import java.util.List;

public class AchievementPowAdapter extends RecyclerView.Adapter<AchievementPowAdapter.MyViewHolder> {

    private List<Achievements> achievements = new ArrayList<>();
    private UserAchievementsRepository userAchievementsRepository;
    private LifecycleOwner lifecycleOwner;
    private int userID;

    public AchievementPowAdapter(int userID, LifecycleOwner lifecycleOwner, UserAchievementsRepository userAchievementsRepository) {
        this.userID = userID;
        this.lifecycleOwner = lifecycleOwner;
        this.userAchievementsRepository = userAchievementsRepository;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_achievement, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Achievements currentAchievement = achievements.get(position);
        holder.title.setText(currentAchievement.getTitle());

        String imageName = currentAchievement.getLinkIcon();
        int imageResId = holder.itemView.getContext().getResources().getIdentifier(imageName, "drawable", holder.itemView.getContext().getPackageName());

        if (imageResId != 0) { // Проверяем, что ресурс существует
            holder.icon.setImageResource(imageResId);
        } else {
            // Обработка случая, когда ресурс не найден
            holder.icon.setImageResource(R.drawable.comp); // Здесь установите изображение по умолчанию
        }
    }

    @Override
    public int getItemCount() {
        return achievements.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setAchievements(List<Achievements> achievements){
        this.achievements = achievements;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_achievement);
            icon = itemView.findViewById(R.id.imageView_achievement);
        }
    }
}

