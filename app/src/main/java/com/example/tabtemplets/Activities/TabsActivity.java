package com.example.tabtemplets.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tabtemplets.Adapters.FragmentsSlidingAdapter;
import com.example.tabtemplets.FavSound;
import com.example.tabtemplets.FavWallpaper;
import com.example.tabtemplets.MainActivity;
import com.example.tabtemplets.R;
import com.example.tabtemplets.UserProfile;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TabsActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    TabLayout tabLayout;
    TabItem soundtab, wallpapertab;
    ViewPager viewPager;
    FragmentsSlidingAdapter adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //0 for sound and 1 for wallpaper
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        recreate();
                        break;
                    case R.id.fav:
                        if(flag == 0){
                            Intent i = new Intent(TabsActivity.this, FavSound.class);
                            startActivity(i);
                        }else{
                            Intent i = new Intent(TabsActivity.this, FavWallpaper.class);
                            startActivity(i);
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.profile:
                        Intent i = new Intent(TabsActivity.this, UserProfile.class);
                        startActivity(i);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.logout:
                        editor.clear();
                        editor.apply();
                        finish();
                        break;


                }
                return true;
            }
        });

        tabLayout = findViewById(R.id.tablayout);
        soundtab = findViewById(R.id.soundtab);
        wallpapertab = findViewById(R.id.wallpapertab);
        viewPager = findViewById(R.id.viewpager);

        sharedPreferences = getSharedPreferences("LoginSession", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        adapter = new FragmentsSlidingAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0 || tab.getPosition()==1){
                    if (tab.getPosition() == 0){
                        flag = 0;
                    }
                    if(tab.getPosition() == 1){
                        flag = 1;
                    }

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
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutbtn:
                editor.clear();
                editor.apply();
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
  */



}