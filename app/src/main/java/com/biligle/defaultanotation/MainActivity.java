package com.biligle.defaultanotation;

import android.widget.Toast;

import com.biligle.basemoudle.BaseFragment;
import com.biligle.basemoudle.BaseFragmentActivity;
import com.biligle.basemoudle.widgets.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 组件化模式下，部分导包会报错，忽略掉
 * 将config.gradle里的isMoudle改成false就好了
 */
public class MainActivity extends BaseFragmentActivity {

    private CustomViewPager vp_main;

    private List<BaseFragment> fragments;
//    private MainAdapter mainAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        vp_main = (CustomViewPager) findViewById(R.id.vp_main);
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
//        fragments.add(new Fragment1());
//        mainAdapter = new MainAdapter(getSupportFragmentManager(), fragments);
//        vp_main.setOffscreenPageLimit(4);
//        vp_main.setAdapter(mainAdapter);
        Toast.makeText(this, "这是v1.0.2", Toast.LENGTH_SHORT).show();
    }

}
