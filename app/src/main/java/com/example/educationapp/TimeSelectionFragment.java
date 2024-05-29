package com.example.educationapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TimeSelectionFragment extends Fragment {
    private RadioGroup radioGroup;
    private Button buttonContinue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_selection, container, false);

        radioGroup = view.findViewById(R.id.radioGroup);
        buttonContinue = view.findViewById(R.id.button_continue);

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = view.findViewById(selectedId);

                NavHostFragment.findNavController(TimeSelectionFragment.this)
                        .navigate(R.id.action_TimeFragment_to_ReminderFragment);
            }
        });

        return view;
    }
}
