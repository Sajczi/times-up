package com.example.timesup.util;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class ComponentDimensionUtil {

    private WindowManager windowManager;

    public ComponentDimensionUtil(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public int getScreenHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public int getFrameHeight() {
        return getScreenHeight() * 3/4;
    }

}
