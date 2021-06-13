package com.example.hoangthequyen_project.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

//import android.app.Fragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int pageNum;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNum = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFrg();
            case 1:
                return new SearchFrg();
            case 2:
                return new NotiFrg();
            default:
                return new ProfileFrg();
        }
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}

