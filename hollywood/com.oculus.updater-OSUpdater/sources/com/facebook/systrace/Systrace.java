package com.facebook.systrace;

import com.facebook.debug.log.BLog;
import com.oculus.common.build.BuildConfig;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class Systrace {
    private static boolean debugEnabled;
    private static final String[][] sAuthorizedStartStopPairs = {new String[]{"com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onActivate", "com.facebook.common.fury.FBSystraceReqContextLifecycleCallbacks.onDeactivate"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.pluginMarkerEnd"}, new String[]{"com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsStart", "com.facebook.common.plugins.fblogging.FbPluginsLogger.onSocketGetPluginsEnd"}};
    private static final AtomicInteger sDebugCount = new AtomicInteger();
    private static final ThreadLocal<DebugData> sDebugData = new ThreadLocal<DebugData>() {
        /* class com.facebook.systrace.Systrace.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public DebugData initialValue() {
            return new DebugData();
        }
    };
    private static final String[] sExcludedClasses = {"dalvik.system.VMStack", "java.lang.Thread", "com.facebook.systrace.Systrace", "com.facebook.systrace.SystraceMessage", "com.facebook.litho.FbComponentsSystrace", "com.facebook.litho.ComponentsSystrace", "com.facebook.debug.tracer.Tracer"};
    private static long sForcedTags = 0;

    public enum EventScope {
        THREAD('t'),
        PROCESS('p'),
        GLOBAL('g');
        
        private final char mCode;

        private EventScope(char c) {
            this.mCode = c;
        }

        public char getCode() {
            return this.mCode;
        }
    }

    static {
        TraceConfig.setAppTracingAllowed(true);
        SystraceMetadata.init();
    }

    /* access modifiers changed from: private */
    public static class DebugData {
        private Stack<StackTraceElement> callers;
        private boolean isEnabled;

        private DebugData() {
        }
    }

    public static void beginSection(long j, String str) {
        if (isTracing(j)) {
            if (debugEnabled) {
                debugTraceStart();
            }
            TraceDirect.beginSection(str);
        }
    }

    private static void debugTraceStart() {
        DebugData debugData = sDebugData.get();
        if (debugData != null && debugData.isEnabled) {
            debugData.callers.push(getCallingStackTraceElement());
        }
    }

    private static void debugTraceStop() {
        boolean z;
        DebugData debugData = sDebugData.get();
        if (debugData != null && debugData.isEnabled) {
            StackTraceElement stackTraceElement = (StackTraceElement) debugData.callers.pop();
            String className = stackTraceElement.getClassName();
            StackTraceElement callingStackTraceElement = getCallingStackTraceElement();
            if (!className.equals(callingStackTraceElement.getClassName())) {
                BLog.e("Systrace", "stopTracer called from a different class (%s) than startTracer : %s", callingStackTraceElement, stackTraceElement);
            } else if (!stackTraceElement.getMethodName().equals(callingStackTraceElement.getMethodName())) {
                String str = className + "." + stackTraceElement.getMethodName();
                String str2 = callingStackTraceElement.getClassName() + "." + callingStackTraceElement.getMethodName();
                int i = 0;
                while (true) {
                    String[][] strArr = sAuthorizedStartStopPairs;
                    if (i < strArr.length) {
                        if (strArr[i][0].equals(str) && sAuthorizedStartStopPairs[i][1].equals(str2)) {
                            z = true;
                            break;
                        }
                        i++;
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    BLog.w("Systrace", "stopTracer called from a different method (%s) than startTracer : %s", callingStackTraceElement, stackTraceElement);
                }
            }
        }
    }

    private static StackTraceElement getCallingStackTraceElement() {
        boolean z;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();
            int i2 = 0;
            while (true) {
                String[] strArr = sExcludedClasses;
                if (i2 >= strArr.length) {
                    z = true;
                    break;
                } else if (className.startsWith(strArr[i2])) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                return stackTrace[i];
            }
        }
        return new StackTraceElement("<unknown>", "<unknown>", "<unknown>", 0);
    }

    public static void endSection(long j) {
        if (isTracing(j)) {
            if (debugEnabled) {
                debugTraceStop();
            }
            TraceDirect.endSection();
        }
    }

    public static boolean isTracing(long j) {
        return TraceConfig.isTracing(j) || (j & sForcedTags) != 0;
    }

    public static void traceMetadata(long j, String str, String str2, int i) {
        if (isTracing(j)) {
            TraceDirect.traceMetadata(str, str2, i);
        }
    }

    public static void traceInstant(long j, String str, EventScope eventScope) {
        if (isTracing(j)) {
            TraceDirect.traceInstant(BuildConfig.PROVIDER_SUFFIX, str, eventScope.getCode());
        }
    }
}
