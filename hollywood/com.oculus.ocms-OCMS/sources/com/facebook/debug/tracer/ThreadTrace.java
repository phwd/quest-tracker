package com.facebook.debug.tracer;

import android.os.Build;
import android.util.SparseArray;
import com.facebook.common.time.Clock;
import com.facebook.debug.log.BLog;
import com.facebook.debug.tracer.TraceEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* access modifiers changed from: package-private */
public final class ThreadTrace {
    static final int HARD_TRACE_EVENT_LIMIT_SIZE = 2000;
    static int INVALID_TRACE_ID = -1;
    static final long MAX_TIME_LIMIT_NS = 180000000000L;
    static final int SOFT_TRACE_EVENT_LIMIT_SIZE = 1500;
    private static final String TAG = "ThreadTrace";
    private static final long mDefaultThresholdNanos = 3000000;
    private static final Comparator<TraceEvent> sComparatorByElapsedNanos = new Comparator<TraceEvent>() {
        /* class com.facebook.debug.tracer.ThreadTrace.AnonymousClass2 */

        public int compare(TraceEvent traceEvent, TraceEvent traceEvent2) {
            long eventTimeNanos = traceEvent.getEventTimeNanos() - traceEvent.getStartTimeNanos();
            long eventTimeNanos2 = traceEvent2.getEventTimeNanos() - traceEvent2.getStartTimeNanos();
            if (eventTimeNanos < eventTimeNanos2) {
                return -1;
            }
            return eventTimeNanos > eventTimeNanos2 ? 1 : 0;
        }
    };
    private static final AtomicInteger sIdGenerator = new AtomicInteger(1);
    static final ThreadLocal<ThreadTrace> sThreadLocalState = new ThreadLocal<ThreadTrace>() {
        /* class com.facebook.debug.tracer.ThreadTrace.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public ThreadTrace initialValue() {
            return new ThreadTrace();
        }
    };
    private TraceEvent[] mEvents = new TraceEvent[100];
    private int mEventsSize;
    private int mNullCount;
    private final SparseArray<TraceEvent> mOutstandingEvents = new SparseArray<>();

    ThreadTrace() {
    }

    /* access modifiers changed from: package-private */
    public int startTracer(String str, boolean z) {
        return startTracer(str, null, z);
    }

    /* access modifiers changed from: package-private */
    public int startTracer(String str, Object[] objArr, boolean z) {
        pruneTracers();
        int nextId = getNextId();
        TraceEvent startEvent = TraceEvent.getStartEvent(nextId, str, objArr, z);
        addInternal(startEvent);
        this.mOutstandingEvents.append(nextId, startEvent);
        return startEvent.getId();
    }

    /* access modifiers changed from: package-private */
    public void adjustStartTime(int i, long j) {
        TraceEvent traceEvent = this.mOutstandingEvents.get(i);
        if (traceEvent != null) {
            traceEvent.setEventTimeNanos(traceEvent.getEventTimeNanos() + (j * 1000000));
        }
    }

    /* access modifiers changed from: package-private */
    public long stopTracer(int i, long j, boolean z) {
        long nanoTime = TracerClock.nanoTime();
        long j2 = !z ? mDefaultThresholdNanos : j * 1000000;
        int indexOfKey = this.mOutstandingEvents.indexOfKey(i);
        if (indexOfKey < 0) {
            return -1;
        }
        TraceEvent valueAt = this.mOutstandingEvents.valueAt(indexOfKey);
        if (Build.VERSION.SDK_INT >= 11) {
            this.mOutstandingEvents.removeAt(indexOfKey);
        } else {
            this.mOutstandingEvents.remove(i);
        }
        long eventTimeNanos = nanoTime - valueAt.getEventTimeNanos();
        if (eventTimeNanos < j2) {
            int i2 = this.mEventsSize - 1;
            while (true) {
                if (i2 < 0) {
                    break;
                }
                TraceEvent[] traceEventArr = this.mEvents;
                if (traceEventArr[i2] == valueAt) {
                    traceEventArr[i2] = null;
                    this.mNullCount++;
                    valueAt.release();
                    break;
                }
                i2--;
            }
        } else {
            addInternal(TraceEvent.getStopEvent(valueAt));
        }
        return eventTimeNanos;
    }

    public TraceEvent getEvent(int i) {
        return this.mOutstandingEvents.get(i);
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        for (int i = 0; i < this.mEventsSize; i++) {
            TraceEvent traceEvent = this.mEvents[i];
            if (traceEvent != null) {
                if (traceEvent.getEventType() == TraceEvent.Type.START) {
                    this.mOutstandingEvents.remove(traceEvent.getId());
                }
                traceEvent.release();
            }
        }
        this.mEventsSize = 0;
        this.mNullCount = 0;
        this.mOutstandingEvents.clear();
    }

    /* access modifiers changed from: package-private */
    public void addComment(String str, Object[] objArr) {
        addInternal(TraceEvent.getCommentEvent(getNextId(), str, objArr));
    }

    /* access modifiers changed from: package-private */
    public void addSpawn(String str) {
        addInternal(TraceEvent.getSpawnEvent(getNextId(), str));
    }

    /* access modifiers changed from: package-private */
    public int getLastTraceId() {
        for (int i = this.mEventsSize - 1; i >= 0; i--) {
            TraceEvent traceEvent = this.mEvents[i];
            if (traceEvent != null) {
                return traceEvent.getId();
            }
        }
        return INVALID_TRACE_ID;
    }

    /* access modifiers changed from: package-private */
    public long getLastEventTimeNanos() {
        for (int i = this.mEventsSize - 1; i >= 0; i--) {
            TraceEvent traceEvent = this.mEvents[i];
            if (traceEvent != null) {
                return traceEvent.getEventTimeNanos();
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public List<TraceEvent> getEvents(int i) {
        removeNulls();
        int startTraceIndex = getStartTraceIndex(i);
        if (startTraceIndex == -1) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(Arrays.asList(this.mEvents).subList(startTraceIndex, getStopTraceIndex(i) + 1));
    }

    /* access modifiers changed from: package-private */
    public List<TraceEvent> getAllEvents() {
        removeNulls();
        return Collections.unmodifiableList(Arrays.asList(this.mEvents).subList(0, this.mEventsSize));
    }

    /* access modifiers changed from: package-private */
    public void logTrace(int i, int i2, String str) {
        TraceFormatter.logTrace(i, i2, str, Arrays.asList(this.mEvents).subList(0, this.mEventsSize), this.mOutstandingEvents);
    }

    /* access modifiers changed from: package-private */
    public void pruneTracers() {
        long realNanoTime = TracerClock.realNanoTime();
        int i = 0;
        try {
            if (getTracerCount() <= 2000) {
                long realNanoTime2 = TracerClock.realNanoTime() - realNanoTime;
                if (realNanoTime2 > 1000000) {
                    BLog.d(TAG, "Pruned %d events on thread 0x%x in %d ms", Integer.valueOf(i), Long.valueOf(Thread.currentThread().getId()), Long.valueOf(realNanoTime2 / 1000000));
                    return;
                }
                return;
            }
            int pruneOldStoppedTracers = i + pruneOldStoppedTracers() + pruneFineGrainedTracers(SOFT_TRACE_EVENT_LIMIT_SIZE);
            if (getTracerCount() > SOFT_TRACE_EVENT_LIMIT_SIZE) {
                pruneOldStoppedTracers += pruneComments(SOFT_TRACE_EVENT_LIMIT_SIZE);
            }
            if (getTracerCount() > 2000) {
                BLog.wtf(TAG, "Resetting because hit couldn't get under hard limit after normal pruning");
                reset();
            } else if (getTracerCount() > SOFT_TRACE_EVENT_LIMIT_SIZE) {
                BLog.w(TAG, "Couldn't get under soft limit after normal pruning");
            }
        } finally {
            long realNanoTime3 = TracerClock.realNanoTime() - realNanoTime;
            if (realNanoTime3 > 1000000 || i > 0) {
                BLog.d(TAG, "Pruned %d events on thread 0x%x in %d ms", Integer.valueOf(i), Long.valueOf(Thread.currentThread().getId()), Long.valueOf(realNanoTime3 / 1000000));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int pruneOldStoppedTracers() {
        long nanoTime = TracerClock.nanoTime();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.mEventsSize; i3++) {
            TraceEvent traceEvent = this.mEvents[i3];
            if (traceEvent != null) {
                TraceEvent.Type eventType = traceEvent.getEventType();
                if (eventType == TraceEvent.Type.START) {
                    i++;
                } else if (eventType == TraceEvent.Type.STOP) {
                    i--;
                }
                if (nanoTime - traceEvent.getEventTimeNanos() < MAX_TIME_LIMIT_NS) {
                    break;
                } else if (i == 0) {
                    i2 = i3;
                }
            }
        }
        int i4 = 0;
        if (i2 > 0) {
            for (int i5 = 0; i5 < i2 + 1; i5++) {
                TraceEvent traceEvent2 = this.mEvents[i5];
                if (traceEvent2 != null) {
                    traceEvent2.release();
                    this.mEvents[i5] = null;
                    this.mNullCount++;
                    i4++;
                }
            }
        }
        BLog.d(TAG, "Pruned %d old events", Integer.valueOf(i4));
        return i4;
    }

    /* access modifiers changed from: package-private */
    public int pruneFineGrainedTracers(int i) {
        long calculateMaxEventsNanosThreshold = calculateMaxEventsNanosThreshold(i);
        if (calculateMaxEventsNanosThreshold < 0) {
            return 0;
        }
        return pruneFineGrainedTracersAtNanosThreshold(calculateMaxEventsNanosThreshold);
    }

    /* access modifiers changed from: package-private */
    public long calculateMaxEventsNanosThreshold(int i) {
        if (getTracerCount() <= i) {
            return -1;
        }
        PriorityQueue priorityQueue = new PriorityQueue(this.mEventsSize / 2, sComparatorByElapsedNanos);
        for (int i2 = 0; i2 < this.mEventsSize; i2++) {
            TraceEvent traceEvent = this.mEvents[i2];
            if (traceEvent != null && traceEvent.getEventType() == TraceEvent.Type.STOP) {
                priorityQueue.add(traceEvent);
            }
        }
        int tracerCount = getTracerCount() - i;
        Iterator it = priorityQueue.iterator();
        long j = 0;
        while (it.hasNext() && tracerCount > 0) {
            TraceEvent traceEvent2 = (TraceEvent) it.next();
            j = traceEvent2.getEventTimeNanos() - traceEvent2.getStartTimeNanos();
            tracerCount -= 2;
        }
        return tracerCount <= 0 ? j : Clock.MAX_TIME;
    }

    /* access modifiers changed from: package-private */
    public int pruneFineGrainedTracersAtNanosThreshold(long j) {
        int[] iArr = new int[10];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.mEventsSize; i3++) {
            TraceEvent traceEvent = this.mEvents[i3];
            if (traceEvent != null) {
                if (traceEvent.getEventType() == TraceEvent.Type.START) {
                    if (i2 >= iArr.length) {
                        iArr = Arrays.copyOf(iArr, ((iArr.length * 3) / 2) + 1);
                    }
                    iArr[i2] = i3;
                    i2++;
                } else if (traceEvent.getEventType() == TraceEvent.Type.STOP) {
                    int i4 = iArr[i2 - 1];
                    i2--;
                    if (traceEvent.getEventTimeNanos() - traceEvent.getStartTimeNanos() <= j) {
                        TraceEvent traceEvent2 = this.mEvents[i4];
                        traceEvent.release();
                        traceEvent2.release();
                        TraceEvent[] traceEventArr = this.mEvents;
                        traceEventArr[i3] = null;
                        traceEventArr[i4] = null;
                        this.mNullCount += 2;
                        i += 2;
                    }
                }
            }
        }
        BLog.d(TAG, "Pruned %d fine grain events", Integer.valueOf(i));
        return i;
    }

    /* access modifiers changed from: package-private */
    public int pruneComments(int i) {
        int tracerCount = getTracerCount() - i;
        int i2 = 0;
        for (int i3 = 0; i3 < this.mEventsSize && tracerCount > 0; i3++) {
            TraceEvent traceEvent = this.mEvents[i3];
            if (traceEvent != null && traceEvent.getEventType() == TraceEvent.Type.COMMENT) {
                traceEvent.release();
                this.mEvents[i3] = null;
                this.mNullCount++;
                i2++;
                tracerCount--;
            }
        }
        BLog.d(TAG, "Pruned %d comments", Integer.valueOf(i2));
        return i2;
    }

    private void removeNulls() {
        int i = 0;
        for (int i2 = 0; i2 < this.mEventsSize; i2++) {
            TraceEvent[] traceEventArr = this.mEvents;
            TraceEvent traceEvent = traceEventArr[i2];
            if (traceEvent != null) {
                traceEventArr[i] = traceEvent;
                i++;
            }
        }
        this.mEventsSize = i;
        this.mNullCount = 0;
    }

    private int getStartTraceIndex(int i) {
        for (int i2 = 0; i2 < this.mEventsSize; i2++) {
            TraceEvent traceEvent = this.mEvents[i2];
            if (traceEvent != null && traceEvent.getEventType().isStartLikeEvent() && traceEvent.getId() == i) {
                return i2;
            }
        }
        return -1;
    }

    private int getStopTraceIndex(int i) {
        int i2 = 0;
        while (true) {
            int i3 = this.mEventsSize;
            if (i2 >= i3) {
                return i3 - 1;
            }
            TraceEvent traceEvent = this.mEvents[i2];
            if (traceEvent != null && traceEvent.getEventType().isStopLikeEvent() && traceEvent.getId() == i) {
                return i2;
            }
            i2++;
        }
    }

    private int getTracerCount() {
        return this.mEventsSize - this.mNullCount;
    }

    private void addInternal(TraceEvent traceEvent) {
        if (this.mNullCount * 8 > this.mEventsSize) {
            removeNulls();
        }
        int i = this.mEventsSize;
        TraceEvent[] traceEventArr = this.mEvents;
        if (i >= traceEventArr.length) {
            this.mEvents = (TraceEvent[]) Arrays.copyOf(traceEventArr, (traceEventArr.length * 2) + 1);
        }
        TraceEvent[] traceEventArr2 = this.mEvents;
        int i2 = this.mEventsSize;
        this.mEventsSize = i2 + 1;
        traceEventArr2[i2] = traceEvent;
    }

    private static int getNextId() {
        int incrementAndGet = sIdGenerator.incrementAndGet();
        while (true) {
            if (incrementAndGet != INVALID_TRACE_ID && incrementAndGet != 0) {
                return incrementAndGet;
            }
            incrementAndGet = sIdGenerator.incrementAndGet();
        }
    }
}
