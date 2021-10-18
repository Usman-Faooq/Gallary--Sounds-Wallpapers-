package com.example.tabtemplets;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pagerAdapter extends FragmentPagerAdapter {
    int itemcount;

    public pagerAdapter(@NonNull FragmentManager fm, int itemcount) {
        super(fm, itemcount);
        this.itemcount = itemcount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new sound_frag();
            case 1:
                return new wallpaper_frag();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return itemcount;
    }
}
