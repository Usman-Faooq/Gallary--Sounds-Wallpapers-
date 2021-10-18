package com.example.tabtemplets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem soundtab, wallpapertab;
    ViewPager viewPager;
    pagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    tabLayout = findViewById(R.id.tablayout);
    soundtab = findViewById(R.id.soundtab);
    wallpapertab = findViewById(R.id.wallpapertab);
    viewPager = findViewById(R.id.viewpager);

    adapter = new pagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
    viewPager.setAdapter(adapter);

    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
            if (tab.getPosition()==0 || tab.getPosition()==1){
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    });
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



    }
}