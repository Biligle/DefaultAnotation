package com.biligle.moudle1.debug;

import android.view.View;
import android.widget.Toast;

import com.biligle.basemoudle.BaseFragment;
import com.biligle.basemoudle.BaseFragmentActivity;
import com.biligle.basemoudle.widgets.CustomViewPager;
import com.biligle.moudle1.Fragment1;
import com.biligle.moudle1.MainAdapter;
import com.biligle.moudle1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangguoli
 */
public class LayoutForActivity extends BaseFragmentActivity {


    private CustomViewPager vp_main;

    private List<BaseFragment> fragments;
    private MainAdapter mainAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_for_layout;
    }

    @Override
    protected void init() {
        vp_main = (CustomViewPager) findViewById(R.id.vp_main);
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.add(new Fragment1());
        mainAdapter = new MainAdapter(getSupportFragmentManager(), fragments);
        vp_main.setOffscreenPageLimit(4);
        vp_main.setAdapter(mainAdapter);
    }

    public void doClick(View view) {
        Toast.makeText(this,"点击了",Toast.LENGTH_SHORT).show();
    }
}
