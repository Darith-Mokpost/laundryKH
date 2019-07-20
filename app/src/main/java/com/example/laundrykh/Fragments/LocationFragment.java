package com.example.laundrykh.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.laundrykh.MainActivity;
import com.example.laundrykh.R;

public class LocationFragment extends Fragment {


    OnlogoutListener logoutListener;
    public interface OnlogoutListener{
        public void LogoutPerform();
    }

    public LocationFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_welcome, container, false);
        View view = inflater.inflate(R.layout.sel_location, container, false);
        return view;
        }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logoutListener = (OnlogoutListener) activity;
    }
}
