package com.oculus.panellib;

import X.AnonymousClass006;
import android.os.Process;
import android.os.Trace;
import android.util.Log;
import java.util.HashMap;

public class Systrace {
    public static final String DEFAULT_THREAD_NAME = "Java";
    public static final int PID = 4;
    public static final int START_EVENT_ID = 1000000;
    public static final String TAG = "Systrace";
    public static String buffer = "";
    public static final Object bufferLock = new Object();
    public static int curEventId = 1000000;
    public static HashMap<Integer, Event> events = new HashMap<>();
    public static final Object eventsLock = new Object();
    public static boolean isEnabled;
    public static boolean isSystemTracingEnabled;
    public static HashMap<Long, String> threadNames = new HashMap<>();

    public static class Event {
        public long endTime = -1;
        public final EventType eventType;
        public final int id;
        public final String name;
        public final long startTime;
        public final long threadId;

        public String toString() {
            String format;
            Object[] objArr;
            String str;
            EventType eventType2 = this.eventType;
            if (eventType2 == EventType.BLOCK) {
                Long valueOf = Long.valueOf(this.threadId);
                format = String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"%f\",\"ph\":\"B\",\"name\":\"%s\"}", valueOf, 4, Double.valueOf(((double) this.startTime) / 1000.0d), this.name);
                objArr = new Object[]{valueOf, 4, Double.valueOf(((double) this.endTime) / 1000.0d)};
                str = "{\"tid\":%d,\"pid\":%d,\"ts\":\"%f\",\"ph\":\"E\"}";
            } else if (eventType2 == EventType.ASYNC) {
                Long valueOf2 = Long.valueOf(this.threadId);
                Double valueOf3 = Double.valueOf(((double) this.startTime) / 1000.0d);
                Integer valueOf4 = Integer.valueOf(this.id);
                String str2 = this.name;
                format = String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"%f\",\"ph\":\"b\",\"cat\":\"async\",\"id\":%d,\"name\":\"%s\"}", valueOf2, 4, valueOf3, valueOf4, str2);
                objArr = new Object[]{valueOf2, 4, Double.valueOf(((double) this.endTime) / 1000.0d), valueOf4, str2};
                str = "{\"tid\":%d,\"pid\":%d,\"ts\":\"%f\",\"ph\":\"e\",\"cat\":\"async\",\"id\":%d,\"name\":\"%s\"}";
            } else {
                Log.e(Systrace.TAG, AnonymousClass006.A07("Unsupported event type for systrace block: ", this.name));
                return "";
            }
            return String.format("%s,\n%s,\n", format, String.format(str, objArr));
        }

        public Event(int i, String str, EventType eventType2, long j, long j2) {
            this.id = i;
            this.name = str;
            this.eventType = eventType2;
            this.startTime = j;
            this.threadId = j2;
        }
    }

    public enum EventType {
        BLOCK,
        ASYNC
    }

    public static void enable(boolean z) {
        if (isEnabled != z) {
            isEnabled = z;
            if (!z) {
                flushBuffer();
                events.clear();
                threadNames.clear();
            }
        }
    }

    public static void enableSystemTracing(boolean z) {
        if (isSystemTracingEnabled != z) {
            isSystemTracingEnabled = z;
        }
    }

    public static void end(int i) {
        Integer valueOf;
        Event event;
        if (isEnabled) {
            synchronized (eventsLock) {
                HashMap<Integer, Event> hashMap = events;
                valueOf = Integer.valueOf(i);
                event = hashMap.get(valueOf);
                events.remove(valueOf);
            }
            if (event != null) {
                if (isSystemTracingEnabled && event.eventType == EventType.BLOCK) {
                    Trace.endSection();
                }
                event.endTime = System.nanoTime();
                long myTid = (long) Process.myTid();
                long j = event.threadId;
                if (myTid != j && event.eventType == EventType.BLOCK) {
                    Log.e(TAG, String.format("Systrace block event %s started in thread %d and ended in a different thread %d", Long.valueOf(j), Long.valueOf(myTid)));
                }
                if (getThreadName(myTid) == null) {
                    setThreadName();
                }
                String event2 = event.toString();
                synchronized (bufferLock) {
                    buffer = AnonymousClass006.A07(buffer, event2);
                }
                return;
            }
            Log.e(TAG, String.format("Systrace.end: block with ID=%d either does not exist or was already closed", valueOf));
        }
    }

    public static String flushBuffer() {
        String str;
        if (!isEnabled) {
            return "";
        }
        synchronized (bufferLock) {
            str = buffer;
            buffer = "";
        }
        return str;
    }

    public static String getThreadName(long j) {
        return threadNames.get(Long.valueOf(j));
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

    public static int beginAsync(String str) {
        return begin(EventType.ASYNC, str);
    }

    public static int beginAsync(String str, String str2) {
        return begin(EventType.ASYNC, AnonymousClass006.A09(str, "::", str2));
    }

    public static int beginBlock(String str) {
        return begin(EventType.BLOCK, str);
    }

    public static int beginBlock(String str, long j) {
        return begin(EventType.BLOCK, str, j);
    }

    public static int beginBlock(String str, String str2) {
        return begin(EventType.BLOCK, AnonymousClass006.A09(str, "::", str2));
    }

    public static int beginBlock(String str, String str2, long j) {
        return begin(EventType.BLOCK, AnonymousClass006.A09(str, "::", str2), j);
    }

    public static void setThreadName() {
        if (isEnabled) {
            String name = Thread.currentThread().getName();
            if (name.isEmpty()) {
                name = DEFAULT_THREAD_NAME;
            }
            setThreadName(name, true);
        }
    }

    public static void setThreadName(String str) {
        setThreadName(str, true);
    }

    public static void setThreadName(String str, boolean z) {
        if (isEnabled) {
            long myTid = (long) Process.myTid();
            HashMap<Long, String> hashMap = threadNames;
            Long valueOf = Long.valueOf(myTid);
            hashMap.put(valueOf, str);
            if (z) {
                str = String.format("%s [%d]", str, valueOf);
            }
            String format = String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"0.0\",\"ph\":\"M\",\"name\":\"thread_name\",\"args\":{\"name\":\"%s\"}},\n", valueOf, 4, str);
            synchronized (bufferLock) {
                buffer = AnonymousClass006.A07(buffer, format);
            }
        }
    }
}
