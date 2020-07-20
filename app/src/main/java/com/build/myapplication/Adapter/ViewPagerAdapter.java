package com.build.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    private ArrayList<Fragment> fragmentArray = new ArrayList<>();
    private ArrayList<String> arrayTitle = new ArrayList<>();


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArray.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArray.size();
    }

    public void addFragment (Fragment fragment,String title) {
        fragmentArray.add(fragment);
        arrayTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }
}
