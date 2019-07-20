package com.example.laundrykh.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.laundrykh.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DateDeliveryFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog ddd;
    private EditText editText;

    public DateDeliveryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.deliviry, container, false);

        // Find our View instances

        return view;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }
}
