package com.oculus.panellib;

import android.os.Process;
import android.os.Trace;
import android.util.Log;
import java.util.HashMap;

public class Systrace {
    private static final String DEFAULT_THREAD_NAME = "Java";
    private static final int PID = 4;
    private static final int START_EVENT_ID = 1000000;
    private static final String TAG = "Systrace";
    private static String buffer = "";
    private static final Object bufferLock = new Object();
    private static int curEventId = START_EVENT_ID;
    private static HashMap<Integer, Event> events = new HashMap<>();
    private static final Object eventsLock = new Object();
    private static boolean isEnabled = false;
    private static boolean isSystemTracingEnabled = false;
    private static HashMap<Long, String> threadNames = new HashMap<>();

    /* access modifiers changed from: private */
    public enum EventType {
        BLOCK,
        ASYNC
    }

    /* access modifiers changed from: private */
    public static class Event {
        public long endTime = -1;
        public final EventType eventType;
        public final int id;
        public final String name;
        public final long startTime;
        public final long threadId;

        public Event(int id2, String name2, EventType eventType2, long startTime2, long threadId2) {
            this.id = id2;
            this.name = name2;
            this.eventType = eventType2;
            this.startTime = startTime2;
            this.threadId = threadId2;
        }

        public String toString() {
            if (this.eventType == EventType.BLOCK) {
                return String.format("%s,\n%s,\n", String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"%f\",\"ph\":\"B\",\"name\":\"%s\"}", Long.valueOf(this.threadId), 4, Double.valueOf(((double) this.startTime) / 1000.0d), this.name), String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"%f\",\"ph\":\"E\"}", Long.valueOf(this.threadId), 4, Double.valueOf(((double) this.endTime) / 1000.0d)));
            } else if (this.eventType == EventType.ASYNC) {
                return String.format("%s,\n%s,\n", String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"%f\",\"ph\":\"b\",\"cat\":\"async\",\"id\":%d,\"name\":\"%s\"}", Long.valueOf(this.threadId), 4, Double.valueOf(((double) this.startTime) / 1000.0d), Integer.valueOf(this.id), this.name), String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"%f\",\"ph\":\"e\",\"cat\":\"async\",\"id\":%d,\"name\":\"%s\"}", Long.valueOf(this.threadId), 4, Double.valueOf(((double) this.endTime) / 1000.0d), Integer.valueOf(this.id), this.name));
            } else {
                Log.e(Systrace.TAG, "Unsupported event type for systrace block: " + this.name);
                return "";
            }
        }
    }

    public static void enable(boolean enable) {
        if (isEnabled != enable) {
            isEnabled = enable;
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

    public static void enableSystemTracing(boolean enable) {
        if (isSystemTracingEnabled != enable) {
            isSystemTracingEnabled = enable;
            Object[] objArr = new Object[1];
            objArr[0] = isSystemTracingEnabled ? "enabled" : "disabled";
            Log.i(TAG, String.format("Additional tracing via Trace API is %s in Java.", objArr));
        }
    }

    public static boolean isEnabled() {
        return isEnabled;
    }

    public static int begin(EventType eventType, String name) {
        return begin(eventType, name, System.nanoTime());
    }

    public static int begin(EventType eventType, String name, long startTime) {
        int eventId;
        if (!isEnabled) {
            return 0;
        }
        synchronized (eventsLock) {
            curEventId++;
            eventId = curEventId;
            events.put(Integer.valueOf(eventId), new Event(eventId, name, eventType, startTime, (long) Process.myTid()));
        }
        if (!isSystemTracingEnabled || eventType != EventType.BLOCK) {
            return eventId;
        }
        Trace.beginSection(name);
        return eventId;
    }

    public static int beginBlock(String name) {
        return begin(EventType.BLOCK, name);
    }

    public static int beginBlock(String name, long startTime) {
        return begin(EventType.BLOCK, name, startTime);
    }

    public static int beginBlock(String tag, String name, long startTime) {
        return beginBlock(tag + "::" + name, startTime);
    }

    public static int beginBlock(String tag, String name) {
        return beginBlock(tag + "::" + name);
    }

    public static int beginAsync(String name) {
        return begin(EventType.ASYNC, name);
    }

    public static int beginAsync(String tag, String name) {
        return beginAsync(tag + "::" + name);
    }

    public static void end(int blockId) {
        Event event;
        if (isEnabled) {
            synchronized (eventsLock) {
                event = events.get(Integer.valueOf(blockId));
                events.remove(Integer.valueOf(blockId));
            }
            if (event != null) {
                if (isSystemTracingEnabled && event.eventType == EventType.BLOCK) {
                    Trace.endSection();
                }
                event.endTime = System.nanoTime();
                long threadId = (long) Process.myTid();
                if (threadId != event.threadId && event.eventType == EventType.BLOCK) {
                    Log.e(TAG, String.format("Systrace block event %s started in thread %d and ended in a different thread %d", Long.valueOf(event.threadId), Long.valueOf(threadId)));
                }
                if (getThreadName(threadId) == null) {
                    setThreadName();
                }
                String eventStr = event.toString();
                synchronized (bufferLock) {
                    buffer += eventStr;
                }
                return;
            }
            Log.e(TAG, String.format("Systrace.end: block with ID=%d either does not exist or was already closed", Integer.valueOf(blockId)));
        }
    }

    public static String getThreadName(long threadId) {
        return threadNames.get(Long.valueOf(threadId));
    }

    public static void setThreadName(String name, boolean addIdSuffix) {
        String threadName;
        if (isEnabled) {
            long threadId = (long) Process.myTid();
            threadNames.put(Long.valueOf(threadId), name);
            if (addIdSuffix) {
                threadName = String.format("%s [%d]", name, Long.valueOf(threadId));
            } else {
                threadName = name;
            }
            String entry = String.format("{\"tid\":%d,\"pid\":%d,\"ts\":\"0.0\",\"ph\":\"M\",\"name\":\"thread_name\",\"args\":{\"name\":\"%s\"}},\n", Long.valueOf(threadId), 4, threadName);
            synchronized (bufferLock) {
                buffer += entry;
            }
        }
    }

    public static void setThreadName(String name) {
        setThreadName(name, true);
    }

    public static void setThreadName() {
        if (isEnabled) {
            String threadName = Thread.currentThread().getName();
            if (threadName.isEmpty()) {
                threadName = DEFAULT_THREAD_NAME;
            }
            setThreadName(threadName);
        }
    }

    public static String flushBuffer() {
        String res;
        if (!isEnabled) {
            return "";
        }
        synchronized (bufferLock) {
            res = buffer;
            buffer = "";
        }
        return res;
    }
}
