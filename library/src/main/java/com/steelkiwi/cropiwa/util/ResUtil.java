package com.steelkiwi.cropiwa.util;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author yarolegovich https://github.com/yarolegovich
 * 04.02.2017.
 */
public class ResUtil {

    private Context context;

    public ResUtil(Context context) {
        this.context = context;
    }

    @ColorInt
    public int color(@ColorRes int colorRes) {
        return ContextCompat.getColor(context, colorRes);
    }

    public int dimen(@DimenRes int dimRes) {
        return Math.round(context.getResources().getDimension(dimRes));
    }

    public static int getWidthScreen(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    public static int getHeightScreen(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
