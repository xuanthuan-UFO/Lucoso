package com.xuanthuan.lucoso.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdapterViewPagerFood extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> titles= new ArrayList<>();

    public void addFragment(Fragment fm, String title){
        fragments.add(fm);
        titles.add(title);
    }

    public AdapterViewPagerFood(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }
    public CharSequence getPageTitle(int i){
        return titles.get(i);
    }

}
