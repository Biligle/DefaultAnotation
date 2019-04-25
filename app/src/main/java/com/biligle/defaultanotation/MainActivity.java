package com.biligle.defaultanotation;

import android.view.View;
import android.widget.Toast;

import com.biligle.basemoudle.BaseFragment;
import com.biligle.basemoudle.BaseFragmentActivity;
import com.biligle.basemoudle.widgets.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.vp_main)
    CustomViewPager vp_main;

    private List<BaseFragment> fragments;
    private MainAdapter mainAdapter;

    @OnClick(R.id.bt_main)
    public void clickBt_mainActivity(View view) {
        Toast.makeText(this,"点击过了",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.add(new Fragment1());
        mainAdapter = new MainAdapter(getSupportFragmentManager(), fragments);
        vp_main.setOffscreenPageLimit(4);
        vp_main.setAdapter(mainAdapter);
    }

}
