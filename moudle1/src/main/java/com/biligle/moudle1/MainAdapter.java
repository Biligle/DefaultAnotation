package com.biligle.moudle1;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
        //测试随便写
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }
}
