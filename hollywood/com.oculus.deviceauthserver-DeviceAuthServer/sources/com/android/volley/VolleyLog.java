package com.android.volley;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VolleyLog {
    private static final String CLASS_NAME = VolleyLog.class.getName();
    public static boolean DEBUG = Log.isLoggable(TAG, 2);
    public static String TAG = "Volley";

    public static void setTag(String tag) {
        d("Changing log tag to %s", tag);
        TAG = tag;
        DEBUG = Log.isLoggable(TAG, 2);
    }

    public static void v(String format, Object... args) {
        if (DEBUG) {
            Log.v(TAG, buildMessage(format, args));
        }
    }

    public static void d(String format, Object... args) {
        Log.d(TAG, buildMessage(format, args));
    }

    public static void e(String format, Object... args) {
        Log.e(TAG, buildMessage(format, args));
    }

    public static void e(Throwable tr, String format, Object... args) {
        Log.e(TAG, buildMessage(format, args), tr);
    }

    public static void wtf(String format, Object... args) {
        Log.wtf(TAG, buildMessage(format, args));
    }

    public static void wtf(Throwable tr, String format, Object... args) {
        Log.wtf(TAG, buildMessage(format, args), tr);
    }

    private static String buildMessage(String format, Object... args) {
        String msg = args == null ? format : String.format(Locale.US, format, args);
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "<unknown>";
        int i = 2;
        while (true) {
            if (i >= trace.length) {
                break;
            } else if (!trace[i].getClassName().equals(CLASS_NAME)) {
                String callingClass = trace[i].getClassName();
                String callingClass2 = callingClass.substring(callingClass.lastIndexOf(46) + 1);
                caller = callingClass2.substring(callingClass2.lastIndexOf(36) + 1) + "." + trace[i].getMethodName();
                break;
            } else {
                i++;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), caller, msg);
    }

    /* access modifiers changed from: package-private */
    public static class MarkerLog {
        public static final boolean ENABLED = VolleyLog.DEBUG;
        private static final long MIN_DURATION_FOR_LOGGING_MS = 0;
        private boolean mFinished = false;
        private final List<Marker> mMarkers = new ArrayList();

        MarkerLog() {
        }

        /* access modifiers changed from: private */
        public static class Marker {
            public final String name;
            public final long thread;
            public final long time;

            public Marker(String name2, long thread2, long time2) {
                this.name = name2;
                this.thread = thread2;
                this.time = time2;
            }
        }

        public synchronized void add(String name, long threadId) {
            if (!this.mFinished) {
                this.mMarkers.add(new Marker(name, threadId, SystemClock.elapsedRealtime()));
            } else {
                throw new IllegalStateException("Marker added to finished log");
            }
        }

        public synchronized void finish(String header) {
            this.mFinished = true;
            long duration = getTotalDuration();
            if (duration > 0) {
                long prevTime = this.mMarkers.get(0).time;
                VolleyLog.d("(%-4d ms) %s", Long.valueOf(duration), header);
                for (Marker marker : this.mMarkers) {
                    long thisTime = marker.time;
                    VolleyLog.d("(+%-4d) [%2d] %s", Long.valueOf(thisTime - prevTime), Long.valueOf(marker.thread), marker.name);
                    prevTime = thisTime;
                }
            }
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            if (!this.mFinished) {
                finish("Request on the loose");
                VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        private long getTotalDuration() {
            if (this.mMarkers.size() == 0) {
                return 0;
            }
            long first = this.mMarkers.get(0).time;
            List<Marker> list = this.mMarkers;
            return list.get(list.size() - 1).time - first;
        }
    }
}
