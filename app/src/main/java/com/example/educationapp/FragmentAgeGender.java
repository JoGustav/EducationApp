package com.example.educationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.educationapp.data.model.viewmodels.RegistrationViewModel;
import com.example.educationapp.databinding.FragmentAgeGenderBinding;

public class FragmentAgeGender extends Fragment {

    private FragmentAgeGenderBinding binding;
    private Spinner spinnerGender;
    private Button buttonSubmit;
    private EditText editTextName;
    private SeekBar seekBarAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonBoy;
    private RadioButton radioButtonGirl;
    private TextView textViewAge;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentAgeGenderBinding.inflate(inflater, container, false);

        // Используем binding для инициализации элементов
        editTextName = binding.editTextName;
        seekBarAge = binding.childsAge; // Инициализация SeekBar
        radioGroupGender = binding.genderGroup; // Инициализация RadioGroup
        radioButtonBoy = binding.genderBoy; // Инициализация RadioButton для мальчика
        radioButtonGirl = binding.genderGirl;
        buttonSubmit = binding.buttonSubmit;
        textViewAge = binding.textViewAge;


        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RegistrationViewModel regVM = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);

        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Получаем корневой элемент вашего макета
                View rootView = getActivity().getWindow().getDecorView();

                if (checkedId == R.id.gender_boy) {
                    // Устанавливаем фон для мальчика
                    rootView.setBackgroundResource(R.drawable.boy);
                } else if (checkedId == R.id.gender_girl) {
                    // Устанавливаем фон для девочки
                    rootView.setBackgroundResource(R.drawable.boy);
                }
                // Добавьте другие условия для других RadioButton
            }
        });
        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Обновление текста в TextViewAge при изменении значения SeekBar
                textViewAge.setText("Возраст: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Метод, который вызывается, когда пользователь начинает перетаскивание ползунка
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Метод, который вызывается, когда пользователь заканчивает перетаскивание ползунка
            }
        });


        binding.buttonSubmit.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            int age = seekBarAge.getProgress();
            int selectedGenderId = radioGroupGender.getCheckedRadioButtonId(); // Получаем ID выбранного RadioButton
            String gender;
            if (selectedGenderId == R.id.gender_boy) {
                gender = "Мальчик";
            } else if (selectedGenderId == R.id.gender_girl) {
                gender = "Девочка";
            } else {
                gender = "Не указано";
            }
            regVM.setUserAge(age); // Сохраняем возраст в ViewModel
            regVM.setUserGender(gender); // Сохраняем гендер в ViewModel
            regVM.setUserName(name);
            if (getActivity() instanceof RegistrationActivity) {
                // Вызываем метод addUserToDatabase из RegistrationActivity
                ((RegistrationActivity)getActivity()).addUserToDatabase();
            }
            NavHostFragment.findNavController(FragmentAgeGender.this)
                    .navigate(R.id.action_SecondFragment_to_TimeFragment);
//            Intent intent = new Intent(getActivity(), DirectionsActivity.class);
//            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}