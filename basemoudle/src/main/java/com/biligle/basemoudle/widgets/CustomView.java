package com.biligle.basemoudle.widgets;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.widget.AppCompatButton;

import java.lang.reflect.Method;

/**
 * @Author wangguoli
 */
public class CustomView extends AppCompatButton {

    private int lastX, lastY;
    private int offsetX, offsetY;
    private int maxWidth, maxHeight;
    private boolean mInPortrait;

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
        int navigationBar = getNavigationBarHeight(context);
        int status = getStatusHeight(context);
        int title = getTitleHeight((Activity) context);
        int actionBar = getActionBarHeight(context);
        maxHeight = dm.heightPixels - status - title;
        /*---------------------------------------初始化高度结束——————————————————————————————————————*/
        mInPortrait = (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
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
                offsetX = x - lastX;
                offsetY = y - lastY;
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
            case MotionEvent.ACTION_UP:
                if (offsetX == 0 && offsetY == 0) {
                    //主动触发（逻辑：当空件移动了就不触发点击事件）
                    performClick();
                }
                offsetX = offsetY = 0;
                break;
        }
        return true;
    }

    @Override
    public boolean performClick() {
        //处理外部点击事件，防止触发不了
        return super.performClick();
    }

    /**
     * 获取导航栏高度
     */
    public int getNavigationBarHeight(Context context) {
        Resources res = context.getResources();
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (hasNavBar(context)) {
                String key;
                if (mInPortrait) {
                    key = "navigation_bar_height";
                } else {
                    key = "navigation_bar_height_landscape";
                }
                return getInternalDimensionSize(res, key);
            }
        }
        return result;
    }

    /**
     * 获取导航栏宽度
     */
    private int getNavigationBarWidth(Context context) {
        Resources res = context.getResources();
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (hasNavBar(context)) {
                return getInternalDimensionSize(res, "navigation_bar_width");
            }
        }
        return result;
    }

    //通过此方法获取资源对应的像素值
    private int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    //通过此方法判断是否存在navigation bar
    private boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // 查看是否有通过系统属性来控制navigation bar。
            if ("1".equals(getNavBarOverride())) {
                hasNav = false;
            } else if ("0".equals(getNavBarOverride())) {
                hasNav = true;
            }
            return hasNav;
        } else {
            //可通过此方法来查看设备是否存在物理按键(menu,back,home键)。
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    // 安卓系统允许修改系统的属性来控制navigation bar的显示和隐藏，此方法用来判断是否有修改过相关属性。
    // (修改系统文件，在build.prop最后加入qemu.hw.mainkeys=1即可隐藏navigation bar)
    // 相关属性模拟器中有使用。
    // 当返回值等于"1"表示隐藏navigation bar，等于"0"表示显示navigation bar。
    private String getNavBarOverride() {
        String isNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                isNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
                isNavBarOverride = null;
            }
        }
        return isNavBarOverride;
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
    public static int getActionBarHeight(Context context) {
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                    actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actionBarHeight;
    }
}
