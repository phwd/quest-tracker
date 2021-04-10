package com.facebook.androidinternals.android.os;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Trace;
import com.facebook.androidinternals.ReflectionHelper;
import com.facebook.infer.annotation.Assertions;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

public final class TraceInternal {
    public static final boolean ANDROID_OS_TRACE_SUPPORTED;
    public static final int MAX_SECTION_NAME_LEN = 127;
    public static final long TRACE_TAG_APP;
    private static volatile boolean sHiddenMembersEnabled;
    @Nullable
    private static final Method sIsTagEnabled;
    @Nullable
    private static final Method sSetAppTracingAllowed;

    static {
        boolean z;
        if (Build.VERSION.SDK_INT >= 18) {
            z = true;
        } else {
            z = false;
        }
        ANDROID_OS_TRACE_SUPPORTED = z;
        SystraceHiddenMembers hiddenMembers = null;
        if (ANDROID_OS_TRACE_SUPPORTED) {
            hiddenMembers = SystraceHiddenMembers.newInstance();
        }
        if (hiddenMembers != null) {
            sIsTagEnabled = hiddenMembers.isTagEnabled;
            sSetAppTracingAllowed = hiddenMembers.setAppTracingAllowed;
            TRACE_TAG_APP = hiddenMembers.TRACE_TAG_APP;
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
        @TargetApi(18)
        public static SystraceHiddenMembers newInstance() {
            try {
                Method isTagEnabled2 = Trace.class.getMethod("isTagEnabled", Long.TYPE);
                Method setAppTracingAllowed2 = Trace.class.getMethod("setAppTracingAllowed", Boolean.TYPE);
                Field appTraceTagField = Trace.class.getField("TRACE_TAG_APP");
                if (appTraceTagField.getType() != Long.TYPE) {
                    return null;
                }
                return new SystraceHiddenMembers(isTagEnabled2, setAppTracingAllowed2, appTraceTagField.getLong(null));
            } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException e) {
                return null;
            }
        }

        public SystraceHiddenMembers(Method isTagEnabled2, Method setAppTracingAllowed2, long traceTagApp) {
            this.isTagEnabled = isTagEnabled2;
            this.setAppTracingAllowed = setAppTracingAllowed2;
            this.TRACE_TAG_APP = traceTagApp;
        }
    }

    private TraceInternal() {
    }

    public static boolean isTagEnabled(long traceTag) {
        if (!sHiddenMembersEnabled) {
            return false;
        }
        Boolean result = (Boolean) invoke((Method) Assertions.assertNotNull(sIsTagEnabled), Long.valueOf(traceTag));
        if (result == null) {
            return false;
        }
        return result.booleanValue();
    }

    public static void setAppTracingAllowed(boolean allowed) {
        if (sHiddenMembersEnabled) {
            invoke((Method) Assertions.assertNotNull(sSetAppTracingAllowed), Boolean.valueOf(allowed));
        }
    }

    @Nullable
    private static Object invoke(Method method, Object... args) {
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
