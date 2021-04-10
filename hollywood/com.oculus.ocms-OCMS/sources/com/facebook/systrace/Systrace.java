package com.facebook.systrace;

import com.facebook.debug.log.BLog;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class Systrace {
    private static final String TAG = "Systrace";
    public static final long TRACE_TAG_ALLOC_COUNTING = 1024;
    public static final long TRACE_TAG_ALWAYS = 1;
    public static final long TRACE_TAG_APP_CORE_INFRA = 2147483648L;
    public static final long TRACE_TAG_APP_STATE = 8;
    public static final long TRACE_TAG_AR_DELIVERY = 2251799813685248L;
    public static final long TRACE_TAG_ASYNC = 128;
    public static final long TRACE_TAG_CLASS_LOADER = 549755813888L;
    public static final long TRACE_TAG_CLASS_LOADS = 34359738368L;
    public static final long TRACE_TAG_COMPACTDISK = 4294967296L;
    public static final long TRACE_TAG_COMPONENTS = 4194304;
    public static final long TRACE_TAG_COMPONENT_SCRIPT = 536870912;
    public static final long TRACE_TAG_CONTENT_PROVIDER = 512;
    public static final long TRACE_TAG_DATA_FETCH = 1099511627776L;
    public static final long TRACE_TAG_DATA_YOGA = 2199023255552L;
    public static final long TRACE_TAG_ERROR_REPORTING = 256;
    public static final long TRACE_TAG_EXTENSIONS = 70368744177664L;
    public static final long TRACE_TAG_FBLITE = 281474976710656L;
    public static final long TRACE_TAG_FRAME_RATE_EVENTS = 262144;
    public static final long TRACE_TAG_FRESCO = 68719476736L;
    public static final long TRACE_TAG_GRAPHSERVICES = 140737488355328L;
    public static final long TRACE_TAG_GRAPHSTORE = 2097152;
    public static final long TRACE_TAG_GRIMSEY_ALLOCATION = 1048576;
    public static final long TRACE_TAG_GRIMSEY_METHOD_EXECUTION = 524288;
    public static final long TRACE_TAG_GRIMSEY_NATIVE_ALLOCATION = 35184372088832L;
    public static final long TRACE_TAG_JS_VM = 67108864;
    public static final long TRACE_TAG_LS_PLUGINS = 4503599627370496L;
    public static final long TRACE_TAG_MAIN_LOOPER = 4096;
    public static final long TRACE_TAG_METADATA = 64;
    public static final long TRACE_TAG_NETWORK = 16;
    public static final long TRACE_TAG_NEVER = 0;
    public static final long TRACE_TAG_NT_CPP = 4398046511104L;
    public static final long TRACE_TAG_ORCA_MSYS_EXECUTOR = 562949953421312L;
    public static final long TRACE_TAG_ORCA_MSYS_INIT = 1125899906842624L;
    public static final long TRACE_TAG_PERF_LOGGER = 4;
    public static final long TRACE_TAG_PERF_LOGGER_EXT = 274877906944L;
    public static final long TRACE_TAG_PROFILO = 268435456;
    public static final long TRACE_TAG_REACT_APPS = 131072;
    public static final long TRACE_TAG_REACT_CXX_BRIDGE = 16384;
    public static final long TRACE_TAG_REACT_FRESCO = 16777216;
    public static final long TRACE_TAG_REACT_JAVA_BRIDGE = 8192;
    public static final long TRACE_TAG_REACT_JS_BRIDGE = 32768;
    public static final long TRACE_TAG_REACT_JS_INFRA = 65536;
    public static final long TRACE_TAG_REACT_JS_VM_CALLS = 134217728;
    public static final long TRACE_TAG_REACT_VIEW = 33554432;
    public static final long TRACE_TAG_RESOURCE_METRICS = 17592186044416L;
    public static final long TRACE_TAG_SEQ_LOGGER = 2;
    public static final long TRACE_TAG_TRACER = 32;
    public static final long TRACE_TAG_ULTRALIGHT = 137438953472L;
    public static final long TRACE_TAG_VIEW_PERF_HARNESS = 8388608;
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

    public static void setWarnOnUnclosedEnabled(boolean z) {
        DebugData debugData = sDebugData.get();
        if (debugData != null && debugData.isEnabled != z) {
            debugData.isEnabled = z;
            boolean z2 = true;
            if (z) {
                debugData.callers = new Stack();
                if (sDebugCount.incrementAndGet() <= 0) {
                    z2 = false;
                }
                debugEnabled = z2;
                return;
            }
            debugData.callers = null;
            if (sDebugCount.decrementAndGet() <= 0) {
                z2 = false;
            }
            debugEnabled = z2;
        }
    }

    public static void registerListener(TraceListener traceListener) {
        TraceConfig.addListener(traceListener);
    }

    public static void unregisterListener(TraceListener traceListener) {
        TraceConfig.removeListener(traceListener);
    }

    public static void enableExtraTracing() {
        SystraceMetadata.enableTidTracking();
    }

    public static void forceEnabledTagsForTest(long j) {
        TraceConfig.forceEnabledTags(j);
    }

    public static void beginSection(long j, String str) {
        if (isTracing(j)) {
            if (debugEnabled) {
                debugTraceStart();
            }
            TraceDirect.beginSection(str);
        }
    }

    public static void beginSection(long j, String str, String[] strArr, int i) {
        if (isTracing(j)) {
            if (debugEnabled) {
                debugTraceStart();
            }
            TraceDirect.beginSectionWithArgs(str, strArr, i);
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
                BLog.e(TAG, "stopTracer called from a different class (%s) than startTracer : %s", callingStackTraceElement, stackTraceElement);
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
                    BLog.w(TAG, "stopTracer called from a different method (%s) than startTracer : %s", callingStackTraceElement, stackTraceElement);
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

    public static void endSection(long j, String str, String[] strArr, int i) {
        if (isTracing(j)) {
            if (debugEnabled) {
                debugTraceStop();
            }
            TraceDirect.endSectionWithArgs(strArr, i);
        }
    }

    public static boolean isTracing(long j) {
        return TraceConfig.isTracing(j) || (j & sForcedTags) != 0;
    }

    public static void forceTags(long j) {
        sForcedTags = j;
    }

    public static void traceCounter(long j, String str, int i) {
        if (isTracing(j)) {
            TraceDirect.traceCounter(str, i);
        }
    }

    public static void beginAsyncSection(long j, String str, int i) {
        if (isTracing(j)) {
            TraceDirect.asyncTraceBegin(str, i, 0);
        }
    }

    public static void beginAsyncSection(long j, String str, int i, long j2) {
        if (isTracing(j)) {
            TraceDirect.asyncTraceBegin(str, i, FbSystrace.computeDeltaNanos(j2));
        }
    }

    public static void beginAsyncSectionStage(long j, String str, int i, String str2) {
        if (isTracing(j)) {
            TraceDirect.asyncTraceStageBegin(str, i, 0, str2);
        }
    }

    public static void beginAsyncSectionStage(long j, String str, int i, long j2, String str2) {
        if (isTracing(j)) {
            TraceDirect.asyncTraceStageBegin(str, i, FbSystrace.computeDeltaNanos(j2), str2);
        }
    }

    public static void endAsyncSection(long j, String str, int i) {
        if (isTracing(j)) {
            TraceDirect.asyncTraceEnd(str, i, 0);
        }
    }

    public static void endAsyncSection(long j, String str, int i, long j2) {
        if (isTracing(j)) {
            TraceDirect.asyncTraceEnd(str, i, FbSystrace.computeDeltaNanos(j2));
        }
    }

    public static void startAsyncFlow(long j, String str, int i) {
        if (isTracing(j)) {
            TraceDirect.startAsyncFlow(str, i);
        }
    }

    public static void stepAsyncFlow(long j, String str, int i) {
        if (isTracing(j)) {
            TraceDirect.stepAsyncFlow(str, i);
        }
    }

    public static void endAsyncFlow(long j, String str, int i) {
        if (isTracing(j)) {
            TraceDirect.endAsyncFlow(str, i);
        }
    }

    public static void traceMetadata(long j, String str, String str2, int i) {
        if (isTracing(j)) {
            TraceDirect.traceMetadata(str, str2, i);
        }
    }

    public static void traceInstant(long j, String str, EventScope eventScope) {
        if (isTracing(j)) {
            TraceDirect.traceInstant("", str, eventScope.getCode());
        }
    }

    public static void cancelAsyncSection(long j, String str, int i) {
        if (isTracing(j)) {
            TraceDirect.asyncTraceCancel(str, i);
        }
    }

    public static void renameAsyncSection(long j, String str, String str2, int i) {
        if (isTracing(j)) {
            TraceDirect.asyncTraceRename(str, str2, i);
        }
    }
}
