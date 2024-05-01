package com.example.educationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.educationapp.data.model.viewmodels.RegistrationViewModel;
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
        binding = FragmentAgeGenderBinding.inflate(inflater, container, false);

        // Используем binding для инициализации элементов
        editTextAge = binding.editTextAge;
        spinnerGender = binding.spinnerGender;
        buttonSubmit = binding.buttonSubmit;

        // Создаем ArrayAdapter с массивом строк
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.gender_array, android.R.layout.simple_spinner_item);
        // Указываем layout для раскрывающегося списка
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к spinner
        spinnerGender.setAdapter(adapter);

        // Устанавливаем "мужской" как значение по умолчанию
        spinnerGender.setSelection(adapter.getPosition("мужской"));

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RegistrationViewModel regVM = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);

        binding.buttonSubmit.setOnClickListener(v -> {
            int age = Integer.parseInt(editTextAge.getText().toString());
            String gender = spinnerGender.getSelectedItem().toString();
            regVM.setUserAge(age); // Сохраняем возраст в ViewModel
            regVM.setUserGender(gender); // Сохраняем гендер в ViewModel
            if (getActivity() instanceof RegistrationActivity) {
                // Вызываем метод addUserToDatabase из RegistrationActivity
                ((RegistrationActivity)getActivity()).addUserToDatabase();
            }
            Intent intent = new Intent(getActivity(), DirectionsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}