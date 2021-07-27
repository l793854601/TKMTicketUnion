package com.example.tkmticketunion.utils;

import android.util.Log;

/**
 * 日志工具类
 */
public class LogUtil {

    /**
     * debug
     */
    private static final int DEBUG_LEVEL = 4;

    /**
     * info
     */
    private static final int INFO_LEVEL = 3;

    /**
     * warning
     */
    private static final int WARNING_LEVEL = 2;

    /**
     * error
     */
    private static final int ERROR_LEVEL = 1;

    /**
     * 当前打印等级，根据测试/生产版本动态调整
     */
    private static int sCurrentLevel = DEBUG_LEVEL;

    public static void d(String tag, String msg) {
        if (sCurrentLevel >= DEBUG_LEVEL) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (sCurrentLevel >= INFO_LEVEL) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sCurrentLevel >= WARNING_LEVEL) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sCurrentLevel >= ERROR_LEVEL) {
            Log.e(tag, msg);
        }
    }
}
