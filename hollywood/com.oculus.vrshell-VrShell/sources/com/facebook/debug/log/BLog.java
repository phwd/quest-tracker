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

    public static synchronized void setLogLevel(int i) {
        synchronized (BLog.class) {
            sLoggingDelegate.setMinimumLoggingLevel(i);
            for (BLogLevelCallback bLogLevelCallback : logLevelCallbacks) {
                bLogLevelCallback.onLogLevelChanged(i);
            }
        }
    }

    public static int getLogLevel() {
        return sLoggingDelegate.getMinimumLoggingLevel();
    }

    public static synchronized void registerOnLogLevelChanged(BLogLevelCallback bLogLevelCallback) {
        synchronized (BLog.class) {
            logLevelCallbacks.add(bLogLevelCallback);
        }
    }

    public static synchronized void unregisterOnLogLevelChanged(BLogLevelCallback bLogLevelCallback) {
        synchronized (BLog.class) {
            logLevelCallbacks.remove(bLogLevelCallback);
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

    public static synchronized void attachPreprocessor(LoggingPreprocessor loggingPreprocessor) {
        synchronized (BLog.class) {
            sPreprocessing = true;
            loggingPreprocessor.setDownstreamLogger(sLoggingDelegate);
            sLoggingDelegate = loggingPreprocessor;
            FLog.setLoggingDelegate(sLoggingDelegate);
        }
    }

    public static boolean isLoggable(int i) {
        return sLoggingDelegate.isLoggable(i);
    }

    public static Class getLoggingDelegateClass() {
        return sLoggingDelegate.getClass();
    }

    public static void v(String str, String str2) {
        if (sLoggingDelegate.isLoggable(2)) {
            sLoggingDelegate.v(str, str2);
        }
    }

    public static void v(String str, String str2, @Nullable Object obj) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(str, StringFormatUtil.formatStrLocaleSafe(str2, obj));
        }
    }

    public static void v(String str, String str2, @Nullable Object obj, @Nullable Object obj2) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2));
        }
    }

    public static void v(String str, String str2, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2, obj3));
        }
    }

    public static void v(String str, String str2, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void v(Class<?> cls, String str) {
        if (sLoggingDelegate.isLoggable(2)) {
            sLoggingDelegate.v(LogPrefixer.renderClass(cls), str);
        }
    }

    public static void v(Class<?> cls, String str, @Nullable Object obj) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(cls, StringFormatUtil.formatStrLocaleSafe(str, obj));
        }
    }

    public static void v(Class<?> cls, String str, @Nullable Object obj, @Nullable Object obj2) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(cls, StringFormatUtil.formatStrLocaleSafe(str, obj, obj2));
        }
    }

    public static void v(Class<?> cls, String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(cls, StringFormatUtil.formatStrLocaleSafe(str, obj, obj2, obj3));
        }
    }

    public static void v(Class<?> cls, String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(cls, StringFormatUtil.formatStrLocaleSafe(str, obj, obj2, obj3, obj4));
        }
    }

    public static void v(String str, String str2, Object... objArr) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void v(String str, Throwable th, String str2, Object... objArr) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }

    public static void v(Class<?> cls, String str, Object... objArr) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(cls, StringFormatUtil.formatStrLocaleSafe(str, objArr));
        }
    }

    public static void v(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sLoggingDelegate.isLoggable(2)) {
            v(cls, StringFormatUtil.formatStrLocaleSafe(str, objArr), th);
        }
    }

    public static void v(String str, Throwable th, String str2) {
        if (sLoggingDelegate.isLoggable(2)) {
            sLoggingDelegate.v(str, str2, th);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (sLoggingDelegate.isLoggable(2)) {
            sLoggingDelegate.v(str, str2, th);
        }
    }

    public static void v(Class<?> cls, String str, Throwable th) {
        if (sLoggingDelegate.isLoggable(2)) {
            sLoggingDelegate.v(LogPrefixer.renderClass(cls), str, th);
        }
    }

    public static void d(String str, String str2) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(str, str2);
        }
    }

    public static void d(String str, String str2, @Nullable Object obj) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(str, StringFormatUtil.formatStrLocaleSafe(str2, obj));
        }
    }

    public static void d(String str, String str2, @Nullable Object obj, @Nullable Object obj2) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2));
        }
    }

    public static void d(String str, String str2, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2, obj3));
        }
    }

    public static void d(String str, String str2, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void d(Class<?> cls, String str) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(LogPrefixer.renderClass(cls), str);
        }
    }

    public static void d(Class<?> cls, String str, @Nullable Object obj) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(cls, StringFormatUtil.formatStrLocaleSafe(str, obj));
        }
    }

    public static void d(Class<?> cls, String str, @Nullable Object obj, @Nullable Object obj2) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(cls, StringFormatUtil.formatStrLocaleSafe(str, obj, obj2));
        }
    }

    public static void d(Class<?> cls, String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(cls, StringFormatUtil.formatStrLocaleSafe(str, obj, obj2, obj3));
        }
    }

    public static void d(Class<?> cls, String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(cls, StringFormatUtil.formatStrLocaleSafe(str, obj, obj2, obj3, obj4));
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void d(String str, Throwable th, String str2, Object... objArr) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }

    public static void d(Class<?> cls, String str, Object... objArr) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(cls, StringFormatUtil.formatStrLocaleSafe(str, objArr));
        }
    }

    public static void d(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sLoggingDelegate.isLoggable(3)) {
            d(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(str, objArr), th);
        }
    }

    public static void d(String str, Throwable th, String str2) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(str, str2, th);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(str, str2, th);
        }
    }

    public static void d(Class<?> cls, String str, Throwable th) {
        if (sLoggingDelegate.isLoggable(3)) {
            sLoggingDelegate.d(LogPrefixer.renderClass(cls), str, th);
        }
    }

    public static void i(String str, String str2) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(str, str2);
        }
    }

    public static void i(String str, String str2, @Nullable Object obj) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(str, StringFormatUtil.formatStrLocaleSafe(str2, obj));
        }
    }

    public static void i(String str, String str2, @Nullable Object obj, @Nullable Object obj2) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2));
        }
    }

    public static void i(String str, String str2, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2, obj3));
        }
    }

    public static void i(String str, String str2, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void i(Class<?> cls, String str) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(LogPrefixer.renderClass(cls), str);
        }
    }

    public static void i(Class<?> cls, String str, @Nullable Object obj) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(cls, StringFormatUtil.formatStrLocaleSafe(str, obj));
        }
    }

    public static void i(Class<?> cls, String str, @Nullable Object obj, @Nullable Object obj2) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(cls, StringFormatUtil.formatStrLocaleSafe(str, obj, obj2));
        }
    }

    public static void i(Class<?> cls, String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(cls, StringFormatUtil.formatStrLocaleSafe(str, obj, obj2, obj3));
        }
    }

    public static void i(Class<?> cls, String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(cls, StringFormatUtil.formatStrLocaleSafe(str, obj, obj2, obj3, obj4));
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void i(String str, Throwable th, String str2, Object... objArr) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }

    public static void i(Class<?> cls, String str, Object... objArr) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(cls, StringFormatUtil.formatStrLocaleSafe(str, objArr));
        }
    }

    public static void i(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sLoggingDelegate.isLoggable(4)) {
            i(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(str, objArr), th);
        }
    }

    public static void i(String str, Throwable th, String str2) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(str, str2, th);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(str, str2, th);
        }
    }

    public static void i(Class<?> cls, String str, Throwable th) {
        if (sLoggingDelegate.isLoggable(4)) {
            sLoggingDelegate.i(LogPrefixer.renderClass(cls), str, th);
        }
    }

    public static void w(String str, String str2) {
        if (sLoggingDelegate.isLoggable(5)) {
            sLoggingDelegate.w(str, str2);
        }
    }

    public static void w(Class<?> cls, String str) {
        if (sLoggingDelegate.isLoggable(5)) {
            sLoggingDelegate.w(LogPrefixer.renderClass(cls), str);
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

    public static void w(Class<?> cls, String str, Object... objArr) {
        if (sLoggingDelegate.isLoggable(5)) {
            w(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(str, objArr));
        }
    }

    public static void w(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sLoggingDelegate.isLoggable(5)) {
            w(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(str, objArr), th);
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

    public static void w(Class<?> cls, String str, Throwable th) {
        if (sLoggingDelegate.isLoggable(5)) {
            sLoggingDelegate.w(LogPrefixer.renderClass(cls), str, th);
        }
    }

    public static void e(String str, String str2) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.e(str, str2);
        }
    }

    public static void e(Class<?> cls, String str) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.e(LogPrefixer.renderClass(cls), str);
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

    public static void e(Class<?> cls, String str, Object... objArr) {
        if (sLoggingDelegate.isLoggable(6)) {
            e(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(str, objArr));
        }
    }

    public static void e(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sLoggingDelegate.isLoggable(6)) {
            e(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(str, objArr), th);
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

    public static void e(Class<?> cls, String str, Throwable th) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.e(LogPrefixer.renderClass(cls), str, th);
        }
    }

    public static void wtf(String str, String str2) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.wtf(str, str2);
        }
    }

    public static void wtf(Class<?> cls, String str) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.wtf(LogPrefixer.renderClass(cls), str);
        }
    }

    public static void wtf(String str, String str2, Object... objArr) {
        if (sLoggingDelegate.isLoggable(6)) {
            wtf(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void wtf(String str, Throwable th, String str2, Object... objArr) {
        if (sLoggingDelegate.isLoggable(6)) {
            wtf(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }

    public static void wtf(Class<?> cls, String str, Object... objArr) {
        if (sLoggingDelegate.isLoggable(6)) {
            wtf(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(str, objArr));
        }
    }

    public static void wtf(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sLoggingDelegate.isLoggable(6)) {
            wtf(LogPrefixer.renderClass(cls), StringFormatUtil.formatStrLocaleSafe(str, objArr), th);
        }
    }

    public static void wtf(String str, Throwable th, String str2) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.wtf(str, str2, th);
        }
    }

    public static void wtf(String str, String str2, Throwable th) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.wtf(str, str2, th);
        }
    }

    public static void wtf(Class<?> cls, String str, Throwable th) {
        if (sLoggingDelegate.isLoggable(6)) {
            sLoggingDelegate.wtf(LogPrefixer.renderClass(cls), str, th);
        }
    }

    public static void log(int i, String str, String str2) {
        if (sLoggingDelegate.isLoggable(i)) {
            sLoggingDelegate.log(i, str, str2);
        }
    }

    public static void println(int i, String str, String str2) {
        if (sLoggingDelegate.isLoggable(i)) {
            sLoggingDelegate.log(i, str, str2);
        }
    }
}
