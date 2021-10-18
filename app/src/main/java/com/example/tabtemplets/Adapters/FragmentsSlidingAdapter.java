package com.example.tabtemplets.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tabtemplets.Fragments.SoundFragment;
import com.example.tabtemplets.Fragments.WallpaperFragment;

public class FragmentsSlidingAdapter extends FragmentPagerAdapter {
    int itemcount;

    public FragmentsSlidingAdapter(@NonNull FragmentManager fm, int itemcount) {
        super(fm, itemcount);
        this.itemcount = itemcount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new SoundFragment();
            case 1:
                return new WallpaperFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return itemcount;
    }
}
