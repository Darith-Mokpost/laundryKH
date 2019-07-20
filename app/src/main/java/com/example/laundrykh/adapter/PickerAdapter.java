package com.example.laundrykh.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.laundrykh.Fragments.DateDeliveryFragment;
import com.example.laundrykh.Fragments.DatePickerFragment;
import com.example.laundrykh.R;

public class PickerAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGES = 2;
    Fragment dateDeliveryFragment;
    Fragment datePickerFragment;

    public PickerAdapter(FragmentManager fm) {
        super(fm);
        datePickerFragment = new DatePickerFragment();
        dateDeliveryFragment = new DateDeliveryFragment();

    }
    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return datePickerFragment;
            case 1:
            default:
                return dateDeliveryFragment;

        }
    }
    public int getTitle(int position) {
        switch(position) {
            case 0:
                return R.string.tab_title_pickup;
            case 1:
            default:
                return R.string.tab_title_delivery;

        }
    }

}
