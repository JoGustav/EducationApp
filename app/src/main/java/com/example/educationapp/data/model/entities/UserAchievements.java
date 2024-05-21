package com.example.educationapp.data.model.entities;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.educationapp.BR;

@Entity(tableName = "user_achievements",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "userID",
                        childColumns = "userID",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Achievements.class,
                            parentColumns = "achievementID",
                            childColumns = "achievementID",
                            onDelete = ForeignKey.CASCADE)})
public class UserAchievements extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int userAchievementID;
    @ColumnInfo(index = true)
    private int userID;
    @ColumnInfo(index = true)
    private int achievementID;

    public UserAchievements(int userID, int achievementID) {
        this.userID = userID;
        this.achievementID = achievementID;
    }

    @Bindable
    public int getUserAchievementID() {return userAchievementID;}
    public void setUserAchievementID(int achievementID) {
        this.achievementID = achievementID;
        notifyPropertyChanged(BR.achievementID);
    }
    @Bindable
    public int getUserID() {return userID;}
    public void setUserID(int userID) {
        this.userID = userID;
        notifyPropertyChanged(BR.userID);
    }
    @Bindable
    public int getAchievementID() { return achievementID; }
    public void setAchievementID(int achievementID) {
        this.achievementID = achievementID;
        notifyPropertyChanged(BR.achievementID);
    }
}
