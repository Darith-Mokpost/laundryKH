package com.example.laundrykh.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.laundrykh.Extras.PreConfig;
import androidx.fragment.app.Fragment;
import com.example.laundrykh.MainActivity;
import com.example.laundrykh.R;
import com.example.laundrykh.Services.RetrofitClient;
import com.example.laundrykh.Services.ServiceApi;
import com.example.laundrykh.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.laundrykh.MainActivity.preConfig;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private OnLoginFormActivityListener loginFormActivityListener;
    public static ServiceApi serviceApi;
    private EditText emailInput, passwordInput;
    private Button loginBtn, registerTV;

    public interface OnLoginFormActivityListener{
        public void Register();
        public void loginUser(String name);
    }

    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.screen_login, container, false);

        // for login
        emailInput = view.findViewById(R.id.etEmail);
        passwordInput = view.findViewById(R.id.etPassword);
        loginBtn = view.findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        registerTV = view.findViewById(R.id.btnRegister);
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFormActivityListener.Register();
            }
        });
        return view;
    } //ending onCreateView

    private void loginUser() {
        String Email = emailInput.getText().toString();
        String Password = passwordInput.getText().toString();

        serviceApi = RetrofitClient.getApiClient().create(ServiceApi.class);

        if (TextUtils.isEmpty(Email)){
            preConfig.displayToast("Your email is required.");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            preConfig.displayToast("Invalid email");
        } else if (TextUtils.isEmpty(Password)){
            preConfig.displayToast("Password required");
        } else if (Password.length() < 6){
            preConfig.displayToast("Password  may be at least 6 characters long.");
        } else {
            Call<User> userCall = serviceApi.doLogin(Email, Password);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.e("Response",response.message());
                    if (response.body().getResponse().equals("ok")){
                        preConfig.writeLoginStatus(true); // set login status in sharedPreference
                        loginFormActivityListener.loginUser(
                                response.body().getName());
                    } else if (response.body().getResponse().equals("login_failed")){
                        preConfig.displayToast("Error. Login Failed");
                        emailInput.setText("");
                        passwordInput.setText("");
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("Show error",t.getLocalizedMessage());
                }
            });
        }
    } //ending loginUser()

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener = (OnLoginFormActivityListener) activity;
    }

}
