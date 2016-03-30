package com.example.wcg.cookbook.Utils;

import android.content.Context;

/**
 * android显示器尺寸转换工具类.<p>
 *
 * @author 赵尉尉
 */
public class Dimension {

    private Dimension(){

    }

    /**
     * dp转px
     * @param context 上下文
     * @param value dp值
     * @return
     */
    public static int dp2px(Context context, float value){
        final float scale = Screen.getDensity(context);
        return (int)(value * scale + 0.5f);
    }

    /**
     * px转dp
     * @param context 上下文
     * @param value px值
     * @return
     */
    public static int px2dp(Context context, float value){
        final float scale = Screen.getDensity(context);
        return (int)(value / scale + 0.5f);
    }

    /**
     * sp转px
     * @param context 上下文
     * @param value sp值
     * @return
     */
    public static int sp2px(Context context, float value){
        final float scale = Screen.getDensity(context);
        return (int)(value * scale +0.5f);
    }

    /**
     * px转sp
     * @param context 上下文
     * @param value px值
     * @return
     */
    public static int px2sp(Context context, float value){
        final float scale = Screen.getDensity(context);
        return (int) (value / scale + 0.5f);
    }

}
