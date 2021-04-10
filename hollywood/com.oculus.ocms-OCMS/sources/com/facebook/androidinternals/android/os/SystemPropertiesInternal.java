package com.facebook.androidinternals.android.os;

import android.os.Build;
import com.facebook.androidinternals.ReflectionHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

public final class SystemPropertiesInternal {
    @Nullable
    private static final Method sAddChangeCallback;
    @Nullable
    private static final Method sGetBoolean;
    @Nullable
    private static final Method sGetEmptyDefault;
    @Nullable
    private static final Method sGetLong;
    private static volatile boolean sHiddenMembersEnabled;
    @Nullable
    private static final Method sSet;

    private static class SystemPropertiesHiddenMembers {
        @Nullable
        public final Method addChangeCallback;
        public final Method getBoolean;
        public final Method getEmptyDefault;
        public final Method getLong;
        public final Method set;

        @Nullable
        public static SystemPropertiesHiddenMembers newInstance() {
            Method method;
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                Method method2 = cls.getMethod("get", String.class);
                Method method3 = cls.getMethod("getBoolean", String.class, Boolean.TYPE);
                Method method4 = cls.getMethod("getLong", String.class, Long.TYPE);
                Method method5 = cls.getMethod("set", String.class, String.class);
                if (Build.VERSION.SDK_INT >= 16) {
                    method = cls.getMethod("addChangeCallback", Runnable.class);
                } else {
                    method = null;
                }
                return new SystemPropertiesHiddenMembers(method, method2, method3, method4, method5);
            } catch (ClassNotFoundException | NoSuchMethodException unused) {
                return null;
            }
        }

        private SystemPropertiesHiddenMembers(@Nullable Method method, Method method2, Method method3, Method method4, Method method5) {
            this.addChangeCallback = method;
            this.getEmptyDefault = method2;
            this.getBoolean = method3;
            this.getLong = method4;
            this.set = method5;
        }
    }

    static {
        SystemPropertiesHiddenMembers newInstance = SystemPropertiesHiddenMembers.newInstance();
        if (newInstance != null) {
            sAddChangeCallback = newInstance.addChangeCallback;
            sGetEmptyDefault = newInstance.getEmptyDefault;
            sGetBoolean = newInstance.getBoolean;
            sGetLong = newInstance.getLong;
            sSet = newInstance.set;
            sHiddenMembersEnabled = true;
            return;
        }
        sAddChangeCallback = null;
        sGetEmptyDefault = null;
        sGetBoolean = null;
        sGetLong = null;
        sSet = null;
        sHiddenMembersEnabled = false;
    }

    private SystemPropertiesInternal() {
    }

    public static String get(String str) {
        if (!sHiddenMembersEnabled) {
            return "";
        }
        String str2 = (String) invoke(sGetEmptyDefault, str);
        if (str2 == null) {
            return "";
        }
        return str2;
    }

    public static String get(String str, String str2) {
        String str3 = get(str);
        return (str3 == null || str3.isEmpty()) ? str2 : str3;
    }

    public static boolean getBoolean(String str, boolean z) {
        if (!sHiddenMembersEnabled) {
            return z;
        }
        Boolean bool = (Boolean) invoke(sGetBoolean, str, Boolean.valueOf(z));
        return bool != null ? bool.booleanValue() : z;
    }

    public static long getLong(String str, long j) {
        if (!sHiddenMembersEnabled) {
            return j;
        }
        Long l = (Long) invoke(sGetLong, str, Long.valueOf(j));
        if (l == null) {
            return j;
        }
        return l.longValue();
    }

    public static void set(String str, String str2) {
        if (sHiddenMembersEnabled) {
            invoke(sSet, str, str2);
        }
    }

    public static void addChangeCallback(Runnable runnable) {
        if (sHiddenMembersEnabled) {
            invoke(sAddChangeCallback, runnable);
        }
    }

    @Nullable
    private static Object invoke(@Nullable Method method, Object... objArr) {
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(null, objArr);
        } catch (IllegalAccessException unused) {
            sHiddenMembersEnabled = false;
            return null;
        } catch (InvocationTargetException e) {
            ReflectionHelper.rethrowRuntimeExceptions(e);
            return null;
        }
    }
}
