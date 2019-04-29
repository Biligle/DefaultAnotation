package com.biligle.moudle1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biligle.basemoudle.BaseFragment;


/**
 * A fragment with a Google +1 button.
 */
public class Fragment1 extends BaseFragment {

    private TextView tv_fragment1;
    private String content;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * 避免数据丢失
     */
    public static Fragment1 newInstance(Bundle mBundle) {
        Fragment1 instance = new Fragment1();
        instance.setArguments(mBundle);
        return instance;
    }

    @Override
    protected View getViewLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_fragment1,container,false);
        return mView;
    }

    @Override
    public void init(View mView) {
        tv_fragment1 = (TextView) mView.findViewById(R.id.tv_fragment1);
        tv_fragment1.setText("这是Fragment1哈哈哈哈");
    }
}
