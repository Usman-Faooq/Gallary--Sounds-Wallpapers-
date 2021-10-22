package com.example.tabtemplets.Activities;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.tabtemplets.Adapters.FragmentsSlidingAdapter;
import com.example.tabtemplets.Fragments.FavouriteSound;
import com.example.tabtemplets.Fragments.FavouriteWallpaper;
import com.example.tabtemplets.Fragments.UpdateForm;
import com.example.tabtemplets.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TabsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem soundtab, wallpapertab;
    ViewPager viewPager;
    FragmentsSlidingAdapter adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    BottomNavigationView navigationView;

    //0 for sound and 1 for wallpaper
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        tabLayout = findViewById(R.id.tablayout);
        soundtab = findViewById(R.id.soundtab);
        wallpapertab = findViewById(R.id.wallpapertab);
        viewPager = findViewById(R.id.viewpager);
        navigationView = findViewById(R.id.bottom_navigation);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedfragment = null;
                switch(item.getItemId()){
                    case R.id.home:
                        recreate();
                        break;
                    case R.id.category:
                        break;
                    case R.id.add:
                        break;
                    case R.id.fav:
                        if (flag == 0){
                            FavouriteSound sound = new FavouriteSound();
                            sound.show(getSupportFragmentManager(), "Favourite Sound");
                        }else{
                            FavouriteWallpaper wallpaper = new FavouriteWallpaper();
                            wallpaper.show(getSupportFragmentManager(), "Favourite Wallpapers");
                        }
                        break;
                    case R.id.profile:
                        UpdateForm form = new UpdateForm();
                        form.show(getSupportFragmentManager(), "Update Form");
                        break;
                }
                return false;
            }
        });


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
}