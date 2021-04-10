package com.facebook.systrace;

import com.facebook.debug.log.BLog;
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

    public static void beginSection(long tag, String sectionName) {
        if (isTracing(tag)) {
            if (debugEnabled) {
                debugTraceStart();
            }
            TraceDirect.beginSection(sectionName);
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
                BLog.e("Systrace", "stopTracer called from a different class (%s) than startTracer : %s", stopCaller, startCaller);
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
                    BLog.w("Systrace", "stopTracer called from a different method (%s) than startTracer : %s", stopCaller, startCaller);
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

    public static boolean isTracing(long tag) {
        return TraceConfig.isTracing(tag) || (sForcedTags & tag) != 0;
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
}
