package com.biligle.basemoudle;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author wangguoli
 */
public abstract class BaseFragment<T extends Activity> extends Fragment {
    protected T mContext;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (T) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = getViewLayoutId(inflater, container, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, mView);
        init(mView);
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) mUnbinder.unbind();
    }

    protected abstract View getViewLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 建议初始化工作
     */
    protected abstract void init(View mView);

}
