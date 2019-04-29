package com.biligle.basemoudle;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.biligle.basemoudle.utils.ActivityUtil;

/**
 * @Author wangguoli
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ActivityUtil.getInstance().pushActivity(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.getInstance().finishActivity(this);
    }

    /**
     * 传入layoutId
     */
    protected abstract int getLayoutId();

    /**
     * 建议初始化工作
     */
    protected abstract void init();
}
