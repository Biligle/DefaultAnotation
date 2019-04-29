package com.biligle.basemoudle;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Author wangguoli
 */
public abstract class BaseFragment extends Fragment {
    protected Activity mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = getViewLayoutId(inflater, container, savedInstanceState);
        init(mView);
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract View getViewLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 建议初始化工作
     */
    protected abstract void init(View mView);

}
