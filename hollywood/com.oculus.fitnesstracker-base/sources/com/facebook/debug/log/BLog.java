package com.facebook.debug.log;

import android.annotation.SuppressLint;
import com.facebook.common.logging.FLog;
import com.facebook.common.logging.LoggingDelegate;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.NullsafeStrict;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NullsafeStrict
@SuppressLint({"StringFormatUse", "BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e"})
public final class BLog {
    private static final List<Object> logLevelCallbacks = new ArrayList();
    private static volatile LoggingDelegate sLoggingDelegate = DefaultLoggingDelegate.getInstance();
    private static volatile boolean sPreprocessing = false;

    static {
        sLoggingDelegate.setMinimumLoggingLevel(5);
        LoggingDelegate loggingDelegate = sLoggingDelegate;
        if (loggingDelegate != null) {
            FLog.sHandler = loggingDelegate;
            return;
        }
        throw new IllegalArgumentException();
    }

    public static synchronized void setLogLevel(int i) {
        synchronized (BLog.class) {
            sLoggingDelegate.setMinimumLoggingLevel(i);
            Iterator<Object> it = logLevelCallbacks.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    public static void v(String str, String str2, Object obj) {
        if (sLoggingDelegate.isLoggable(2)) {
            String formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe(str2, obj);
            if (sLoggingDelegate.isLoggable(2)) {
                sLoggingDelegate.v(str, formatStrLocaleSafe);
            }
        }
    }

    public static void d(String str, String str2) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(str, str2);
        }
    }

    public static void d(String str, String str2, Object obj) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(str, StringFormatUtil.formatStrLocaleSafe(str2, obj));
        }
    }

    public static void d(String str, String str2, Object obj, Object obj2) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2));
        }
    }

    public static void d(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2, obj3));
        }
    }

    public static void d(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void d(String str, Throwable th, String str2) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(str, str2, th);
        }
    }

    public static void i(String str, String str2) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(str, str2);
        }
    }

    public static void i(String str, String str2, Object obj) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(str, StringFormatUtil.formatStrLocaleSafe(str2, obj));
        }
    }

    public static void i(String str, String str2, Object obj, Object obj2) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2));
        }
    }

    public static void i(String str, Throwable th, String str2) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(str, str2, th);
        }
    }

    public static void w(String str, String str2) {
        if (sLoggingDelegate.isLoggable(5)) {
            sLoggingDelegate.w(str, str2);
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        if (sLoggingDelegate.isLoggable(5)) {
            w(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void w(String str, Throwable th, String str2, Object... objArr) {
        if (sLoggingDelegate.isLoggable(5)) {
            w(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }

    public static void w(String str, Throwable th, String str2) {
        if (sLoggingDelegate.isLoggable(5)) {
            sLoggingDelegate.w(str, str2, th);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (sLoggingDelegate.isLoggable(5)) {
            sLoggingDelegate.w(str, str2, th);
        }
    }

    public static void e(String str, String str2) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.e(str, str2);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (sLoggingDelegate.isLoggable(6)) {
            e(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void e(String str, Throwable th, String str2, Object... objArr) {
        if (sLoggingDelegate.isLoggable(6)) {
            e(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }

    public static void e(String str, Throwable th, String str2) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.e(str, str2, th);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.e(str, str2, th);
        }
    }
}
