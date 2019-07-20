package com.example.laundrykh.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.laundrykh.Extras.PreConfig;
import com.example.laundrykh.MainActivity;
import com.example.laundrykh.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private TextView name, email;
    private Button logoutBtn;

    OnLogoutListener logoutListener;

    public interface OnLogoutListener{
        public void LogoutPerform();
    }

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.name);
        //String Name = "Hi, " + MainActivity.preConfig.displayToast();
        //name.setText(Name);

        email = view.findViewById(R.id.email);

//        String Email_cDate = MainActivity.preConfig.getDisplayEmail()
////                +"\n Registered at: " + MainActivity.preConfig.getCreDate();
////        email.setText(Email_cDate);
        //Log.e("created_at: ", c_date);



        logoutBtn = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutListener.LogoutPerform();
            }
        });

        return view;
    } // ending onCreateView

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logoutListener = (OnLogoutListener) activity;

    }
}
