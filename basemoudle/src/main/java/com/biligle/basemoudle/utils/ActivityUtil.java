package com.biligle.basemoudle.utils;

import android.app.Activity;
import android.widget.Toast;

import java.util.Stack;

/**
 * @Author wangguoli
 * Activity管理工具类
 */
public class ActivityUtil {
    private static Stack<Activity> activityStack;
    private static final ActivityUtil instance = new ActivityUtil();

    public static ActivityUtil getInstance() {
        return instance;
    }

    private ActivityUtil() {
    }

    /**
     * 添加Activity（入栈）
     * true：成功
     */
    public boolean pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        return activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity getActivity() {
        return activityStack != null && activityStack.size() > 0 ? activityStack.lastElement() : null;
    }

    /**
     * 获取指定的Activity
     */
    public Activity getActivity(Class<?> cls) {
        if (activityStack == null || activityStack.size() < 1) return null;
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 结束当前Activity（栈顶的Activity）
     */
    public void finishActivity() {
        if (activityStack == null || activityStack.size() == 0) return;
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity == null) return;
        activityStack.remove(activity);
        if (!activity.isFinishing()) activity.finish();
    }

    /**
     * 结束非指定外的所有activity
     */
    public void finishFilterActivity(Class<?> cls) {
        if (activityStack == null || activityStack.size() < 1) return;
        Activity activity = null;
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i) != null) {
                if (cls != null && activityStack.get(i).getClass().equals(cls)) {
                    activity = activityStack.get(i);
                } else if (!activityStack.get(i).isFinishing()) {
                    activityStack.get(i).finish();
                }
            }
        }
        activityStack.clear();
        activityStack.add(activity);
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (activityStack == null) return;
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (activityStack == null || activityStack.size() < 1) return;
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    private long lastTime = 0;
    public void AppExit(Activity activity) {
        if (System.currentTimeMillis() - lastTime > 2000) {
            lastTime = System.currentTimeMillis();
            Toast.makeText(activity,"再按一次退出",Toast.LENGTH_SHORT).show();
        } else {
            try {
                finishAllActivity();
                System.exit(0);
                // 杀死该应用进程
                android.os.Process.killProcess(android.os.Process.myPid());
            } catch (Exception e) {
            }
        }
    }

}
