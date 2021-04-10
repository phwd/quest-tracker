package com.oculus.panellib;

import android.os.Process;
import android.os.Trace;
import android.util.Log;
import com.oculus.common.build.BuildConfig;
import java.util.HashMap;

public class Systrace {
    private static final String DEFAULT_THREAD_NAME = "Java";
    private static final int PID = 4;
    private static final int START_EVENT_ID = 1000000;
    private static final String TAG = "Systrace";
    private static String buffer = BuildConfig.PROVIDER_SUFFIX;
    private static final Object bufferLock = new Object();
    private static int curEventId = 1000000;
    private static HashMap<Integer, Event> events = new HashMap<>();
    private static final Object eventsLock = new Object();
    private static boolean isEnabled = false;
    private static boolean isSystemTracingEnabled = false;
    private static HashMap<Long, String> threadNames = new HashMap<>();

    /* access modifiers changed from: package-private */
    public enum EventType {
        BLOCK,
        ASYNC
    }

    /* access modifiers changed from: package-private */
    public static class Event {
        public long endTime = -1;
        public final EventType eventType;
        public final int id;
        public final String name;
        public final long startTime;
        public final long threadId;

        public Event(int i, String str, EventType eventType2, long j, long j2) {
            this.id = i;
            this.name = str;
            this.eventType = eventType2;
            this.startTime = j;
            this.threadId = j2;
        }

        public String toString() {
            if (this.eventType == EventType.BLOCK) {
                double d = (double) this.startTime;
                Double.isNaN(d);
                String format = String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"%f\",\"ph\":\"B\",\"name\":\"%s\"}", Long.valueOf(this.threadId), 4, Double.valueOf(d / 1000.0d), this.name);
                double d2 = (double) this.endTime;
                Double.isNaN(d2);
                return String.format("%s,\n%s,\n", format, String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"%f\",\"ph\":\"E\"}", Long.valueOf(this.threadId), 4, Double.valueOf(d2 / 1000.0d)));
            } else if (this.eventType == EventType.ASYNC) {
                double d3 = (double) this.startTime;
                Double.isNaN(d3);
                String format2 = String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"%f\",\"ph\":\"b\",\"cat\":\"async\",\"id\":%d,\"name\":\"%s\"}", Long.valueOf(this.threadId), 4, Double.valueOf(d3 / 1000.0d), Integer.valueOf(this.id), this.name);
                double d4 = (double) this.endTime;
                Double.isNaN(d4);
                return String.format("%s,\n%s,\n", format2, String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"%f\",\"ph\":\"e\",\"cat\":\"async\",\"id\":%d,\"name\":\"%s\"}", Long.valueOf(this.threadId), 4, Double.valueOf(d4 / 1000.0d), Integer.valueOf(this.id), this.name));
            } else {
                Log.e(Systrace.TAG, "Unsupported event type for systrace block: " + this.name);
                return BuildConfig.PROVIDER_SUFFIX;
            }
        }
    }

    public static void enable(boolean z) {
        if (isEnabled != z) {
            isEnabled = z;
            Object[] objArr = new Object[1];
            objArr[0] = isEnabled ? "enabled" : "disabled";
            Log.i(TAG, String.format("Systrace is %s in Java.", objArr));
            if (!isEnabled) {
                flushBuffer();
                events.clear();
                threadNames.clear();
            }
        }
    }

    public static void enableSystemTracing(boolean z) {
        if (isSystemTracingEnabled != z) {
            isSystemTracingEnabled = z;
            Object[] objArr = new Object[1];
            objArr[0] = isSystemTracingEnabled ? "enabled" : "disabled";
            Log.i(TAG, String.format("Additional tracing via Trace API is %s in Java.", objArr));
        }
    }

    public static boolean isEnabled() {
        return isEnabled;
    }

    public static int begin(EventType eventType, String str) {
        return begin(eventType, str, System.nanoTime());
    }

    public static int begin(EventType eventType, String str, long j) {
        int i;
        if (!isEnabled) {
            return 0;
        }
        synchronized (eventsLock) {
            i = curEventId + 1;
            curEventId = i;
            events.put(Integer.valueOf(i), new Event(i, str, eventType, j, (long) Process.myTid()));
        }
        if (isSystemTracingEnabled && eventType == EventType.BLOCK) {
            Trace.beginSection(str);
        }
        return i;
    }

    public static int beginBlock(String str) {
        return begin(EventType.BLOCK, str);
    }

    public static int beginBlock(String str, long j) {
        return begin(EventType.BLOCK, str, j);
    }

    public static int beginBlock(String str, String str2, long j) {
        return beginBlock(str + "::" + str2, j);
    }

    public static int beginBlock(String str, String str2) {
        return beginBlock(str + "::" + str2);
    }

    public static int beginAsync(String str) {
        return begin(EventType.ASYNC, str);
    }

    public static int beginAsync(String str, String str2) {
        return beginAsync(str + "::" + str2);
    }

    public static void end(int i) {
        Event event;
        if (isEnabled) {
            synchronized (eventsLock) {
                event = events.get(Integer.valueOf(i));
                events.remove(Integer.valueOf(i));
            }
            if (event != null) {
                if (isSystemTracingEnabled && event.eventType == EventType.BLOCK) {
                    Trace.endSection();
                }
                event.endTime = System.nanoTime();
                long myTid = (long) Process.myTid();
                if (myTid != event.threadId && event.eventType == EventType.BLOCK) {
                    Log.e(TAG, String.format("Systrace block event %s started in thread %d and ended in a different thread %d", Long.valueOf(event.threadId), Long.valueOf(myTid)));
                }
                if (getThreadName(myTid) == null) {
                    setThreadName();
                }
                String event2 = event.toString();
                synchronized (bufferLock) {
                    buffer += event2;
                }
                return;
            }
            Log.e(TAG, String.format("Systrace.end: block with ID=%d either does not exist or was already closed", Integer.valueOf(i)));
        }
    }

    public static String getThreadName(long j) {
        return threadNames.get(Long.valueOf(j));
    }

    public static void setThreadName(String str, boolean z) {
        if (isEnabled) {
            long myTid = (long) Process.myTid();
            threadNames.put(Long.valueOf(myTid), str);
            if (z) {
                str = String.format("%s [%d]", str, Long.valueOf(myTid));
            }
            String format = String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"0.0\",\"ph\":\"M\",\"name\":\"thread_name\",\"args\":{\"name\":\"%s\"}},\n", Long.valueOf(myTid), 4, str);
            synchronized (bufferLock) {
                buffer += format;
            }
        }
    }

    public static void setThreadName(String str) {
        setThreadName(str, true);
    }

    public static void setThreadName() {
        if (isEnabled) {
            String name = Thread.currentThread().getName();
            if (name.isEmpty()) {
                name = DEFAULT_THREAD_NAME;
            }
            setThreadName(name);
        }
    }

    public static String flushBuffer() {
        String str;
        if (!isEnabled) {
            return BuildConfig.PROVIDER_SUFFIX;
        }
        synchronized (bufferLock) {
            str = buffer;
            buffer = BuildConfig.PROVIDER_SUFFIX;
        }
        return str;
    }
}
