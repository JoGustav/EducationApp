package com.example.educationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class ReminderFragment extends Fragment {

    private TimePicker timePicker;
    private Button buttonTurnOn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reminder, container, false);

        // Установить русскую локаль
        Locale locale = new Locale("ru");
        Locale.setDefault(locale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        timePicker = view.findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true); // Установить 24-часовой формат

        buttonTurnOn = view.findViewById(R.id.button_turn_on);

        buttonTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                Intent intent = new Intent(getActivity(), DirectionsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}