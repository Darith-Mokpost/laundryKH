package com.example.laundrykh.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.laundrykh.R;
import com.example.laundrykh.adapter.PickerAdapter;
import com.google.android.material.tabs.TabLayout;

public class SelDateTime extends AppCompatActivity {
    ViewPager viewPager;
    PickerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_date_time);

        adapter = new PickerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        setSupportActionBar(findViewById(R.id.toolbar));
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        for(int i=0; i < adapter.getCount(); i++) //noinspection ConstantConditions
            tabLayout.getTabAt(i).setText(adapter.getTitle(i));

    }
}
