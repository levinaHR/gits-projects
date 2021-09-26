package com.levinahr.asus.catdoption.activity.ui.addCat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.levinahr.asus.catdoption.R;

public class AddCatFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private AddCatViewModel addCatViewModel;
    private EditText editName;
    private EditText editAge;
    private EditText editBreed;
    private EditText editDescription;
    private Button btnAdd;
    private Spinner spinner;
    private static final String[] items = new String[]{"Male", "Female"};
    private int gender;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addCatViewModel = ViewModelProviders.of(this).get(AddCatViewModel.class);
        View view = inflater.inflate(R.layout.fragment_add_cat, container, false);

        editName = view.findViewById(R.id.edit_name_cat);
        editAge = view.findViewById(R.id.edit_age_cat);
        editBreed = view.findViewById(R.id.edit_breed_cat);
        editDescription = view.findViewById(R.id.edit_description_cat);

        spinner = view.findViewById(R.id.edit_gender_cat);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnAdd = view.findViewById(R.id.btn_add_cat);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCatViewModel.postCatDatas(
                        editName.getText().toString().trim(),
                        editAge.getText().toString().trim(),
                        gender,
                        editBreed.getText().toString().trim(),
                        editDescription.getText().toString().trim()
                ).observe(getActivity(), catResponse -> {
                    Toast.makeText(getActivity(), "Cat added successfully", Toast.LENGTH_SHORT).show();
                });
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (position) {
            case 0:
                gender = 0;
                break;
            case 1:
                gender = 1;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}