package com.build.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.build.myapplication.Adapter.ViewPagerAdapter;
import com.build.myapplication.Fragment.Fragment_AlbumHot;
import com.build.myapplication.Fragment.Fragment_Chude;
import com.build.myapplication.Fragment.Fragment_Home;
import com.build.myapplication.Fragment.Fragment_Search;
import com.build.myapplication.R;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        init();
    }
    private void init() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),getChangingConfigurations());
        viewPagerAdapter.addFragment(new Fragment_Home(),"Trang Chủ");
        viewPagerAdapter.addFragment(new Fragment_Search(),"Tìm Kiếm");
//        viewPagerAdapter.addFragment(new Fragment_Search(),"BXH");
        viewPagerAdapter.addFragment(new Fragment_Chude(),"Chủ Đề");
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //set icon for tab
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_search_24);
//        tabLayout.getTabAt(2).setIcon(R.drawable.ic_ranting_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_topics_24);

    }

    private void AnhXa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}