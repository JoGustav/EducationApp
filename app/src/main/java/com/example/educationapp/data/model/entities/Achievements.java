package com.example.educationapp.data.model.entities;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.educationapp.BR;

@Entity(tableName = "achievements")
public class Achievements extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int achievementID;
    @ColumnInfo(name = "Title")
    private String title;
    @ColumnInfo(name = "Description")
    private String description;
    @ColumnInfo(name = "CriteriaType")
    private String criteriaType;
    @ColumnInfo(name = "LinkIcon")
    private String linkIcon;
    public Achievements(String title, String description, String criteriaType, String linkIcon) {
        this.title = title;
        this.description = description;
        this.criteriaType = criteriaType;
        this.linkIcon = linkIcon;
    }
    @Bindable
    public int getAchievementID() { return achievementID; }
    public void setAchievementID(int achievementID) {
        this.achievementID = achievementID;
        notifyPropertyChanged(BR.achievementID);
    }
    @Bindable
    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }
    @Bindable
    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }
    @Bindable
    public String getCriteriaType() { return criteriaType; }
    public void setCriteriaType(String criteriaType) {
        this.criteriaType = criteriaType;
        notifyPropertyChanged(BR.criteriaType);
    }
    @Bindable
    public String getLinkIcon() { return linkIcon;}
    public void setLinkIcon(String linkIcon) {
        this.linkIcon = linkIcon;
        notifyPropertyChanged(BR.linkIcon);
    }

}
