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
            Method addChangeCallback2;
            try {
                Class systemProperties = Class.forName("android.os.SystemProperties");
                Method getEmptyDefault2 = systemProperties.getMethod("get", String.class);
                Method getBoolean2 = systemProperties.getMethod("getBoolean", String.class, Boolean.TYPE);
                Method getLong2 = systemProperties.getMethod("getLong", String.class, Long.TYPE);
                Method set2 = systemProperties.getMethod("set", String.class, String.class);
                if (Build.VERSION.SDK_INT >= 16) {
                    addChangeCallback2 = systemProperties.getMethod("addChangeCallback", Runnable.class);
                } else {
                    addChangeCallback2 = null;
                }
                return new SystemPropertiesHiddenMembers(addChangeCallback2, getEmptyDefault2, getBoolean2, getLong2, set2);
            } catch (ClassNotFoundException e) {
                return null;
            } catch (NoSuchMethodException e2) {
                return null;
            }
        }

        private SystemPropertiesHiddenMembers(@Nullable Method addChangeCallback2, Method getEmptyDefault2, Method getBoolean2, Method getLong2, Method set2) {
            this.addChangeCallback = addChangeCallback2;
            this.getEmptyDefault = getEmptyDefault2;
            this.getBoolean = getBoolean2;
            this.getLong = getLong2;
            this.set = set2;
        }
    }

    static {
        SystemPropertiesHiddenMembers hiddenMembers = SystemPropertiesHiddenMembers.newInstance();
        if (hiddenMembers != null) {
            sAddChangeCallback = hiddenMembers.addChangeCallback;
            sGetEmptyDefault = hiddenMembers.getEmptyDefault;
            sGetBoolean = hiddenMembers.getBoolean;
            sGetLong = hiddenMembers.getLong;
            sSet = hiddenMembers.set;
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

    public static String get(String key) {
        if (!sHiddenMembersEnabled) {
            return "";
        }
        String result = (String) invoke(sGetEmptyDefault, key);
        if (result == null) {
            return "";
        }
        return result;
    }

    public static String get(String key, String def) {
        String result = get(key);
        return (result == null || result.isEmpty()) ? def : result;
    }

    public static boolean getBoolean(String key, boolean def) {
        if (!sHiddenMembersEnabled) {
            return def;
        }
        Boolean booleanObj = (Boolean) invoke(sGetBoolean, key, Boolean.valueOf(def));
        return booleanObj != null ? booleanObj.booleanValue() : def;
    }

    public static long getLong(String key, long def) {
        if (!sHiddenMembersEnabled) {
            return def;
        }
        Long result = (Long) invoke(sGetLong, key, Long.valueOf(def));
        return result != null ? result.longValue() : def;
    }

    public static void set(String key, String value) {
        if (sHiddenMembersEnabled) {
            invoke(sSet, key, value);
        }
    }

    public static void addChangeCallback(Runnable callback) {
        if (sHiddenMembersEnabled) {
            invoke(sAddChangeCallback, callback);
        }
    }

    @Nullable
    private static Object invoke(@Nullable Method method, Object... args) {
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(null, args);
        } catch (IllegalAccessException e) {
            sHiddenMembersEnabled = false;
            return null;
        } catch (InvocationTargetException e2) {
            ReflectionHelper.rethrowRuntimeExceptions(e2);
            return null;
        }
    }
}
