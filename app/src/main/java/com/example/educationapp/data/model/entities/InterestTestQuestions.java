package com.example.educationapp.data.model.entities;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.databinding.BaseObservable;
import androidx.annotation.NonNull;

import com.example.educationapp.BR;

@Entity(tableName = "interest_test_questions")
public class InterestTestQuestions extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int questionID;
    @ColumnInfo(name = "QuestionText")
    private String questionText;
    @ColumnInfo(name = "Type")
    private String type;

    public InterestTestQuestions(String questionText, String type) {
        this.questionText = questionText;
        this.type = type;
    }

    @Bindable
    public int getQuestionID() { return questionID; }
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
        notifyPropertyChanged(BR.questionID);
    }

    @Bindable
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
        notifyPropertyChanged(BR.questionText);
    }

    @Bindable
    public String getType() { return type; }
    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }
}