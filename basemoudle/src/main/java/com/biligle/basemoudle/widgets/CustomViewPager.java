package com.biligle.basemoudle.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * false：Android 禁止ViewPager 滑动
 * true：允许滑动
 *
 */
public class CustomViewPager extends ViewPager {

	private boolean isScrollable;

	public CustomViewPager(Context context) {
		super(context);
	}

	public CustomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (isScrollable == false) {
			return false;
		} else {
			return super.onTouchEvent(ev);
		}

	}
	@Override
	public void setCurrentItem(int item) {
		//super.setCurrentItem(item);源码
		super.setCurrentItem(item,false);//false表示切换的时候,不经过两个页面的中间页
	}


	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (isScrollable == false) {
			return false;
		} else {
			return super.onInterceptTouchEvent(ev);
		}

	}

	public boolean isScrollable() {
		return isScrollable;
	}

	public void setScrollable(boolean isScrollable) {
		this.isScrollable = isScrollable;
	}

}
