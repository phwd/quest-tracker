package libraries.debug.log;

import android.annotation.SuppressLint;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"StringFormatUse", "BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e"})
public class BLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static FbLog fbLog;
    private static volatile int logLevel = 5;
    private static final List<BLogLevelCallback> logLevelCallbacks = new ArrayList();

    public static void setLogLevel(int logLevel2) {
        logLevel = logLevel2;
        for (BLogLevelCallback cb : logLevelCallbacks) {
            cb.onLogLevelChanged(logLevel2);
        }
    }

    public static int getLogLevel() {
        return logLevel;
    }

    public static void registerOnLogLevelChanged(BLogLevelCallback callback) {
        logLevelCallbacks.add(callback);
    }

    public static void unregisterOnLogLevelChanged(BLogLevelCallback callback) {
        logLevelCallbacks.remove(callback);
    }

    public static void init(FbLog fbLog2) {
        fbLog = fbLog2;
    }

    public static boolean isLoggable(int level) {
        return level >= logLevel;
    }

    public static boolean isLoggable(String tag, int level) {
        try {
            return level >= logLevel && Log.isLoggable(tag, level);
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static boolean isLoggable(Class<?> cls, int level) {
        return level >= logLevel && isLoggable(LogPrefixer.renderClass(cls), level);
    }

    public static void v(String tag, String msg) {
        if (logLevel > 2) {
            return;
        }
        if (fbLog != null) {
            fbLog.v(tag, msg);
        } else {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Object arg1) {
        if (logLevel <= 2) {
            v(tag, String.format(msg, arg1));
        }
    }

    public static void v(String tag, String msg, Object arg1, Object arg2) {
        if (logLevel <= 2) {
            v(tag, String.format(msg, arg1, arg2));
        }
    }

    public static void v(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 2) {
            v(tag, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void v(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 2) {
            v(tag, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void v(Class<?> cls, String msg) {
        if (logLevel > 2) {
            return;
        }
        if (fbLog != null) {
            fbLog.v(LogPrefixer.renderClass(cls), msg);
        } else {
            Log.v(LogPrefixer.renderClass(cls), msg);
        }
    }

    public static void v(Class<?> cls, String msg, Object arg1) {
        if (logLevel <= 2) {
            v(cls, String.format(msg, arg1));
        }
    }

    public static void v(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (logLevel <= 2) {
            v(cls, String.format(msg, arg1, arg2));
        }
    }

    public static void v(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 2) {
            v(cls, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void v(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 2) {
            v(cls, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void v(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object... args) {
        if (logLevel <= 2) {
            v(tag, String.format(msg, arg1, arg2, arg3, arg4, arg5, args));
        }
    }

    public static void v(String tag, Throwable tr, String msg, Object... args) {
        if (logLevel <= 2) {
            v(tag, tr, String.format(msg, args));
        }
    }

    public static void v(String tag, Throwable tr, String msg) {
        if (logLevel > 2) {
            return;
        }
        if (fbLog != null) {
            fbLog.v(tag, msg, tr);
        } else {
            Log.v(tag, msg, tr);
        }
    }

    public static void v(String tag, Throwable tr, String msg, Object arg) {
        if (logLevel <= 2) {
            v(tag, tr, String.format(msg, arg));
        }
    }

    public static void v(String tag, Throwable tr, String msg, Object arg1, Object arg2) {
        if (logLevel <= 2) {
            v(tag, tr, String.format(msg, arg1, arg2));
        }
    }

    public static void v(String tag, Throwable tr, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 2) {
            v(tag, tr, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void v(String tag, Throwable tr, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 2) {
            v(tag, tr, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void v(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object... args) {
        if (logLevel <= 2) {
            v(cls, String.format(msg, arg1, arg2, arg3, arg4, arg5, args));
        }
    }

    public static void v(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (logLevel <= 2) {
            v(cls, tr, String.format(msg, args));
        }
    }

    public static void v(Class<?> cls, Throwable tr, String msg) {
        if (logLevel > 2) {
            return;
        }
        if (fbLog != null) {
            fbLog.v(LogPrefixer.renderClass(cls), msg, tr);
        } else {
            Log.v(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void v(Class<?> cls, Throwable tr, String msg, Object arg) {
        if (logLevel <= 2) {
            v(cls, tr, String.format(msg, arg));
        }
    }

    public static void v(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2) {
        if (logLevel <= 2) {
            v(cls, tr, String.format(msg, arg1, arg2));
        }
    }

    public static void v(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 2) {
            v(cls, tr, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void v(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 2) {
            v(cls, tr, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (logLevel > 2) {
            return;
        }
        if (fbLog != null) {
            fbLog.v(tag, msg, tr);
        } else {
            Log.v(tag, msg, tr);
        }
    }

    public static void v(Class<?> cls, String msg, Throwable tr) {
        if (logLevel > 2) {
            return;
        }
        if (fbLog != null) {
            fbLog.v(LogPrefixer.renderClass(cls), msg, tr);
        } else {
            Log.v(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (logLevel > 3) {
            return;
        }
        if (fbLog != null) {
            fbLog.d(tag, msg);
        } else {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Object arg1) {
        if (logLevel <= 3) {
            d(tag, String.format(msg, arg1));
        }
    }

    public static void d(String tag, String msg, Object arg1, Object arg2) {
        if (logLevel <= 3) {
            d(tag, String.format(msg, arg1, arg2));
        }
    }

    public static void d(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 3) {
            d(tag, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void d(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 3) {
            d(tag, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void d(Class<?> cls, String msg) {
        if (logLevel > 3) {
            return;
        }
        if (fbLog != null) {
            fbLog.d(LogPrefixer.renderClass(cls), msg);
        } else {
            Log.d(LogPrefixer.renderClass(cls), msg);
        }
    }

    public static void d(Class<?> cls, String msg, Object arg1) {
        if (logLevel <= 3) {
            d(cls, String.format(msg, arg1));
        }
    }

    public static void d(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (logLevel <= 3) {
            d(cls, String.format(msg, arg1, arg2));
        }
    }

    public static void d(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 3) {
            d(cls, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void d(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 3) {
            d(cls, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void d(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object... args) {
        if (logLevel <= 3) {
            d(tag, String.format(msg, arg1, arg2, arg3, arg4, arg5, args));
        }
    }

    public static void d(String tag, Throwable tr, String msg, Object... args) {
        if (logLevel <= 3) {
            d(tag, tr, String.format(msg, args));
        }
    }

    public static void d(String tag, Throwable tr, String msg) {
        if (logLevel > 3) {
            return;
        }
        if (fbLog != null) {
            fbLog.d(tag, msg, tr);
        } else {
            Log.d(tag, msg, tr);
        }
    }

    public static void d(String tag, Throwable tr, String msg, Object arg) {
        if (logLevel <= 3) {
            d(tag, tr, String.format(msg, arg));
        }
    }

    public static void d(String tag, Throwable tr, String msg, Object arg1, Object arg2) {
        if (logLevel <= 3) {
            d(tag, tr, String.format(msg, arg1, arg2));
        }
    }

    public static void d(String tag, Throwable tr, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 3) {
            d(tag, tr, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void d(String tag, Throwable tr, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 3) {
            d(tag, tr, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void d(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object... args) {
        if (logLevel <= 3) {
            d(cls, String.format(msg, arg1, arg2, arg3, arg4, arg5, args));
        }
    }

    public static void d(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (logLevel <= 3) {
            d(cls, tr, String.format(msg, args));
        }
    }

    public static void d(Class<?> cls, Throwable tr, String msg) {
        if (logLevel > 3) {
            return;
        }
        if (fbLog != null) {
            fbLog.d(LogPrefixer.renderClass(cls), msg, tr);
        } else {
            Log.d(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void d(Class<?> cls, Throwable tr, String msg, Object arg) {
        if (logLevel <= 3) {
            d(cls, tr, String.format(msg, arg));
        }
    }

    public static void d(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2) {
        if (logLevel <= 3) {
            d(cls, tr, String.format(msg, arg1, arg2));
        }
    }

    public static void d(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 3) {
            d(cls, tr, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void d(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 3) {
            d(cls, tr, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (logLevel > 3) {
            return;
        }
        if (fbLog != null) {
            fbLog.d(tag, msg, tr);
        } else {
            Log.d(tag, msg, tr);
        }
    }

    public static void d(Class<?> cls, String msg, Throwable tr) {
        if (logLevel > 3) {
            return;
        }
        if (fbLog != null) {
            fbLog.d(LogPrefixer.renderClass(cls), msg, tr);
        } else {
            Log.d(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (logLevel > 4) {
            return;
        }
        if (fbLog != null) {
            fbLog.i(tag, msg);
        } else {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Object arg1) {
        if (logLevel <= 4) {
            i(tag, String.format(msg, arg1));
        }
    }

    public static void i(String tag, String msg, Object arg1, Object arg2) {
        if (logLevel <= 4) {
            i(tag, String.format(msg, arg1, arg2));
        }
    }

    public static void i(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 4) {
            i(tag, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void i(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 4) {
            i(tag, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void i(Class<?> cls, String msg) {
        if (logLevel > 4) {
            return;
        }
        if (fbLog != null) {
            fbLog.i(LogPrefixer.renderClass(cls), msg);
        } else {
            Log.i(LogPrefixer.renderClass(cls), msg);
        }
    }

    public static void i(Class<?> cls, String msg, Object arg1) {
        if (logLevel <= 4) {
            i(cls, String.format(msg, arg1));
        }
    }

    public static void i(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (logLevel <= 4) {
            i(cls, String.format(msg, arg1, arg2));
        }
    }

    public static void i(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 4) {
            i(cls, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void i(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 4) {
            i(cls, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void i(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object... args) {
        if (logLevel <= 4) {
            i(tag, String.format(msg, arg1, arg2, arg3, arg4, arg5, args));
        }
    }

    public static void i(String tag, Throwable tr, String msg, Object... args) {
        if (logLevel <= 4) {
            i(tag, tr, String.format(msg, args));
        }
    }

    public static void i(String tag, Throwable tr, String msg) {
        if (logLevel > 4) {
            return;
        }
        if (fbLog != null) {
            fbLog.i(tag, msg, tr);
        } else {
            Log.i(tag, msg, tr);
        }
    }

    public static void i(String tag, Throwable tr, String msg, Object arg) {
        if (logLevel <= 4) {
            i(tag, tr, String.format(msg, arg));
        }
    }

    public static void i(String tag, Throwable tr, String msg, Object arg1, Object arg2) {
        if (logLevel <= 4) {
            i(tag, tr, String.format(msg, arg1, arg2));
        }
    }

    public static void i(String tag, Throwable tr, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 4) {
            i(tag, tr, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void i(String tag, Throwable tr, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 4) {
            i(tag, tr, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void i(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object... args) {
        if (logLevel <= 4) {
            i(cls, String.format(msg, arg1, arg2, arg3, arg4, arg5, args));
        }
    }

    public static void i(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (logLevel <= 4) {
            i(cls, tr, String.format(msg, args));
        }
    }

    public static void i(Class<?> cls, Throwable tr, String msg) {
        if (logLevel > 4) {
            return;
        }
        if (fbLog != null) {
            fbLog.i(LogPrefixer.renderClass(cls), msg, tr);
        } else {
            Log.i(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void i(Class<?> cls, Throwable tr, String msg, Object arg) {
        if (logLevel <= 4) {
            i(cls, tr, String.format(msg, arg));
        }
    }

    public static void i(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2) {
        if (logLevel <= 4) {
            i(cls, tr, String.format(msg, arg1, arg2));
        }
    }

    public static void i(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 4) {
            i(cls, tr, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void i(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 4) {
            i(cls, tr, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (logLevel > 4) {
            return;
        }
        if (fbLog != null) {
            fbLog.i(tag, msg, tr);
        } else {
            Log.i(tag, msg, tr);
        }
    }

    public static void i(Class<?> cls, String msg, Throwable tr) {
        if (logLevel > 4) {
            return;
        }
        if (fbLog != null) {
            fbLog.i(LogPrefixer.renderClass(cls), msg, tr);
        } else {
            Log.i(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (logLevel > 5) {
            return;
        }
        if (fbLog != null) {
            fbLog.w(tag, msg);
        } else {
            Log.w(tag, msg);
        }
    }

    public static void w(Class<?> cls, String msg) {
        if (logLevel > 5) {
            return;
        }
        if (fbLog != null) {
            fbLog.w(LogPrefixer.renderClass(cls), msg);
        } else {
            Log.w(LogPrefixer.renderClass(cls), msg);
        }
    }

    public static void w(String tag, String msg, Object... args) {
        if (logLevel <= 5) {
            String msg2 = String.format(msg, args);
            if (fbLog != null) {
                fbLog.w(tag, msg2);
            } else {
                Log.w(tag, msg2);
            }
        }
    }

    public static void w(String tag, Throwable tr, String msg, Object... args) {
        if (logLevel <= 5) {
            w(tag, tr, String.format(msg, args));
        }
    }

    public static void w(String tag, Throwable tr, String msg) {
        if (logLevel > 5) {
            return;
        }
        if (fbLog != null) {
            fbLog.w(tag, msg, tr);
        } else {
            Log.w(tag, msg, tr);
        }
    }

    public static void w(String tag, Throwable tr, String msg, Object arg) {
        if (logLevel <= 5) {
            w(tag, tr, String.format(msg, arg));
        }
    }

    public static void w(String tag, Throwable tr, String msg, Object arg1, Object arg2) {
        if (logLevel <= 5) {
            w(tag, tr, String.format(msg, arg1, arg2));
        }
    }

    public static void w(String tag, Throwable tr, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 5) {
            w(tag, tr, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void w(String tag, Throwable tr, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 5) {
            w(tag, tr, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void w(Class<?> cls, String msg, Object... args) {
        if (logLevel <= 5) {
            String msg2 = String.format(msg, args);
            if (fbLog != null) {
                fbLog.w(LogPrefixer.renderClass(cls), msg2);
            } else {
                Log.w(LogPrefixer.renderClass(cls), msg2);
            }
        }
    }

    public static void w(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (logLevel <= 5) {
            w(cls, tr, String.format(msg, args));
        }
    }

    public static void w(Class<?> cls, Throwable tr, String msg) {
        if (logLevel > 5) {
            return;
        }
        if (fbLog != null) {
            fbLog.w(LogPrefixer.renderClass(cls), msg, tr);
        } else {
            Log.w(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void w(Class<?> cls, Throwable tr, String msg, Object arg) {
        if (logLevel <= 5) {
            w(cls, tr, String.format(msg, arg));
        }
    }

    public static void w(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2) {
        if (logLevel <= 5) {
            w(cls, tr, String.format(msg, arg1, arg2));
        }
    }

    public static void w(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 5) {
            w(cls, tr, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void w(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 5) {
            w(cls, tr, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (logLevel > 5) {
            return;
        }
        if (fbLog != null) {
            fbLog.w(tag, msg, tr);
        } else {
            Log.w(tag, msg, tr);
        }
    }

    public static void w(Class<?> cls, String msg, Throwable tr) {
        if (logLevel > 5) {
            return;
        }
        if (fbLog != null) {
            fbLog.w(LogPrefixer.renderClass(cls), msg, tr);
        } else {
            Log.w(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (logLevel > 5) {
            return;
        }
        if (fbLog != null) {
            fbLog.w(tag, tr);
        } else {
            Log.w(tag, tr);
        }
    }

    public static void w(Class<?> cls, Throwable tr) {
        if (logLevel > 5) {
            return;
        }
        if (fbLog != null) {
            fbLog.w(LogPrefixer.renderClass(cls), tr);
        } else {
            Log.w(LogPrefixer.renderClass(cls), tr);
        }
    }

    public static void e(String tag, String msg) {
        if (logLevel > 6) {
            return;
        }
        if (fbLog != null) {
            fbLog.e(tag, msg);
        } else {
            Log.e(tag, msg);
        }
    }

    public static void e(Class<?> cls, String msg) {
        if (logLevel > 6) {
            return;
        }
        if (fbLog != null) {
            fbLog.e(LogPrefixer.renderClass(cls), msg);
        } else {
            Log.e(LogPrefixer.renderClass(cls), msg);
        }
    }

    public static void e(String tag, String msg, Object... args) {
        if (logLevel <= 6) {
            String msg2 = String.format(msg, args);
            if (fbLog != null) {
                fbLog.e(tag, msg2);
            } else {
                Log.e(tag, msg2);
            }
        }
    }

    public static void e(String tag, Throwable tr, String msg, Object... args) {
        if (logLevel <= 6) {
            e(tag, tr, String.format(msg, args));
        }
    }

    public static void e(String tag, Throwable tr, String msg) {
        if (logLevel > 6) {
            return;
        }
        if (fbLog != null) {
            fbLog.e(tag, msg, tr);
        } else {
            Log.e(tag, msg, tr);
        }
    }

    public static void e(String tag, Throwable tr, String msg, Object arg) {
        if (logLevel <= 6) {
            e(tag, tr, String.format(msg, arg));
        }
    }

    public static void e(String tag, Throwable tr, String msg, Object arg1, Object arg2) {
        if (logLevel <= 6) {
            e(tag, tr, String.format(msg, arg1, arg2));
        }
    }

    public static void e(String tag, Throwable tr, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 6) {
            e(tag, tr, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void e(String tag, Throwable tr, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 6) {
            e(tag, tr, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void e(Class<?> cls, String msg, Object... args) {
        if (logLevel <= 6) {
            String msg2 = String.format(msg, args);
            if (fbLog != null) {
                fbLog.e(LogPrefixer.renderClass(cls), msg2);
            } else {
                Log.e(LogPrefixer.renderClass(cls), msg2);
            }
        }
    }

    public static void e(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (logLevel <= 6) {
            e(cls, tr, String.format(msg, args));
        }
    }

    public static void e(Class<?> cls, Throwable tr, String msg) {
        if (logLevel > 6) {
            return;
        }
        if (fbLog != null) {
            fbLog.e(LogPrefixer.renderClass(cls), msg, tr);
        } else {
            Log.e(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void e(Class<?> cls, Throwable tr, String msg, Object arg) {
        if (logLevel <= 6) {
            e(cls, tr, String.format(msg, arg));
        }
    }

    public static void e(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2) {
        if (logLevel <= 6) {
            e(cls, tr, String.format(msg, arg1, arg2));
        }
    }

    public static void e(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2, Object arg3) {
        if (logLevel <= 6) {
            e(cls, tr, String.format(msg, arg1, arg2, arg3));
        }
    }

    public static void e(Class<?> cls, Throwable tr, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (logLevel <= 6) {
            e(cls, tr, String.format(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (logLevel > 6) {
            return;
        }
        if (fbLog != null) {
            fbLog.e(tag, msg, tr);
        } else {
            Log.e(tag, msg, tr);
        }
    }

    public static void e(Class<?> cls, String msg, Throwable tr) {
        if (logLevel > 6) {
            return;
        }
        if (fbLog != null) {
            fbLog.e(LogPrefixer.renderClass(cls), msg, tr);
        } else {
            Log.e(LogPrefixer.renderClass(cls), msg, tr);
        }
    }

    public static void log(int priority, String tag, String msg) {
        if (logLevel > priority) {
            return;
        }
        if (fbLog != null) {
            fbLog.log(priority, tag, msg);
        } else {
            Log.println(priority, tag, msg);
        }
    }

    public static void println(int priority, String tag, String msg) {
        if (logLevel > priority) {
            return;
        }
        if (fbLog != null) {
            fbLog.log(priority, tag, msg);
        } else {
            Log.println(priority, tag, msg);
        }
    }
}
