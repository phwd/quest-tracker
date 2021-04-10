package com.facebook.debug.log;

import android.annotation.SuppressLint;
import com.facebook.common.logging.FLog;
import com.facebook.common.logging.LoggingDelegate;
import com.facebook.common.stringformat.StringFormatUtil;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"StringFormatUse", "BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e"})
public class BLog {
    private static final List<Object> logLevelCallbacks = new ArrayList();
    private static volatile LoggingDelegate sLoggingDelegate = DefaultLoggingDelegate.getInstance();

    static {
        sLoggingDelegate.setMinimumLoggingLevel(5);
        FLog.setLoggingDelegate(sLoggingDelegate);
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
}
