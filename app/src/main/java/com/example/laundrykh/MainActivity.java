package com.example.laundrykh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.laundrykh.Extras.PreConfig;
import com.example.laundrykh.Fragments.LoginFragment;
import com.example.laundrykh.Fragments.ProfileFragment;
import com.example.laundrykh.Fragments.RegistrationFragment;
import com.example.laundrykh.Services.ServiceApi;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFormActivityListener, ProfileFragment.OnLogoutListener {

    public static PreConfig preConfig;
    public static ServiceApi serviceApi;

    FrameLayout container_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container_layout = findViewById(R.id.fragment_container);
        preConfig = new PreConfig(this);

        //Log.e("created_at: ", c_date);

        if (container_layout != null){
            if (savedInstanceState != null){
                return;
            }

            //check login status from sharedPreference
            if (preConfig.readLoginStatus()){
                //when true
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new ProfileFragment())
                        .commit();
            } else {
                // when get false
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new LoginFragment())
                        .commit();
            }
        }

    } // ending onCreate


    // overridden from MyInterface
    @Override
    public void Register() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new RegistrationFragment())
                .addToBackStack(null)
                .commit();
    }
    @Override
    public void loginUser(String name) {
        preConfig.writeName(name);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ProfileFragment())
                .commit();
    }
    @Override
    public void LogoutPerform() {
        preConfig.writeLoginStatus(false);
        preConfig.writeName("User");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();
    }
}
