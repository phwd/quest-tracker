package org.webrtc;

import X.AnonymousClass006;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {
    public static final Logger fallbackLogger = Logger.getLogger("org.webrtc.Logging");
    public static volatile boolean nativeLibLoaded;
    public static volatile boolean tracingEnabled;
    public static final boolean useNativeLogging = false;

    public enum Severity {
        LS_SENSITIVE,
        LS_VERBOSE,
        LS_INFO,
        LS_WARNING,
        LS_ERROR
    }

    public static native void nativeEnableLogThreads();

    public static native void nativeEnableLogTimeStamps();

    public static native void nativeEnableTracing(String str, int i, int i2);

    public static native void nativeLog(int i, String str, String str2);

    /* renamed from: org.webrtc.Logging$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$org$webrtc$Logging$Severity;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                org.webrtc.Logging$Severity[] r0 = org.webrtc.Logging.Severity.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                org.webrtc.Logging.AnonymousClass1.$SwitchMap$org$webrtc$Logging$Severity = r2
                org.webrtc.Logging$Severity r0 = org.webrtc.Logging.Severity.LS_ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                org.webrtc.Logging$Severity r0 = org.webrtc.Logging.Severity.LS_WARNING     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                org.webrtc.Logging$Severity r0 = org.webrtc.Logging.Severity.LS_INFO     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.webrtc.Logging.AnonymousClass1.<clinit>():void");
        }
    }

    public enum TraceLevel {
        TRACE_NONE(0),
        TRACE_STATEINFO(1),
        TRACE_WARNING(2),
        TRACE_ERROR(4),
        TRACE_CRITICAL(8),
        TRACE_APICALL(16),
        TRACE_DEFAULT(255),
        TRACE_MODULECALL(32),
        TRACE_MEMORY(256),
        TRACE_TIMER(512),
        TRACE_STREAM(1024),
        TRACE_DEBUG(2048),
        TRACE_INFO(4096),
        TRACE_TERSEINFO(8192),
        TRACE_ALL(65535);
        
        public final int level;

        /* access modifiers changed from: public */
        TraceLevel(int i) {
            this.level = i;
        }
    }

    public static void d(String str, String str2) {
        log(Severity.LS_INFO, str, str2);
    }

    public static void enableLogThreads() {
        if (!nativeLibLoaded) {
            fallbackLogger.log(Level.WARNING, "Cannot enable log thread because native lib not loaded.");
        } else {
            nativeEnableLogThreads();
        }
    }

    public static void enableLogTimeStamps() {
        if (!nativeLibLoaded) {
            fallbackLogger.log(Level.WARNING, "Cannot enable log timestamps because native lib not loaded.");
        } else {
            nativeEnableLogTimeStamps();
        }
    }

    public static synchronized void enableTracing(String str, EnumSet<TraceLevel> enumSet, Severity severity) {
        synchronized (Logging.class) {
            if (!nativeLibLoaded) {
                fallbackLogger.log(Level.WARNING, "Cannot enable tracing because native lib not loaded.");
            } else if (!tracingEnabled) {
                int i = 0;
                Iterator<E> it = enumSet.iterator();
                while (it.hasNext()) {
                    i |= it.next().level;
                }
                nativeEnableTracing(str, i, severity.ordinal());
                tracingEnabled = true;
            }
        }
    }

    public static String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static void log(Severity severity, String str, String str2) {
        Level level;
        boolean z = tracingEnabled;
        int ordinal = severity.ordinal();
        if (z) {
            nativeLog(ordinal, str, str2);
            return;
        }
        switch (ordinal) {
            case 2:
                level = Level.INFO;
                break;
            case 3:
                level = Level.WARNING;
                break;
            case 4:
                level = Level.SEVERE;
                break;
            default:
                level = Level.FINE;
                break;
        }
        fallbackLogger.log(level, AnonymousClass006.A07(str, ": ", str2));
    }

    public static void v(String str, String str2) {
        log(Severity.LS_VERBOSE, str, str2);
    }

    public static void e(String str, String str2) {
        log(Severity.LS_ERROR, str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        log(Severity.LS_ERROR, str, str2);
        Severity severity = Severity.LS_ERROR;
        log(severity, str, th.toString());
        log(severity, str, getStackTraceString(th));
    }

    public static void w(String str, String str2) {
        log(Severity.LS_WARNING, str, str2);
    }

    public static void w(String str, String str2, Throwable th) {
        log(Severity.LS_WARNING, str, str2);
        Severity severity = Severity.LS_WARNING;
        log(severity, str, th.toString());
        log(severity, str, getStackTraceString(th));
    }
}
