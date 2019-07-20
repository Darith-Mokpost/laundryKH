package com.example.laundrykh.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.example.laundrykh.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

/**
 * A app {@link Fragment} subclass.
 */
public class DatePickerFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog dpd;
    private EditText editText;

    public DatePickerFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pickup, container, false);

        // Find our View instances
        editText = view.findViewById(R.id.datePick);

        editText.setFocusable(false);
        editText.setClickable(true);
        editText.setKeyListener(null);

        view.findViewById(R.id.datePick).setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            new android.app.DatePickerDialog(
                    requireActivity(),
                    (view1, year, month, dayOfMonth) -> Log.d("Orignal", "Got clicked"),
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            ).show();
        });

        // Show a datepicker when the dateButton is clicked
        editText.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            /*
            It is recommended to always create a new instance whenever you need to show a Dialog.
            The sample app is reusing them because it is useful when looking for regressions
            during testing
             */
            if (dpd == null) {
                dpd = DatePickerDialog.newInstance(
                        DatePickerFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
            } else {
                dpd.initialize(
                        DatePickerFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
            }

            //switchOrientation
            dpd.setScrollOrientation(DatePickerDialog.ScrollOrientation.HORIZONTAL);

            //limitSelectableDays
//            Calendar[] days = new Calendar[13];
//            for (int i = -6; i < 7; i++) {
//                Calendar day = Calendar.getInstance();
//                day.add(Calendar.DAY_OF_MONTH, i * 2);
//                days[i + 6] = day;
//            }
//            dpd.setSelectableDays(days);

            //set title
            //dpd.setTitle("DatePickUp");

            //set to dark mode
            dpd.setThemeDark(false);
            dpd.setAccentColor(Color.parseColor("#2AAAFF"));
            dpd.setOnCancelListener(dialog -> {
                Log.d("DatePickerDialog", "Dialog was cancelled");
                dpd = null;
            });
            dpd.show(requireFragmentManager(), "Datepickerdialog");
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dpd = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) requireFragmentManager().findFragmentByTag("Datepickerdialog");
        if(dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        editText.setText(date);
        dpd = null;
    }
}
