package com.facebook.debug.log;

import android.annotation.SuppressLint;
import com.facebook.common.logging.FLog;
import com.facebook.common.logging.LoggingDelegate;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.NullsafeStrict;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@NullsafeStrict
@SuppressLint({"StringFormatUse", "BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e"})
public class BLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
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

    public static int getLogLevel() {
        return sLoggingDelegate.getMinimumLoggingLevel();
    }

    public static synchronized void registerOnLogLevelChanged(BLogLevelCallback callback) {
        synchronized (BLog.class) {
            logLevelCallbacks.add(callback);
        }
    }

    public static synchronized void unregisterOnLogLevelChanged(BLogLevelCallback callback) {
        synchronized (BLog.class) {
            logLevelCallbacks.remove(callback);
        }
    }

    public static synchronized void init(LoggingDelegate loggingDelegate) {
        synchronized (BLog.class) {
            if (loggingDelegate == null) {
                loggingDelegate = DefaultLoggingDelegate.getInstance();
            }
            loggingDelegate.setMinimumLoggingLevel(sLoggingDelegate.getMinimumLoggingLevel());
            if (sPreprocessing) {
                ((LoggingPreprocessor) sLoggingDelegate).setDownstreamLogger(loggingDelegate);
            } else {
                sLoggingDelegate = loggingDelegate;
                FLog.setLoggingDelegate(sLoggingDelegate);
            }
        }
    }

    public static synchronized void attachPreprocessor(LoggingPreprocessor preprocessingDelegate) {
        synchronized (BLog.class) {
            sPreprocessing = true;
            preprocessingDelegate.setDownstreamLogger(sLoggingDelegate);
            sLoggingDelegate = preprocessingDelegate;
            FLog.setLoggingDelegate(sLoggingDelegate);
        }
    }

    public static boolean isLoggable(int level) {
        return sLoggingDelegate.isLoggable(level);
    }

    public static Class getLoggingDelegateClass() {
        return sLoggingDelegate.getClass();
    }

    public static void v(String tag, String msg) {
        if (sLoggingDelegate.isLoggable(2)) {
            sLoggingDelegate.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, @Nullable Object arg1) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1));
        }
    }

    public static void v(String tag, String msg, @Nullable Object arg1, @Nullable Object arg2) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2));
        }
    }

    public static void v(String tag, String msg, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3));
        }
    }

    public static void v(String tag, String msg, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object arg4) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void v(Class<?> cls, String msg) {
        if (sLoggingDelegate.isLoggable(2)) {
            sLoggingDelegate.v(LogPrefixer.renderClass(cls), msg);
        }
    }

    public static void v(Class<?> cls, String msg, @Nullable Object arg1) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(cls, StringFormatUtil.formatStrLocaleSafe(msg, arg1));
        }
    }

    public static void v(Class<?> cls, String msg, @Nullable Object arg1, @Nullable Object arg2) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(cls, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2));
        }
    }

    public static void v(Class<?> cls, String msg, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(cls, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3));
        }
    }

    public static void v(Class<?> cls, String msg, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object arg4) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(cls, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void v(String tag, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(tag, StringFormatUtil.formatStrLocaleSafe(msg, args));
        }
    }

    public static void v(String tag, Throwable tr, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(tag, StringFormatUtil.formatStrLocaleSafe(msg, args), tr);
        }
    }

    public static void v(Class<?> cls, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(cls, StringFormatUtil.formatStrLocaleSafe(msg, args));
        }
    }

    public static void v(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(cls, StringFormatUtil.formatStrLocaleSafe(msg, args), tr);
        }
    }

    public static void v(String tag, Throwable tr, String msg) {
        if (sLoggingDelegate.isLoggable(2)) {
            sLoggingDelegate.v(tag, msg, tr);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (sLoggingDelegate.isLoggable(2)) {
            sLoggingDelegate.v(tag, msg, tr);
        }
    }

    public static void v(Class<?> cls, String msg, Throwable tr) {
        if (sLoggingDelegate.isLoggable(2)) {
            sLoggingDelegate.v(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, @Nullable Object arg1) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1));
        }
    }

    public static void d(String tag, String msg, @Nullable Object arg1, @Nullable Object arg2) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2));
        }
    }

    public static void d(String tag, String msg, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3));
        }
    }

    public static void d(String tag, String msg, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object arg4) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void d(Class<?> cls, String msg) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(LogPrefixer.renderClass(cls), msg);
        }
    }

    public static void d(Class<?> cls, String msg, @Nullable Object arg1) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(cls, StringFormatUtil.formatStrLocaleSafe(msg, arg1));
        }
    }

    public static void d(Class<?> cls, String msg, @Nullable Object arg1, @Nullable Object arg2) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(cls, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2));
        }
    }

    public static void d(Class<?> cls, String msg, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(cls, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3));
        }
    }

    public static void d(Class<?> cls, String msg, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object arg4) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(cls, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void d(String tag, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(tag, StringFormatUtil.formatStrLocaleSafe(msg, args));
        }
    }

    public static void d(String tag, Throwable tr, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(tag, StringFormatUtil.formatStrLocaleSafe(msg, args), tr);
        }
    }

    public static void d(Class<?> cls, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(cls, StringFormatUtil.formatStrLocaleSafe(msg, args));
        }
    }

    public static void d(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(msg, args), tr);
        }
    }

    public static void d(String tag, Throwable tr, String msg) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(tag, msg, tr);
        }
    }

    public static void d(Class<?> cls, String msg, Throwable tr) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, @Nullable Object arg1) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1));
        }
    }

    public static void i(String tag, String msg, @Nullable Object arg1, @Nullable Object arg2) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2));
        }
    }

    public static void i(String tag, String msg, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3));
        }
    }

    public static void i(String tag, String msg, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object arg4) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(tag, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void i(Class<?> cls, String msg) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(LogPrefixer.renderClass(cls), msg);
        }
    }

    public static void i(Class<?> cls, String msg, @Nullable Object arg1) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(cls, StringFormatUtil.formatStrLocaleSafe(msg, arg1));
        }
    }

    public static void i(Class<?> cls, String msg, @Nullable Object arg1, @Nullable Object arg2) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(cls, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2));
        }
    }

    public static void i(Class<?> cls, String msg, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(cls, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3));
        }
    }

    public static void i(Class<?> cls, String msg, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object arg4) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(cls, StringFormatUtil.formatStrLocaleSafe(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void i(String tag, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(tag, StringFormatUtil.formatStrLocaleSafe(msg, args));
        }
    }

    public static void i(String tag, Throwable tr, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(tag, StringFormatUtil.formatStrLocaleSafe(msg, args), tr);
        }
    }

    public static void i(Class<?> cls, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(cls, StringFormatUtil.formatStrLocaleSafe(msg, args));
        }
    }

    public static void i(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(msg, args), tr);
        }
    }

    public static void i(String tag, Throwable tr, String msg) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(tag, msg, tr);
        }
    }

    public static void i(Class<?> cls, String msg, Throwable tr) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (sLoggingDelegate.isLoggable(5)) {
            sLoggingDelegate.w(tag, msg);
        }
    }

    public static void w(Class<?> cls, String msg) {
        if (sLoggingDelegate.isLoggable(5)) {
            sLoggingDelegate.w(LogPrefixer.renderClass(cls), msg);
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

    public static void w(Class<?> cls, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(5)) {
            w(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(msg, args));
        }
    }

    public static void w(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(5)) {
            w(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(msg, args), tr);
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

    public static void w(Class<?> cls, String msg, Throwable tr) {
        if (sLoggingDelegate.isLoggable(5)) {
            sLoggingDelegate.w(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.e(tag, msg);
        }
    }

    public static void e(Class<?> cls, String msg) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.e(LogPrefixer.renderClass(cls), msg);
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

    public static void e(Class<?> cls, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(6)) {
            e(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(msg, args));
        }
    }

    public static void e(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(6)) {
            e(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(msg, args), tr);
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

    public static void e(Class<?> cls, String msg, Throwable tr) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.e(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void wtf(String tag, String msg) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.wtf(tag, msg);
        }
    }

    public static void wtf(Class<?> cls, String msg) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.wtf(LogPrefixer.renderClass(cls), msg);
        }
    }

    public static void wtf(String tag, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(6)) {
            wtf(tag, StringFormatUtil.formatStrLocaleSafe(msg, args));
        }
    }

    public static void wtf(String tag, Throwable tr, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(6)) {
            wtf(tag, StringFormatUtil.formatStrLocaleSafe(msg, args), tr);
        }
    }

    public static void wtf(Class<?> cls, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(6)) {
            wtf(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(msg, args));
        }
    }

    public static void wtf(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sLoggingDelegate.isLoggable(6)) {
            wtf(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(msg, args), tr);
        }
    }

    public static void wtf(String tag, Throwable tr, String msg) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.wtf(tag, msg, tr);
        }
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.wtf(tag, msg, tr);
        }
    }

    public static void wtf(Class<?> cls, String msg, Throwable tr) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.wtf(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void log(int priority, String tag, String msg) {
        if (sLoggingDelegate.isLoggable(priority)) {
            sLoggingDelegate.log(priority, tag, msg);
        }
    }

    public static void println(int priority, String tag, String msg) {
        if (sLoggingDelegate.isLoggable(priority)) {
            sLoggingDelegate.log(priority, tag, msg);
        }
    }
}
