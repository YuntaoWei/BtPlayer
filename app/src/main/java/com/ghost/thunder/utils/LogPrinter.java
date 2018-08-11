package com.ghost.thunder.utils;

import android.util.Log;

/**
 * Created by wyt on 2018/8/9.
 */

public class LogPrinter {

    private static final int LOG_E_LEVEL = 5;
    private static final int LOG_D_LEVEL = 4;
    private static final int LOG_W_LEVEL = 3;
    private static final int LOG_I_LEVEL = 2;
    private static final int LOG_V_LEVEL = 1;

    private static final int LOG_DEFAULT_LEVEL = 2;

    public static void i(String tag, String msg) {
        if(LOG_I_LEVEL >= LOG_DEFAULT_LEVEL)
            Log.i(tag, msg);
    }

    public static void i_with_trace(String tag, String msg) {
        i(tag, msg + "\n" + Log.getStackTraceString(new Throwable()));
    }

    public static void w(String tag, String msg) {
        if(LOG_W_LEVEL >= LOG_DEFAULT_LEVEL)
            Log.w(tag, msg);
    }

    public static void w_with_trace(String tag, String msg) {
        w(tag, msg + "\n" + Log.getStackTraceString(new Throwable()));
    }

    public static void d(String tag, String msg) {
        if(LOG_D_LEVEL >= LOG_DEFAULT_LEVEL)
            Log.d(tag, msg);
    }

    public static void d_with_trace(String tag, String msg) {
        d(tag, msg + "\n" + Log.getStackTraceString(new Throwable()));
    }

    public static void e(String tag, String msg) {
        if(LOG_E_LEVEL >= LOG_DEFAULT_LEVEL)
            Log.e(tag, msg);
    }

    public static void e_with_trace(String tag, String msg) {
        e(tag, msg + "\n" + Log.getStackTraceString(new Throwable()));
    }

    public static void v(String tag, String msg) {
        if(LOG_V_LEVEL >= LOG_DEFAULT_LEVEL)
            Log.v(tag, msg);
    }

    public static void v_with_trace(String tag, String msg) {
        v(tag, msg + "\n" + Log.getStackTraceString(new Throwable()));
    }

}
