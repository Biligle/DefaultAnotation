package com.biligle.defaultanotation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biligle.basemoudle.BaseFragment;

import butterknife.BindView;

/**
 * A fragment with a Google +1 button.
 */
public class Fragment1 extends BaseFragment<MainActivity> {

    @BindView(R.id.tv_fragment1)
    TextView tv_fragment1;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    protected View getViewLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_fragment1,container,false);
        return mView;
    }

    @Override
    public void init(View mView) {
        tv_fragment1.setText("这是Fragment1");
    }
}
