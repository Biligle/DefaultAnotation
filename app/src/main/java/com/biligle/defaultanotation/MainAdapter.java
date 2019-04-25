package com.biligle.defaultanotation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.biligle.basemoudle.BaseFragment;

import java.util.List;

/**
 * @Author wangguoli
 */
public class MainAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public MainAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }
}
