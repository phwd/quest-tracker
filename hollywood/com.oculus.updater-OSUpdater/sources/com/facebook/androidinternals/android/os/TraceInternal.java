package com.facebook.androidinternals.android.os;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Trace;
import com.facebook.androidinternals.ReflectionHelper;
import com.facebook.infer.annotation.Assertions;
import com.facebook.ultralight.UL;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

public final class TraceInternal {
    public static final boolean ANDROID_OS_TRACE_SUPPORTED = (Build.VERSION.SDK_INT >= 18);
    public static final long TRACE_TAG_APP;
    private static volatile boolean sHiddenMembersEnabled;
    @Nullable
    private static final Method sIsTagEnabled;
    @Nullable
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

        @Nullable
        @TargetApi(UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID)
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

    private TraceInternal() {
    }

    public static boolean isTagEnabled(long j) {
        if (!sHiddenMembersEnabled) {
            return false;
        }
        Boolean bool = (Boolean) invoke((Method) Assertions.assertNotNull(sIsTagEnabled), Long.valueOf(j));
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static void setAppTracingAllowed(boolean z) {
        if (sHiddenMembersEnabled) {
            invoke((Method) Assertions.assertNotNull(sSetAppTracingAllowed), Boolean.valueOf(z));
        }
    }

    @Nullable
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
