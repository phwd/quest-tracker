package com.facebook.androidinternals.android.os;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Trace;
import com.facebook.androidinternals.ReflectionHelper;
import com.facebook.infer.annotation.Assertions;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class TraceInternal {
    public static final boolean ANDROID_OS_TRACE_SUPPORTED = (Build.VERSION.SDK_INT >= 18);
    public static final long TRACE_TAG_APP;
    private static volatile boolean sHiddenMembersEnabled;
    private static final Method sIsTagEnabled;
    private static final Method sSetAppTracingAllowed;

    static {
        SystraceHiddenMembers newInstance = ANDROID_OS_TRACE_SUPPORTED ? SystraceHiddenMembers.newInstance() : null;
        if (newInstance != null) {
            sIsTagEnabled = newInstance.isTagEnabled;
            sSetAppTracingAllowed = newInstance.setAppTracingAllowed;
            TRACE_TAG_APP = newInstance.TRACE_TAG_APP;
            sHiddenMembersEnabled = true;
            return;
        }
        sIsTagEnabled = null;
        sSetAppTracingAllowed = null;
        TRACE_TAG_APP = 0;
        sHiddenMembersEnabled = false;
    }

    private static class SystraceHiddenMembers {
        public final long TRACE_TAG_APP;
        public final Method isTagEnabled;
        public final Method setAppTracingAllowed;

        @TargetApi(18)
        public static SystraceHiddenMembers newInstance() {
            try {
                Method method = Trace.class.getMethod("isTagEnabled", Long.TYPE);
                Method method2 = Trace.class.getMethod("setAppTracingAllowed", Boolean.TYPE);
                Field field = Trace.class.getField("TRACE_TAG_APP");
                if (field.getType() != Long.TYPE) {
                    return null;
                }
                return new SystraceHiddenMembers(method, method2, field.getLong(null));
            } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException unused) {
                return null;
            }
        }

        public SystraceHiddenMembers(Method method, Method method2, long j) {
            this.isTagEnabled = method;
            this.setAppTracingAllowed = method2;
            this.TRACE_TAG_APP = j;
        }
    }

    public static boolean isTagEnabled(long j) {
        if (!sHiddenMembersEnabled) {
            return false;
        }
        Method method = sIsTagEnabled;
        Assertions.assertNotNull(method);
        Boolean bool = (Boolean) invoke(method, Long.valueOf(j));
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static void setAppTracingAllowed(boolean z) {
        if (sHiddenMembersEnabled) {
            Method method = sSetAppTracingAllowed;
            Assertions.assertNotNull(method);
            invoke(method, Boolean.valueOf(z));
        }
    }

    private static Object invoke(Method method, Object... objArr) {
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
