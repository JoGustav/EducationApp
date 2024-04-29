package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "interest_test_result")
public class InterestTestResult extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int resultID;
    @ColumnInfo(name = "DirectionID")
    private int directionID;
    @ColumnInfo(name = "UserID")
    private int userID;
    @ColumnInfo(name = "AnswerResult")
    private String answerResult;

    public InterestTestResult(int resultID, int directionID, int userID, String answerResult) {
        this.resultID = resultID;
        this.directionID = directionID;
        this.userID = userID;
        this.answerResult = answerResult;
    }

    @Bindable
    public int getResultID() { return resultID; }
    public void setResultID(int resultID) {
        this.resultID = resultID;
        notifyPropertyChanged(BR.resultID);
    }

    @Bindable
    public int getDirectionID() { return directionID; }
    public void setDirectionID(int directionID) {
        this.directionID = directionID;
        notifyPropertyChanged(BR.directionID);
    }

    @Bindable
    public int getUserID() { return userID; }
    public void setUserID(int userID) {
        this.userID = userID;
        notifyPropertyChanged(BR.userID);
    }

    @Bindable
    public String getAnswerResult() { return answerResult; }
    public void setAnswerResult(String answerResult) {
        this.answerResult = answerResult;
        notifyPropertyChanged(BR.answerResult);
    }
}