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
    public static FbLog fbLog = null;
    public static volatile int logLevel = 5;
    public static final List<BLogLevelCallback> logLevelCallbacks = new ArrayList();

    public static int getLogLevel() {
        return logLevel;
    }

    public static void log(int i, String str, String str2) {
        if (logLevel <= i) {
            FbLog fbLog2 = fbLog;
            if (fbLog2 != null) {
                fbLog2.log(i, str, str2);
            } else {
                Log.println(i, str, str2);
            }
        }
    }

    public static void println(int i, String str, String str2) {
        if (logLevel <= i) {
            FbLog fbLog2 = fbLog;
            if (fbLog2 != null) {
                fbLog2.log(i, str, str2);
            } else {
                Log.println(i, str, str2);
            }
        }
    }

    public static void registerOnLogLevelChanged(BLogLevelCallback bLogLevelCallback) {
        logLevelCallbacks.add(bLogLevelCallback);
    }

    public static void setLogLevel(int i) {
        logLevel = i;
        for (BLogLevelCallback bLogLevelCallback : logLevelCallbacks) {
            bLogLevelCallback.onLogLevelChanged(i);
        }
    }

    public static void unregisterOnLogLevelChanged(BLogLevelCallback bLogLevelCallback) {
        logLevelCallbacks.remove(bLogLevelCallback);
    }

    public static void init(FbLog fbLog2) {
        fbLog = fbLog2;
    }

    public static void d(Class<?> cls, String str) {
        FbLog fbLog2;
        if (logLevel <= 3 && (fbLog2 = fbLog) != null) {
            fbLog2.d(cls.getSimpleName(), str);
        }
    }

    public static void d(Class<?> cls, String str, Object obj) {
        if (logLevel <= 3) {
            d(cls, String.format(str, obj));
        }
    }

    public static void d(Class<?> cls, String str, Object obj, Object obj2) {
        if (logLevel <= 3) {
            d(cls, String.format(str, obj, obj2));
        }
    }

    public static void d(Class<?> cls, String str, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 3) {
            d(cls, String.format(str, obj, obj2, obj3));
        }
    }

    public static void d(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 3) {
            d(cls, String.format(str, obj, obj2, obj3, obj4));
        }
    }

    public static void d(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object... objArr) {
        if (logLevel <= 3) {
            d(cls, String.format(str, obj, obj2, obj3, obj4, obj5, objArr));
        }
    }

    public static void d(Class<?> cls, String str, Throwable th) {
        FbLog fbLog2;
        if (logLevel <= 3 && (fbLog2 = fbLog) != null) {
            fbLog2.d(cls.getSimpleName(), str, th);
        }
    }

    public static void d(Class<?> cls, Throwable th, String str) {
        FbLog fbLog2;
        if (logLevel <= 3 && (fbLog2 = fbLog) != null) {
            fbLog2.d(cls.getSimpleName(), str, th);
        }
    }

    public static void d(Class<?> cls, Throwable th, String str, Object obj) {
        if (logLevel <= 3) {
            d(cls, th, String.format(str, obj));
        }
    }

    public static void d(Class<?> cls, Throwable th, String str, Object obj, Object obj2) {
        if (logLevel <= 3) {
            d(cls, th, String.format(str, obj, obj2));
        }
    }

    public static void d(Class<?> cls, Throwable th, String str, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 3) {
            d(cls, th, String.format(str, obj, obj2, obj3));
        }
    }

    public static void d(Class<?> cls, Throwable th, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 3) {
            d(cls, th, String.format(str, obj, obj2, obj3, obj4));
        }
    }

    public static void d(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (logLevel <= 3) {
            d(cls, th, String.format(str, objArr));
        }
    }

    public static void d(String str, String str2) {
        FbLog fbLog2;
        if (logLevel <= 3 && (fbLog2 = fbLog) != null) {
            fbLog2.d(str, str2);
        }
    }

    public static void d(String str, String str2, Object obj) {
        if (logLevel <= 3) {
            d(str, String.format(str2, obj));
        }
    }

    public static void d(String str, String str2, Object obj, Object obj2) {
        if (logLevel <= 3) {
            d(str, String.format(str2, obj, obj2));
        }
    }

    public static void d(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 3) {
            d(str, String.format(str2, obj, obj2, obj3));
        }
    }

    public static void d(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 3) {
            d(str, String.format(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void d(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object... objArr) {
        if (logLevel <= 3) {
            d(str, String.format(str2, obj, obj2, obj3, obj4, obj5, objArr));
        }
    }

    public static void d(String str, String str2, Throwable th) {
        FbLog fbLog2;
        if (logLevel <= 3 && (fbLog2 = fbLog) != null) {
            fbLog2.d(str, str2, th);
        }
    }

    public static void d(String str, Throwable th, String str2) {
        FbLog fbLog2;
        if (logLevel <= 3 && (fbLog2 = fbLog) != null) {
            fbLog2.d(str, str2, th);
        }
    }

    public static void d(String str, Throwable th, String str2, Object obj) {
        if (logLevel <= 3) {
            d(str, th, String.format(str2, obj));
        }
    }

    public static void d(String str, Throwable th, String str2, Object obj, Object obj2) {
        if (logLevel <= 3) {
            d(str, th, String.format(str2, obj, obj2));
        }
    }

    public static void d(String str, Throwable th, String str2, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 3) {
            d(str, th, String.format(str2, obj, obj2, obj3));
        }
    }

    public static void d(String str, Throwable th, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 3) {
            d(str, th, String.format(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void d(String str, Throwable th, String str2, Object... objArr) {
        if (logLevel <= 3) {
            d(str, th, String.format(str2, objArr));
        }
    }

    public static void e(Class<?> cls, String str) {
        if (logLevel <= 6) {
            FbLog fbLog2 = fbLog;
            String simpleName = cls.getSimpleName();
            if (fbLog2 != null) {
                fbLog2.e(simpleName, str);
            } else {
                Log.e(simpleName, str);
            }
        }
    }

    public static void e(Class<?> cls, String str, Throwable th) {
        if (logLevel <= 6) {
            FbLog fbLog2 = fbLog;
            String simpleName = cls.getSimpleName();
            if (fbLog2 != null) {
                fbLog2.e(simpleName, str, th);
            } else {
                Log.e(simpleName, str, th);
            }
        }
    }

    public static void e(Class<?> cls, String str, Object... objArr) {
        if (logLevel <= 6) {
            String format = String.format(str, objArr);
            FbLog fbLog2 = fbLog;
            String simpleName = cls.getSimpleName();
            if (fbLog2 != null) {
                fbLog2.e(simpleName, format);
            } else {
                Log.e(simpleName, format);
            }
        }
    }

    public static void e(Class<?> cls, Throwable th, String str) {
        if (logLevel <= 6) {
            FbLog fbLog2 = fbLog;
            String simpleName = cls.getSimpleName();
            if (fbLog2 != null) {
                fbLog2.e(simpleName, str, th);
            } else {
                Log.e(simpleName, str, th);
            }
        }
    }

    public static void e(Class<?> cls, Throwable th, String str, Object obj) {
        if (logLevel <= 6) {
            e(cls, th, String.format(str, obj));
        }
    }

    public static void e(Class<?> cls, Throwable th, String str, Object obj, Object obj2) {
        if (logLevel <= 6) {
            e(cls, th, String.format(str, obj, obj2));
        }
    }

    public static void e(Class<?> cls, Throwable th, String str, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 6) {
            e(cls, th, String.format(str, obj, obj2, obj3));
        }
    }

    public static void e(Class<?> cls, Throwable th, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 6) {
            e(cls, th, String.format(str, obj, obj2, obj3, obj4));
        }
    }

    public static void e(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (logLevel <= 6) {
            e(cls, th, String.format(str, objArr));
        }
    }

    public static void e(String str, String str2) {
        if (logLevel <= 6) {
            FbLog fbLog2 = fbLog;
            if (fbLog2 != null) {
                fbLog2.e(str, str2);
            } else {
                Log.e(str, str2);
            }
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (logLevel <= 6) {
            FbLog fbLog2 = fbLog;
            if (fbLog2 != null) {
                fbLog2.e(str, str2, th);
            } else {
                Log.e(str, str2, th);
            }
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (logLevel <= 6) {
            String format = String.format(str2, objArr);
            FbLog fbLog2 = fbLog;
            if (fbLog2 != null) {
                fbLog2.e(str, format);
            } else {
                Log.e(str, format);
            }
        }
    }

    public static void e(String str, Throwable th, String str2) {
        if (logLevel <= 6) {
            FbLog fbLog2 = fbLog;
            if (fbLog2 != null) {
                fbLog2.e(str, str2, th);
            } else {
                Log.e(str, str2, th);
            }
        }
    }

    public static void e(String str, Throwable th, String str2, Object obj) {
        if (logLevel <= 6) {
            e(str, th, String.format(str2, obj));
        }
    }

    public static void e(String str, Throwable th, String str2, Object obj, Object obj2) {
        if (logLevel <= 6) {
            e(str, th, String.format(str2, obj, obj2));
        }
    }

    public static void e(String str, Throwable th, String str2, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 6) {
            e(str, th, String.format(str2, obj, obj2, obj3));
        }
    }

    public static void e(String str, Throwable th, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 6) {
            e(str, th, String.format(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void e(String str, Throwable th, String str2, Object... objArr) {
        if (logLevel <= 6) {
            e(str, th, String.format(str2, objArr));
        }
    }

    public static void i(Class<?> cls, String str) {
        FbLog fbLog2;
        if (logLevel <= 4 && (fbLog2 = fbLog) != null) {
            fbLog2.i(cls.getSimpleName(), str);
        }
    }

    public static void i(Class<?> cls, String str, Object obj) {
        if (logLevel <= 4) {
            i(cls, String.format(str, obj));
        }
    }

    public static void i(Class<?> cls, String str, Object obj, Object obj2) {
        if (logLevel <= 4) {
            i(cls, String.format(str, obj, obj2));
        }
    }

    public static void i(Class<?> cls, String str, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 4) {
            i(cls, String.format(str, obj, obj2, obj3));
        }
    }

    public static void i(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 4) {
            i(cls, String.format(str, obj, obj2, obj3, obj4));
        }
    }

    public static void i(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object... objArr) {
        if (logLevel <= 4) {
            i(cls, String.format(str, obj, obj2, obj3, obj4, obj5, objArr));
        }
    }

    public static void i(Class<?> cls, String str, Throwable th) {
        FbLog fbLog2;
        if (logLevel <= 4 && (fbLog2 = fbLog) != null) {
            fbLog2.i(cls.getSimpleName(), str, th);
        }
    }

    public static void i(Class<?> cls, Throwable th, String str) {
        FbLog fbLog2;
        if (logLevel <= 4 && (fbLog2 = fbLog) != null) {
            fbLog2.i(cls.getSimpleName(), str, th);
        }
    }

    public static void i(Class<?> cls, Throwable th, String str, Object obj) {
        if (logLevel <= 4) {
            i(cls, th, String.format(str, obj));
        }
    }

    public static void i(Class<?> cls, Throwable th, String str, Object obj, Object obj2) {
        if (logLevel <= 4) {
            i(cls, th, String.format(str, obj, obj2));
        }
    }

    public static void i(Class<?> cls, Throwable th, String str, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 4) {
            i(cls, th, String.format(str, obj, obj2, obj3));
        }
    }

    public static void i(Class<?> cls, Throwable th, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 4) {
            i(cls, th, String.format(str, obj, obj2, obj3, obj4));
        }
    }

    public static void i(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (logLevel <= 4) {
            i(cls, th, String.format(str, objArr));
        }
    }

    public static void i(String str, String str2) {
        FbLog fbLog2;
        if (logLevel <= 4 && (fbLog2 = fbLog) != null) {
            fbLog2.i(str, str2);
        }
    }

    public static void i(String str, String str2, Object obj) {
        if (logLevel <= 4) {
            i(str, String.format(str2, obj));
        }
    }

    public static void i(String str, String str2, Object obj, Object obj2) {
        if (logLevel <= 4) {
            i(str, String.format(str2, obj, obj2));
        }
    }

    public static void i(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 4) {
            i(str, String.format(str2, obj, obj2, obj3));
        }
    }

    public static void i(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 4) {
            i(str, String.format(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void i(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object... objArr) {
        if (logLevel <= 4) {
            i(str, String.format(str2, obj, obj2, obj3, obj4, obj5, objArr));
        }
    }

    public static void i(String str, String str2, Throwable th) {
        FbLog fbLog2;
        if (logLevel <= 4 && (fbLog2 = fbLog) != null) {
            fbLog2.i(str, str2, th);
        }
    }

    public static void i(String str, Throwable th, String str2) {
        FbLog fbLog2;
        if (logLevel <= 4 && (fbLog2 = fbLog) != null) {
            fbLog2.i(str, str2, th);
        }
    }

    public static void i(String str, Throwable th, String str2, Object obj) {
        if (logLevel <= 4) {
            i(str, th, String.format(str2, obj));
        }
    }

    public static void i(String str, Throwable th, String str2, Object obj, Object obj2) {
        if (logLevel <= 4) {
            i(str, th, String.format(str2, obj, obj2));
        }
    }

    public static void i(String str, Throwable th, String str2, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 4) {
            i(str, th, String.format(str2, obj, obj2, obj3));
        }
    }

    public static void i(String str, Throwable th, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 4) {
            i(str, th, String.format(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void i(String str, Throwable th, String str2, Object... objArr) {
        if (logLevel <= 4) {
            i(str, th, String.format(str2, objArr));
        }
    }

    public static boolean isLoggable(int i) {
        return i >= logLevel;
    }

    public static boolean isLoggable(Class<?> cls, int i) {
        return i >= logLevel && isLoggable(cls.getSimpleName(), i);
    }

    public static boolean isLoggable(String str, int i) {
        try {
            return i >= logLevel && Log.isLoggable(str, i);
        } catch (IllegalArgumentException unused) {
            return true;
        }
    }

    public static void v(Class<?> cls, String str) {
        FbLog fbLog2;
        if (logLevel <= 2 && (fbLog2 = fbLog) != null) {
            fbLog2.v(cls.getSimpleName(), str);
        }
    }

    public static void v(Class<?> cls, String str, Object obj) {
        if (logLevel <= 2) {
            v(cls, String.format(str, obj));
        }
    }

    public static void v(Class<?> cls, String str, Object obj, Object obj2) {
        if (logLevel <= 2) {
            v(cls, String.format(str, obj, obj2));
        }
    }

    public static void v(Class<?> cls, String str, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 2) {
            v(cls, String.format(str, obj, obj2, obj3));
        }
    }

    public static void v(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 2) {
            v(cls, String.format(str, obj, obj2, obj3, obj4));
        }
    }

    public static void v(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object... objArr) {
        if (logLevel <= 2) {
            v(cls, String.format(str, obj, obj2, obj3, obj4, obj5, objArr));
        }
    }

    public static void v(Class<?> cls, String str, Throwable th) {
        FbLog fbLog2;
        if (logLevel <= 2 && (fbLog2 = fbLog) != null) {
            fbLog2.v(cls.getSimpleName(), str, th);
        }
    }

    public static void v(Class<?> cls, Throwable th, String str) {
        FbLog fbLog2;
        if (logLevel <= 2 && (fbLog2 = fbLog) != null) {
            fbLog2.v(cls.getSimpleName(), str, th);
        }
    }

    public static void v(Class<?> cls, Throwable th, String str, Object obj) {
        if (logLevel <= 2) {
            v(cls, th, String.format(str, obj));
        }
    }

    public static void v(Class<?> cls, Throwable th, String str, Object obj, Object obj2) {
        if (logLevel <= 2) {
            v(cls, th, String.format(str, obj, obj2));
        }
    }

    public static void v(Class<?> cls, Throwable th, String str, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 2) {
            v(cls, th, String.format(str, obj, obj2, obj3));
        }
    }

    public static void v(Class<?> cls, Throwable th, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 2) {
            v(cls, th, String.format(str, obj, obj2, obj3, obj4));
        }
    }

    public static void v(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (logLevel <= 2) {
            v(cls, th, String.format(str, objArr));
        }
    }

    public static void v(String str, String str2) {
        FbLog fbLog2;
        if (logLevel <= 2 && (fbLog2 = fbLog) != null) {
            fbLog2.v(str, str2);
        }
    }

    public static void v(String str, String str2, Object obj) {
        if (logLevel <= 2) {
            v(str, String.format(str2, obj));
        }
    }

    public static void v(String str, String str2, Object obj, Object obj2) {
        if (logLevel <= 2) {
            v(str, String.format(str2, obj, obj2));
        }
    }

    public static void v(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 2) {
            v(str, String.format(str2, obj, obj2, obj3));
        }
    }

    public static void v(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 2) {
            v(str, String.format(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void v(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object... objArr) {
        if (logLevel <= 2) {
            v(str, String.format(str2, obj, obj2, obj3, obj4, obj5, objArr));
        }
    }

    public static void v(String str, String str2, Throwable th) {
        FbLog fbLog2;
        if (logLevel <= 2 && (fbLog2 = fbLog) != null) {
            fbLog2.v(str, str2, th);
        }
    }

    public static void v(String str, Throwable th, String str2) {
        FbLog fbLog2;
        if (logLevel <= 2 && (fbLog2 = fbLog) != null) {
            fbLog2.v(str, str2, th);
        }
    }

    public static void v(String str, Throwable th, String str2, Object obj) {
        if (logLevel <= 2) {
            v(str, th, String.format(str2, obj));
        }
    }

    public static void v(String str, Throwable th, String str2, Object obj, Object obj2) {
        if (logLevel <= 2) {
            v(str, th, String.format(str2, obj, obj2));
        }
    }

    public static void v(String str, Throwable th, String str2, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 2) {
            v(str, th, String.format(str2, obj, obj2, obj3));
        }
    }

    public static void v(String str, Throwable th, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 2) {
            v(str, th, String.format(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void v(String str, Throwable th, String str2, Object... objArr) {
        if (logLevel <= 2) {
            v(str, th, String.format(str2, objArr));
        }
    }

    public static void w(Class<?> cls, String str) {
        if (logLevel <= 5) {
            FbLog fbLog2 = fbLog;
            String simpleName = cls.getSimpleName();
            if (fbLog2 != null) {
                fbLog2.w(simpleName, str);
            } else {
                Log.w(simpleName, str);
            }
        }
    }

    public static void w(Class<?> cls, String str, Throwable th) {
        if (logLevel <= 5) {
            FbLog fbLog2 = fbLog;
            String simpleName = cls.getSimpleName();
            if (fbLog2 != null) {
                fbLog2.w(simpleName, str, th);
            } else {
                Log.w(simpleName, str, th);
            }
        }
    }

    public static void w(Class<?> cls, String str, Object... objArr) {
        if (logLevel <= 5) {
            String format = String.format(str, objArr);
            FbLog fbLog2 = fbLog;
            String simpleName = cls.getSimpleName();
            if (fbLog2 != null) {
                fbLog2.w(simpleName, format);
            } else {
                Log.w(simpleName, format);
            }
        }
    }

    public static void w(Class<?> cls, Throwable th) {
        if (logLevel <= 5) {
            FbLog fbLog2 = fbLog;
            String simpleName = cls.getSimpleName();
            if (fbLog2 != null) {
                fbLog2.w(simpleName, th);
            } else {
                Log.w(simpleName, th);
            }
        }
    }

    public static void w(Class<?> cls, Throwable th, String str) {
        if (logLevel <= 5) {
            FbLog fbLog2 = fbLog;
            String simpleName = cls.getSimpleName();
            if (fbLog2 != null) {
                fbLog2.w(simpleName, str, th);
            } else {
                Log.w(simpleName, str, th);
            }
        }
    }

    public static void w(Class<?> cls, Throwable th, String str, Object obj) {
        if (logLevel <= 5) {
            w(cls, th, String.format(str, obj));
        }
    }

    public static void w(Class<?> cls, Throwable th, String str, Object obj, Object obj2) {
        if (logLevel <= 5) {
            w(cls, th, String.format(str, obj, obj2));
        }
    }

    public static void w(Class<?> cls, Throwable th, String str, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 5) {
            w(cls, th, String.format(str, obj, obj2, obj3));
        }
    }

    public static void w(Class<?> cls, Throwable th, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 5) {
            w(cls, th, String.format(str, obj, obj2, obj3, obj4));
        }
    }

    public static void w(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (logLevel <= 5) {
            w(cls, th, String.format(str, objArr));
        }
    }

    public static void w(String str, String str2) {
        if (logLevel <= 5) {
            FbLog fbLog2 = fbLog;
            if (fbLog2 != null) {
                fbLog2.w(str, str2);
            } else {
                Log.w(str, str2);
            }
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (logLevel <= 5) {
            FbLog fbLog2 = fbLog;
            if (fbLog2 != null) {
                fbLog2.w(str, str2, th);
            } else {
                Log.w(str, str2, th);
            }
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        if (logLevel <= 5) {
            String format = String.format(str2, objArr);
            FbLog fbLog2 = fbLog;
            if (fbLog2 != null) {
                fbLog2.w(str, format);
            } else {
                Log.w(str, format);
            }
        }
    }

    public static void w(String str, Throwable th) {
        if (logLevel <= 5) {
            FbLog fbLog2 = fbLog;
            if (fbLog2 != null) {
                fbLog2.w(str, th);
            } else {
                Log.w(str, th);
            }
        }
    }

    public static void w(String str, Throwable th, String str2) {
        if (logLevel <= 5) {
            FbLog fbLog2 = fbLog;
            if (fbLog2 != null) {
                fbLog2.w(str, str2, th);
            } else {
                Log.w(str, str2, th);
            }
        }
    }

    public static void w(String str, Throwable th, String str2, Object obj) {
        if (logLevel <= 5) {
            w(str, th, String.format(str2, obj));
        }
    }

    public static void w(String str, Throwable th, String str2, Object obj, Object obj2) {
        if (logLevel <= 5) {
            w(str, th, String.format(str2, obj, obj2));
        }
    }

    public static void w(String str, Throwable th, String str2, Object obj, Object obj2, Object obj3) {
        if (logLevel <= 5) {
            w(str, th, String.format(str2, obj, obj2, obj3));
        }
    }

    public static void w(String str, Throwable th, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (logLevel <= 5) {
            w(str, th, String.format(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void w(String str, Throwable th, String str2, Object... objArr) {
        if (logLevel <= 5) {
            w(str, th, String.format(str2, objArr));
        }
    }
}
