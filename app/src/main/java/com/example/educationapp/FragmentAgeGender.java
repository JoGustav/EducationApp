package com.example.educationapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.educationapp.databinding.FragmentAgeGenderBinding;

public class FragmentAgeGender extends Fragment {

    private FragmentAgeGenderBinding binding;
    private EditText editTextAge;
    private Spinner spinnerGender;
    private Button buttonSubmit;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_age_gender, container, false);
        editTextAge = view.findViewById(R.id.editTextAge);
        spinnerGender = view.findViewById(R.id.spinnerGender);
        buttonSubmit = view.findViewById(R.id.buttonSubmit);

        binding = FragmentAgeGenderBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSubmit.setOnClickListener(v ->
                NavHostFragment.findNavController(FragmentAgeGender.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}