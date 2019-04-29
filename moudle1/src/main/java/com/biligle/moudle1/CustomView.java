package com.biligle.moudle1;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import androidx.appcompat.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

/**
 * @Author wangguoli
 */
public class CustomView extends AppCompatButton {

    private int lastX, lastY;
    private int maxWidth, maxHeight;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        /*---------------------------------------初始化高度开始——————————————————————————————————————*/
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = manager.getDefaultDisplay();
        display.getMetrics(dm);
        maxWidth = dm.widthPixels;
        maxHeight = dm.heightPixels - getNavigationBarHeight() - getStatusHeight(context) - getTitleHeight((Activity) context) - getActionBatHeight(context);
        /*---------------------------------------初始化高度结束——————————————————————————————————————*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int MIN_LEFT = 0;//min左边距
        int MAX_LEFT = maxWidth - getWidth();//max左边距
        int MIN_TOP = 0;//min上边距
        int MAX_TOP = maxHeight - getHeight();//max上边距
        int MIN_RIGHT = getWidth();//min右边距
        int MAX_RIGHT = maxWidth;//max右边距
        int MIN_BOTTOM = getHeight();//min下边距
        int MAX_BOTTOM = maxHeight;//max下边距
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                int left = getLeft() + offsetX < MIN_LEFT ? MIN_LEFT
                        : getLeft() + offsetX > MAX_LEFT ? MAX_LEFT
                        : getLeft() + offsetX;
                int top = getTop() + offsetY < MIN_TOP ? MIN_TOP
                        : getTop() + offsetY > MAX_TOP ? MAX_TOP
                        : getTop() + offsetY;
                int right = getRight() + offsetX < MIN_RIGHT ? MIN_RIGHT
                        : getRight() + offsetX > MAX_RIGHT ? MAX_RIGHT
                        : getRight() + offsetX;
                int bottom = getBottom() + offsetY < MIN_BOTTOM ? MIN_BOTTOM
                        : getBottom() + offsetY > MAX_BOTTOM ? MAX_BOTTOM
                        : getBottom() + offsetY;
                layout(left, top, right, bottom);
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 获取导航栏高度
     */
    public int getNavigationBarHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId > 0) {
            if (resources.getBoolean(resourceId)) {//导航栏是否显示
                resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    return resources.getDimensionPixelSize(resourceId);//获取高度
                }
            }
        }
        return 0;
    }

    /**
     * 获得状态栏的高度
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 标题栏高度
     */
    public static int getTitleHeight(Activity activity) {
        int titleHeight = 0;
        try {
            titleHeight = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return titleHeight;
    }

    /**
     * 获取ActionBar高度
     */
    public static int getActionBatHeight(Context context) {
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        try {
            if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actionBarHeight;
    }
}
