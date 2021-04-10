package com.facebook.debug.log;

import android.annotation.SuppressLint;
import com.facebook.common.logging.FLog;
import com.facebook.common.logging.LoggingDelegate;
import com.facebook.common.stringformat.StringFormatUtil;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"StringFormatUse", "BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e"})
public class BLog {
    private static final List<BLogLevelCallback> logLevelCallbacks = new ArrayList();
    private static volatile LoggingDelegate sLoggingDelegate = DefaultLoggingDelegate.getInstance();
    private static volatile boolean sPreprocessing = false;

    static {
        sLoggingDelegate.setMinimumLoggingLevel(5);
        FLog.setLoggingDelegate(sLoggingDelegate);
    }

    public static synchronized void setLogLevel(int logLevel) {
        synchronized (BLog.class) {
            sLoggingDelegate.setMinimumLoggingLevel(logLevel);
            for (BLogLevelCallback cb : logLevelCallbacks) {
                cb.onLogLevelChanged(logLevel);
            }
        }
    }

    public static void v(String tag, String msg) {
        if (sLoggingDelegate.isLoggable(2)) {
            sLoggingDelegate.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Object arg1) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1));
        }
    }

    public static void d(String tag, String msg) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Object arg1) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1));
        }
    }

    public static void d(String tag, String msg, Object arg1, Object arg2) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2));
        }
    }

    public static void d(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3));
        }
    }

    public static void d(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void i(String tag, String msg) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Object arg1) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1));
        }
    }

    public static void i(String tag, String msg, Object arg1, Object arg2) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2));
        }
    }

    public static void i(String tag, Throwable tr, String msg) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (sLoggingDelegate.isLoggable(5)) {
            sLoggingDelegate.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(5)) {
            w(tag, StringFormatUtil.formatStrLocaleSafe(msg, args));
        }
    }

    public static void w(String tag, Throwable tr, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(5)) {
            w(tag, StringFormatUtil.formatStrLocaleSafe(msg, args), tr);
        }
    }

    public static void w(String tag, Throwable tr, String msg) {
        if (sLoggingDelegate.isLoggable(5)) {
            sLoggingDelegate.w(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (sLoggingDelegate.isLoggable(5)) {
            sLoggingDelegate.w(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(6)) {
            e(tag, StringFormatUtil.formatStrLocaleSafe(msg, args));
        }
    }

    public static void e(String tag, Throwable tr, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(6)) {
            e(tag, StringFormatUtil.formatStrLocaleSafe(msg, args), tr);
        }
    }

    public static void e(String tag, Throwable tr, String msg) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.e(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.e(tag, msg, tr);
        }
    }
}
