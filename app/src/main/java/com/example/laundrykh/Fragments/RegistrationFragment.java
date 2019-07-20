package com.example.laundrykh.Fragments;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.laundrykh.MainActivity;
import com.example.laundrykh.R;
import com.example.laundrykh.Services.RetrofitClient;
import com.example.laundrykh.Services.ServiceApi;
import com.example.laundrykh.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    private EditText nameInput, emailInput, phoneInput, passwordInput;
    Button regBtn;
    public static ServiceApi serviceApi;
    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_screen_signup, container, false);
        nameInput = view.findViewById(R.id.etNama);
        emailInput = view.findViewById(R.id.etEmail);
        phoneInput = view.findViewById(R.id.etPhone);
        passwordInput = view.findViewById(R.id.etPassword);
        regBtn = view.findViewById(R.id.btnRegister);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                Log.e("reg button", "clicked");
            }
        });
        return view;
    }

    public void registerUser() {
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String password = passwordInput.getText().toString();

        serviceApi = RetrofitClient.getApiClient().create(ServiceApi.class);
        if (TextUtils.isEmpty(name)){
            MainActivity.preConfig.displayToast("Your name is required.");
        } else if (TextUtils.isEmpty(email)){
            MainActivity.preConfig.displayToast("Your email is required.");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            MainActivity.preConfig.displayToast("Invalid email");
        } else if (TextUtils.isEmpty(password)){
            MainActivity.preConfig.displayToast("Password required");
        } else if (password.length() < 6){
            MainActivity.preConfig.displayToast("Create a password at least 6 characters long.");
        }
        else {
            Call<User> userCall = serviceApi.doRegistration(name, email, phone, password);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body().getResponse().equals("inserted")){
                        Log.e("response", response.body().getResponse());
                        nameInput.setText("");
                        emailInput.setText("");
                        phoneInput.setText("");
                        passwordInput.setText("");
                        MainActivity.preConfig.displayToast("Registered Successfully");
                    } else if (response.body().getResponse().equals("exists")){
                        MainActivity.preConfig.displayToast("This email already exists");
                    } else if (response.body().getResponse().equals("error")){
                        MainActivity.preConfig.displayToast("Oops! something went wrong.");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                }
            });
        }

    }

}
