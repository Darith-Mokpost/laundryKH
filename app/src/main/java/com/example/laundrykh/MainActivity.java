package com.example.laundrykh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sel_location);

        Toolbar locat_bar = findViewById(R.id.locat_bar);
        locat_bar.setTitle("");

    }
}
