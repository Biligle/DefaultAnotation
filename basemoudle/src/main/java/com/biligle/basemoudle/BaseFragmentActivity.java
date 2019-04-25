package com.biligle.basemoudle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.biligle.basemoudle.utils.ActivityUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author wangguoli
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        ActivityUtil.getInstance().pushActivity(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) mUnbinder.unbind();
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
