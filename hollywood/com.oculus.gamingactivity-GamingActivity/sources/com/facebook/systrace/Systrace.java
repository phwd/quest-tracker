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

        private EventScope(char code) {
            this.mCode = code;
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

    public static void setWarnOnUnclosedEnabled(boolean enabled) {
        boolean z = true;
        DebugData debugData = sDebugData.get();
        if (debugData != null && debugData.isEnabled != enabled) {
            debugData.isEnabled = enabled;
            if (enabled) {
                debugData.callers = new Stack();
                if (sDebugCount.incrementAndGet() <= 0) {
                    z = false;
                }
                debugEnabled = z;
                return;
            }
            debugData.callers = null;
            if (sDebugCount.decrementAndGet() <= 0) {
                z = false;
            }
            debugEnabled = z;
        }
    }

    public static void registerListener(TraceListener listener) {
        TraceConfig.addListener(listener);
    }

    public static void unregisterListener(TraceListener listener) {
        TraceConfig.removeListener(listener);
    }

    public static void enableExtraTracing() {
        SystraceMetadata.enableTidTracking();
    }

    public static void forceEnabledTagsForTest(long tags) {
        TraceConfig.forceEnabledTags(tags);
    }

    public static void beginSection(long tag, String sectionName) {
        if (isTracing(tag)) {
            if (debugEnabled) {
                debugTraceStart();
            }
            TraceDirect.beginSection(sectionName);
        }
    }

    public static void beginSection(long tag, String sectionName, String[] args, int argsLength) {
        if (isTracing(tag)) {
            if (debugEnabled) {
                debugTraceStart();
            }
            TraceDirect.beginSectionWithArgs(sectionName, args, argsLength);
        }
    }

    private static void debugTraceStart() {
        DebugData debugData = sDebugData.get();
        if (debugData != null && debugData.isEnabled) {
            debugData.callers.push(getCallingStackTraceElement());
        }
    }

    private static void debugTraceStop() {
        DebugData debugData = sDebugData.get();
        if (debugData != null && debugData.isEnabled) {
            StackTraceElement startCaller = (StackTraceElement) debugData.callers.pop();
            String startCallerClass = startCaller.getClassName();
            StackTraceElement stopCaller = getCallingStackTraceElement();
            if (!startCallerClass.equals(stopCaller.getClassName())) {
                BLog.e(TAG, "stopTracer called from a different class (%s) than startTracer : %s", stopCaller, startCaller);
            } else if (!startCaller.getMethodName().equals(stopCaller.getMethodName())) {
                boolean authorized = false;
                String fullyQualifiedStartCall = startCallerClass + "." + startCaller.getMethodName();
                String fullyQualifiedStopCall = stopCaller.getClassName() + "." + stopCaller.getMethodName();
                int i = 0;
                while (true) {
                    if (i < sAuthorizedStartStopPairs.length) {
                        if (sAuthorizedStartStopPairs[i][0].equals(fullyQualifiedStartCall) && sAuthorizedStartStopPairs[i][1].equals(fullyQualifiedStopCall)) {
                            authorized = true;
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
                if (!authorized) {
                    BLog.w(TAG, "stopTracer called from a different method (%s) than startTracer : %s", stopCaller, startCaller);
                }
            }
        }
    }

    private static StackTraceElement getCallingStackTraceElement() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTraceElements.length; i++) {
            String className = stackTraceElements[i].getClassName();
            boolean valid = true;
            int j = 0;
            while (true) {
                if (j >= sExcludedClasses.length) {
                    break;
                } else if (className.startsWith(sExcludedClasses[j])) {
                    valid = false;
                    break;
                } else {
                    j++;
                }
            }
            if (valid) {
                return stackTraceElements[i];
            }
        }
        return new StackTraceElement("<unknown>", "<unknown>", "<unknown>", 0);
    }

    public static void endSection(long tag) {
        if (isTracing(tag)) {
            if (debugEnabled) {
                debugTraceStop();
            }
            TraceDirect.endSection();
        }
    }

    public static void endSection(long tag, String sectionName, String[] args, int argsLength) {
        if (isTracing(tag)) {
            if (debugEnabled) {
                debugTraceStop();
            }
            TraceDirect.endSectionWithArgs(args, argsLength);
        }
    }

    public static boolean isTracing(long tag) {
        return TraceConfig.isTracing(tag) || (sForcedTags & tag) != 0;
    }

    public static void forceTags(long tags) {
        sForcedTags = tags;
    }

    public static void traceCounter(long tag, String counterName, int counterValue) {
        if (isTracing(tag)) {
            TraceDirect.traceCounter(counterName, counterValue);
        }
    }

    public static void beginAsyncSection(long tag, String sectionName, int cookie) {
        if (isTracing(tag)) {
            TraceDirect.asyncTraceBegin(sectionName, cookie, 0);
        }
    }

    public static void beginAsyncSection(long tag, String sectionName, int cookie, long startNanos) {
        if (isTracing(tag)) {
            TraceDirect.asyncTraceBegin(sectionName, cookie, FbSystrace.computeDeltaNanos(startNanos));
        }
    }

    public static void beginAsyncSectionStage(long tag, String sectionName, int cookie, String stageName) {
        if (isTracing(tag)) {
            TraceDirect.asyncTraceStageBegin(sectionName, cookie, 0, stageName);
        }
    }

    public static void beginAsyncSectionStage(long tag, String sectionName, int cookie, long beginNanos, String stageName) {
        if (isTracing(tag)) {
            TraceDirect.asyncTraceStageBegin(sectionName, cookie, FbSystrace.computeDeltaNanos(beginNanos), stageName);
        }
    }

    public static void endAsyncSection(long tag, String sectionName, int cookie) {
        if (isTracing(tag)) {
            TraceDirect.asyncTraceEnd(sectionName, cookie, 0);
        }
    }

    public static void endAsyncSection(long tag, String sectionName, int cookie, long endNanos) {
        if (isTracing(tag)) {
            TraceDirect.asyncTraceEnd(sectionName, cookie, FbSystrace.computeDeltaNanos(endNanos));
        }
    }

    public static void startAsyncFlow(long tag, String sectionName, int cookie) {
        if (isTracing(tag)) {
            TraceDirect.startAsyncFlow(sectionName, cookie);
        }
    }

    public static void stepAsyncFlow(long tag, String sectionName, int cookie) {
        if (isTracing(tag)) {
            TraceDirect.stepAsyncFlow(sectionName, cookie);
        }
    }

    public static void endAsyncFlow(long tag, String sectionName, int cookie) {
        if (isTracing(tag)) {
            TraceDirect.endAsyncFlow(sectionName, cookie);
        }
    }

    public static void traceMetadata(long tag, String name, String value, int tid) {
        if (isTracing(tag)) {
            TraceDirect.traceMetadata(name, value, tid);
        }
    }

    public static void traceInstant(long tag, String title, EventScope scope) {
        if (isTracing(tag)) {
            TraceDirect.traceInstant("", title, scope.getCode());
        }
    }

    public static void cancelAsyncSection(long tag, String sectionName, int cookie) {
        if (isTracing(tag)) {
            TraceDirect.asyncTraceCancel(sectionName, cookie);
        }
    }

    public static void renameAsyncSection(long tag, String oldSectionName, String newSectionName, int cookie) {
        if (isTracing(tag)) {
            TraceDirect.asyncTraceRename(oldSectionName, newSectionName, cookie);
        }
    }
}
